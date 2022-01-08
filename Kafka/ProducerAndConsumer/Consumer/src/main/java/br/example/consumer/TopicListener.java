package br.example.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TopicListener {

    @KafkaListener(topics = {"${topic.name.consumer}", "${topic.name.consumer2}"}, groupId = "group_id")
    public void consume(ConsumerRecord<String, String> payload){
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Order: {}", payload.value());
    }

}
