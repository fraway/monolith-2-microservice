package com.example.pdf.task;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.pdf.PdfMaker;
import com.example.pdf.Request;
import com.example.pdf.messaging.Messaging;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.nats.client.Message;
import io.nats.client.Subscription;

@Component
public class PDFConsumer implements CommandLineRunner {
    private final Messaging messaging;
    private final PdfMaker pdfMaker;

    @Autowired
    public PDFConsumer(Messaging messaging, PdfMaker pdfMaker) {
        this.messaging = messaging;
        this.pdfMaker = pdfMaker;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("PDFConsumer.run()");
        Subscription sub = messaging.listen();


        while (true) {
            try {
                // Read a message
                Message msg = sub.nextMessage(Duration.ZERO);

                String str = new String(msg.getData(), StandardCharsets.UTF_8);
                System.out.println(str);

                Request req = new ObjectMapper().readValue(str, Request.class);

                this.pdfMaker.make(req);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
