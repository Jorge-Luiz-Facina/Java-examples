package org.example.client;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.Account;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountClient {

    public Account getAccount(){
        try {
            log.info("Initiated Account");
            Thread.sleep(10000);
            log.info("finished Account");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  Account.builder().number("321321321").build();
    }

}
