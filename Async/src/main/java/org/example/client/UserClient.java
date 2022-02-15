package org.example.client;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.User;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserClient {

    public User getUser(){
        try {
            log.info("Initiated User");
            Thread.sleep(5000);
            log.info("finished User");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  User.builder().name("Jorge").build();
    }

}
