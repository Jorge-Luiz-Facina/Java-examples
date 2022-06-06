package org.example.hexagonal.adapters.inbound.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.hexagonal.application.ports.in.DeleteBookInPort;
import org.springframework.kafka.annotation.KafkaListener;

@RequiredArgsConstructor
//@Service
public class BookConsumer {

    private final DeleteBookInPort deleteBookInPort;

    @KafkaListener(topics = {"${topic.name.consumer}"}, groupId = "group_id")
    public void bookDelete(ConsumerRecord<String, String> payload) {
//        deleteBookInPort.delete();
        //Example [off]  consumer
    }
}
