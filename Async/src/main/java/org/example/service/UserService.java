package org.example.service;

import org.example.entity.User;

import java.util.concurrent.CompletableFuture;

public interface UserService {
    CompletableFuture<User> getUser();
}
