package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.producer.QueueProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/RabbitMQ")
@RequiredArgsConstructor
public class RabbitMQController {
    private final QueueProducer queueProducer;

    @PostMapping(value = "/send")
    public void send(@RequestBody String message){
        queueProducer.send(message);
    }


}
