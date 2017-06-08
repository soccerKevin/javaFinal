package com.pariveda.zonar.gcpdemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class RequestHandler implements Runnable {
    private static Logger logger = Logger.getLogger(RequestHandler.class.getName());

    public void run() {
        try {
            ServerSocket listener = new ServerSocket(8081);

            while (true) {
                Socket sock = listener.accept();
                PrintWriter out = new PrintWriter(sock.getOutputStream(), true);

                out.println("HTTP/1.1 200 OK");
                out.println("Content-Type: text/html");
                out.println("\r\n");
                out.println("<p> Hello world! GCP demo is running!</p>");

                sock.close();
            }
        } catch (IOException e) {
            logger.severe("Unable to start HTTP server! Port might be in use already.");
        }
    }
}
