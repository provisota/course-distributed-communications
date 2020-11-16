package com.course.distributecommunication.books;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.course.distributecommunication.rabbit.Constants.BOOK_AND_AUTHOR_QUEUE;

@EnableRabbit
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Queue bookAndAuthorQueue() {
        return new Queue(BOOK_AND_AUTHOR_QUEUE, false);
    }
}
