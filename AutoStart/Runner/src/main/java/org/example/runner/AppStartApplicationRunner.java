package org.example.runner;

import lombok.RequiredArgsConstructor;
import org.example.services.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class AppStartApplicationRunner implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(AppStartApplicationRunner.class);
    private final MyService myService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
            logger.info(args.getOptionNames().toString());
            myService.test();
    }
}