package com.course.distributecommunication.frontend;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import static com.course.distributecommunication.rabbit.Constants.BOOK_AND_AUTHOR_QUEUE;

@EnableRabbit
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Queue bookAndAuthorQueue() {
        return new Queue(BOOK_AND_AUTHOR_QUEUE, false);
    }
}
