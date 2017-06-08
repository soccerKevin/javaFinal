package com.pariveda.zonar.gcpdemo;

import avro.models.SimpleMessage;
import com.google.cloud.pubsub.PubSub;
import com.google.cloud.pubsub.PubSubOptions;
import com.google.cloud.pubsub.ReceivedMessage;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class DataPipeline {
    public static void main(String[] args) throws Exception {
        Logger log = LoggerFactory.getLogger(MessagePublisher.class);

        PubSub pubsub = PubSubOptions.getDefaultInstance().getService();
        Storage storage = StorageOptions.getDefaultInstance().getService();

        String subscriptionName = "demo-pull-subscription";
        String bucketName = "23we40iuj-dpl-demo-bucket";
        String bucketPrefix = "pubsub/messages/dpl-events/";

        new Thread(new RequestHandler()).start();

        while (true) {
            Iterator<ReceivedMessage> messages = pubsub.pull(subscriptionName, 100);

            while (messages.hasNext()) {
                ReceivedMessage receivedMessage = messages.next();

                SimpleMessage message = deserialize(receivedMessage.getPayload().toByteArray());
                log.info("Received message content: {}", message.toString());

                message.setMessage(message.getMessage().toString().toUpperCase());
                log.info("Transformed message content: {}", message.toString());

                long unixTime = System.currentTimeMillis() / 1000L;

                String blobName = String.format("%s%s-dpl-event", bucketPrefix, String.valueOf(unixTime));
                BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, blobName).build();

                storage.create(blobInfo, serialize(message));
                receivedMessage.ack();
            }
        }
    }

    private static byte[] serialize(SimpleMessage message) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(out, null);
            DatumWriter<SimpleMessage> writer = new SpecificDatumWriter(SimpleMessage.getClassSchema());

            writer.write(message, encoder);

            encoder.flush();
            out.close();

            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

    private static SimpleMessage deserialize(byte[] data) {
        Decoder decoder = DecoderFactory.get().binaryDecoder(data, null);

        try {
            SpecificDatumReader<SimpleMessage> reader = new SpecificDatumReader(SimpleMessage.getClassSchema());
            return reader.read(null, decoder);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }
}
