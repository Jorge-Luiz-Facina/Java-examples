package org.example.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

	@Value("${rabbitmq.queue}")
	String queueName;

	@Autowired
	private AmqpAdmin admin;

	@Bean
	String queue() {
		return admin.declareQueue(new Queue(queueName, true));
	}
}
