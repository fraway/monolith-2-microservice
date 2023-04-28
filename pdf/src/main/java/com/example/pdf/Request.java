package com.example.pdf;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Request {
    public Long userId;
    public String bio;
}