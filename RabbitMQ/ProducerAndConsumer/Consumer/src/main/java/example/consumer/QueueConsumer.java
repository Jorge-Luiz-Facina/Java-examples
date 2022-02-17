package example.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class QueueConsumer {

    @Value("${rabbitmq.queue}")
    private String queueName;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void listen(String message) {
        log.info("Message read from queue : " + message);
    }

}