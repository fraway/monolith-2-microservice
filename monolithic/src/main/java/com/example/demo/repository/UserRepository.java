package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entitiy.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
