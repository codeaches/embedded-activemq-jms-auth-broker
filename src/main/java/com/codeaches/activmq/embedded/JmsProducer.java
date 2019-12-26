package com.codeaches.activmq.embedded;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsProducer {

    Logger log = LoggerFactory.getLogger(JmsProducer.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${activemq.default-destination}")
    String destination;

    public void send(String message) {
	log.info("Sending message='{}'", message);
	jmsTemplate.convertAndSend(destination, message);
    }
}
