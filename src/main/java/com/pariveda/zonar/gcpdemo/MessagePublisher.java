package com.pariveda.zonar.gcpdemo;

import avro.models.SimpleMessage;
import com.google.cloud.ByteArray;
import com.google.cloud.pubsub.Message;
import com.google.cloud.pubsub.PubSub;
import com.google.cloud.pubsub.PubSubOptions;
import com.google.common.util.concurrent.RateLimiter;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MessagePublisher {
    private static final Random random = new Random();
    private static final String topic = "gcp-pubsub-demo";

    private static final Logger log = LoggerFactory.getLogger(MessagePublisher.class);

    public static void main(String[] args) {
        int eventsPerSecond = Integer.parseInt(args[0], 10);

        RateLimiter limiter = RateLimiter.create(eventsPerSecond);
        PubSub pubsub = PubSubOptions.getDefaultInstance().getService();

        while (true) {
            limiter.acquire();

            SimpleMessage simpleMessage = SimpleMessage.newBuilder()
                                            .setMessage(randomString())
                                            .setTimestamp(currentTimeStamp()).build();

            log.info("Publishing message {}", simpleMessage);
            pubsub.publish(topic, Message.of(serialize(simpleMessage)));
        }
    }

    private static ByteArray serialize(SimpleMessage message) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(out, null);
            DatumWriter<SimpleMessage> writer = new SpecificDatumWriter(SimpleMessage.getClassSchema());

            writer.write(message, encoder);

            encoder.flush();
            out.close();

            return ByteArray.copyFrom(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

    private static String currentTimeStamp() {
        return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    }

    private static String randomString() {
        char[] word = new char[random.nextInt(8)+3];

        for(int i = 0; i < word.length; i++)
        {
            word[i] = (char)('a' + random.nextInt(26));
        }

        return new String(word);
    }
}
