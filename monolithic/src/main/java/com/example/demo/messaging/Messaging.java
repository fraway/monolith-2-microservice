package com.example.demo.messaging;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.nats.client.Connection;
import io.nats.client.Nats;

@Service
public class Messaging {
    @Value("${nats.url}")
    String natsUrl;

    public void postStartup() throws IOException, InterruptedException {
        getConnection().publish("microservice.startup", "monolithic".getBytes());
    }

    void postPDFCreate(Long userId, String bio) throws IOException, InterruptedException {
        // single URL
        Connection nc = Nats.connect(natsUrl);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode().put("userId", userId).put("bio", bio);

        nc.publish("pdfs.create", node.toString().getBytes());
    }

    private Connection getConnection() throws IOException, InterruptedException {
        return Nats.connect(natsUrl);
    }
}
