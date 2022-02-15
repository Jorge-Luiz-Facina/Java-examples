package org.example.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.example.client.UserClient;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserClient userClient;

    @Async
    @Override
    public CompletableFuture<User> getUser(){
        var response = CompletableFuture.supplyAsync(() -> userClient.getUser());
        return response;
    }
}
