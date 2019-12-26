package com.codeaches.activmq.embedded;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmbeddedJmsBrokerApplication {

    public static void main(String[] args) {
	SpringApplication.run(EmbeddedJmsBrokerApplication.class, args);
    }
}
