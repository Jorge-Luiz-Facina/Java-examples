package org.example.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.example.client.AccountClient;
import org.example.entity.Account;
import org.example.service.AccountService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;


@Component
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountClient accountClient;

    @Async
    @Override
    public CompletableFuture<Account> getAccount(){
        var response = CompletableFuture.supplyAsync(() -> accountClient.getAccount());

        return response;
    }

}
