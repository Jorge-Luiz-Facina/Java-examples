package br.example.controller;

import br.example.producer.TopicProducer;
import br.example.producer.TopicSecondProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
@RequiredArgsConstructor
public class KafkaController {
    private final TopicSecondProducer topicSecondProducer;

    private final TopicProducer topicProducer;

    @GetMapping(value = "/send")
    public void send(@RequestBody String message){
        topicProducer.send(message);
    }


    @GetMapping(value = "/send-two")
    public void sendTwo(@RequestBody String message){
        topicProducer.send(message);
        topicSecondProducer.send(message);
    }
}
