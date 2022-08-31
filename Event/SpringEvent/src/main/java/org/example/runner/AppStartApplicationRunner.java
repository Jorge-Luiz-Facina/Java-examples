package org.example.runner;

import lombok.RequiredArgsConstructor;
import org.example.service.MyService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class AppStartApplicationRunner implements ApplicationRunner {
    private final MyService myService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        myService.test();
    }
}