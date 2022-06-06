package org.example.hexagonal.adapters.outbound;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.hexagonal.application.core.domain.Book;
import org.example.hexagonal.application.ports.out.ProducerBookOutPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProducerSaveBookOutAdapter implements ProducerBookOutPort {

    @Value("${topic.name.producer}")
    private String topicName;

    private final KafkaTemplate<String, Book> kafkaTemplate;

    @Override
    public void producer(Book book) {
        kafkaTemplate.send(new ProducerRecord<String, Book>(topicName, book));
    }
}
