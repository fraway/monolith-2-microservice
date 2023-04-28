package com.example.pdf.task;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.pdf.messaging.Messaging;

import io.nats.client.Message;
import io.nats.client.Subscription;

@SpringBootApplication
public class PDFConsumer {
    private final Messaging messaging;

    @Autowired
    public PDFConsumer(Messaging messaging) {
        this.messaging = messaging;
    }

    static ConfigurableApplicationContext ctx;

    @Bean
    public CommandLineRunner run() {

        return args -> {
            Subscription sub = messaging.listen();

            // Read a message
            Message msg = sub.nextMessage(Duration.ZERO);

            String str = new String(msg.getData(), StandardCharsets.UTF_8);
            System.out.println(str);
        };
    }

    public static void main(String[] args) {
        ctx = new SpringApplicationBuilder(PDFConsumer.class)
                .run(args);

    }

    // public static void main(String[] args) {
    // new SpringApplicationBuilder(PDFConsumer.class).run(args);

    // ConfigurableApplicationContext context =
    // SpringApplication.run(PDFConsumer.class, args);

    // Messaging messaging = context.getBean(Messaging.class);
    // try {
    // // Subscribe
    // Subscription sub = messaging.listen();

    // // Read a message
    // Message msg = sub.nextMessage(Duration.ZERO);

    // String str = new String(msg.getData(), StandardCharsets.UTF_8);
    // System.out.println(str);

    // // nc.close();

    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // } catch (InterruptedException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // }

}
