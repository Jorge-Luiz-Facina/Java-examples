package org.example.execute;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service.AccountService;
import org.example.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@RequiredArgsConstructor
class AutoExecute implements ApplicationRunner {
    private final UserService userService;
    private final AccountService accountService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            var userAsync = userService.getUser();
            var accountAsync = accountService.getAccount();
            var user = userAsync.get();
            var account = accountAsync.get();
            log.info(user.toString() + account.toString());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
