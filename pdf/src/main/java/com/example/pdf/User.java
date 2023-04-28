package com.example.pdf;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String email;
    private String address;
}
