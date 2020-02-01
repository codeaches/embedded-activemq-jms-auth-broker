package com.codeaches.activmq.embedded;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class JmsProducerConfig {

  @Value("${activemq.broker.url}")
  String brokerUrl;

  @Bean
  ActiveMQConnectionFactory producerActiveMQConnectionFactory() {
    return new ActiveMQConnectionFactory("sa1", "manager", brokerUrl);
  }

  @Bean
  PooledConnectionFactory producerPooledConnectionFactory() {
    return new PooledConnectionFactory(producerActiveMQConnectionFactory());
  }

  @Bean
  JmsTemplate jmsTemplate() {
    return new JmsTemplate(producerPooledConnectionFactory());
  }
}
