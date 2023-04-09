package org.example.statistic.messenger.consumer;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ReportEmailConsumer {

    @RabbitListener(queues = "${application.rabbit.consumer.reportEmail}")
    public void listen(String message) {
        log.info(message);
    }

}