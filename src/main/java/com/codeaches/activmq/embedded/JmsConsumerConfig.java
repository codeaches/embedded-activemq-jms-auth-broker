package com.codeaches.activmq.embedded;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
public class JmsConsumerConfig {

    Logger log = LoggerFactory.getLogger(JmsConsumerConfig.class);

    @Value("${activemq.broker-url}")
    String brokerUrl;

    @Bean
    ActiveMQConnectionFactory consumerActiveMQConnectionFactory() {
	return new ActiveMQConnectionFactory("sa1", "manager", brokerUrl);
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {

	DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	factory.setConnectionFactory(consumerActiveMQConnectionFactory());
	return factory;
    }
}
