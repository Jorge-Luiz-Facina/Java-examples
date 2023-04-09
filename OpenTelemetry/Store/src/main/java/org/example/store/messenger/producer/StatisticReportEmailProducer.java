package org.example.store.messenger.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StatisticReportEmailProducer {

    @Value("${application.rabbit.producer.statisticReportEmail}")
    private String queueName;

    private final RabbitTemplate rabbitTemplate;

    public StatisticReportEmailProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void send() {
        rabbitTemplate.convertAndSend(queueName, "report email ...");
    }
}
