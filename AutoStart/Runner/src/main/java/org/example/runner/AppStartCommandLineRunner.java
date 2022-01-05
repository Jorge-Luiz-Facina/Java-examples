package org.example.runner;

import lombok.RequiredArgsConstructor;
import org.example.services.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class AppStartCommandLineRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(AppStartCommandLineRunner.class);
    private final MyService myService;

    @Override
    public void run(String... args) throws Exception {
        logger.info(args.toString());
        myService.test();
    }
}