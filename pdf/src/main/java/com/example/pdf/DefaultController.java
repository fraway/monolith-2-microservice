package com.example.pdf;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pdf.messaging.Messaging;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class Response {
    public String filename;
}

@RestController()
@RequestMapping("/pdfs")
@CrossOrigin(origins = "*")
public class DefaultController {

    @Autowired
    Messaging messaging;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Response create(@RequestBody Request request) {
        try {
            this.messaging.postPDFCreate(request.userId, request.bio);
            return new Response("waiting...");

        } catch (IOException e) {
            e.printStackTrace();
            return new Response("failure");
        } catch (InterruptedException e) {
            e.printStackTrace();
            return new Response("failure");
        }
    }
}
