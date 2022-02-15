package org.example.service;

import org.example.entity.Account;

import java.util.concurrent.CompletableFuture;

public interface AccountService {
    CompletableFuture<Account> getAccount();

}
