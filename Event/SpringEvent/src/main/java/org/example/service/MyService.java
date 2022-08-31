package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.context.dto.EmailSendDTO;
import org.example.context.event.EmailEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class MyService {

    private final ApplicationEventPublisher publisher;

    public void test() {
        log.info("initialized test");
        publisher.publishEvent(new EmailEvent(this, new EmailSendDTO()));
        log.info("finished test");
    }

}
