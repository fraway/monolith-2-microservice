package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entitiy.User;
import com.example.demo.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;

@RestController()
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public Iterable<User> list() {
        return this.userRepository.findAll();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")

    public User create(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @GetMapping("/{id}")
    @Operation(description = "Currently only used within PDF Microservice", summary = "Retrieve an user detail by id")
    public Optional<User> getById(@PathVariable Long id) {
        return this.userRepository.findById(id);
    }

}
