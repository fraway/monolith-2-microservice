package com.example.demo.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "users")
public class User {
    // @Setter(value = AccessLevel.NONE)
    @Id
    @GeneratedValue
    private Long id;

    // @Getter(value = AccessLevel.NONE)
    private String username;

    // @Getter(value = AccessLevel.NONE)
    private String email;

    // @Getter(value = AccessLevel.NONE)
    private String address;
}
