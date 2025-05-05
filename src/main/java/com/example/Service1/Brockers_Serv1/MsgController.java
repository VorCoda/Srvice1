package com.example.Service1.Brockers_Serv1;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsgController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/send-to-service2")
    public Status sendMessage(@RequestBody MsgDTO message) {
        rabbitTemplate.convertAndSend("queue2", message);
        return new Status("sent to serv2") ;
    }
}
