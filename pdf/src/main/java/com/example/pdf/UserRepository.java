package com.example.pdf;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRepository {

    Optional<User> findById(long id) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            User user = restTemplate.getForObject("http://localhost:8080/users/" + id, User.class);
            return Optional.of(user);
        } catch (Exception e) {
            return null;
        }
    }
}
