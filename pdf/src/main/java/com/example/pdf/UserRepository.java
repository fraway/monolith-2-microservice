package com.example.pdf;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@Service
public class UserRepository {

    @Value("${monolithic.url}")
    String monolithicUrl;

    Optional<User> findById(long id) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            String url = monolithicUrl + "/users/" + id;
            System.out.println(url);
            User user = restTemplate.getForObject(url, User.class);
            return Optional.of(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("got an error: " + e.getMessage());
            return null;
        }
    }
}
