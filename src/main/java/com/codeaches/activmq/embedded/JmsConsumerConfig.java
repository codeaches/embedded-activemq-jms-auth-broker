package com.codeaches.activmq.embedded;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
public class JmsConsumerConfig {

  @Value("${activemq.broker.url}")
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
