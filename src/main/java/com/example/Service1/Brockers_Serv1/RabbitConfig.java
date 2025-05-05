package com.example.Service1.Brockers_Serv1;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//Service1 — отправляет в queue2, получает из queue1
@Configuration
public class RabbitConfig {
    @Bean
    public Queue queue1() {
        return new Queue("queue1"); // Получает из queue1
    }

    @Bean
    public Queue queue2() {
        return new Queue("queue2"); // Отправляет в queue2
    }
}
