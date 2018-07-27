package com.example.rabbitmqdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitConfig.class);
	
	public static final String QUEUE_SIMPLE_ORDERS = "simple-orders-queue";
	public static final String EXCHANGE_ORDERS = "orders-exchange";
	
	// declare Queue with name "simple-orders-queue"
	@Bean
	Queue simpleOrdersQueue() {
		LOGGER.info("Creating orders_queue");
		return QueueBuilder.durable(QUEUE_SIMPLE_ORDERS).build();
	}
	
	// declare Exchange with name "orders-exchange"
	@Bean
	Exchange ordersExchange() {
		LOGGER.info("Creating orders_exchange");
		return ExchangeBuilder.topicExchange(EXCHANGE_ORDERS).build();
	}
	
	// define binding between orders-queue and orders-exchange so that any message sent 
	// to orders-exchange with routing-key as “orders-queue” will be sent to orders-queue.
	@Bean
	Binding binding(Queue simpleOrdersQueue, TopicExchange ordersExchange) {
		LOGGER.info("Binding orders_queue to orders_exchange");
		return BindingBuilder.bind(simpleOrdersQueue).to(ordersExchange).with(QUEUE_SIMPLE_ORDERS);
	}
}
