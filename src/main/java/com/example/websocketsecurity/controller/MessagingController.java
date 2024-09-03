package com.example.websocketsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagingController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @MessageMapping("/send-message")
    public void sendMessage(@Payload String data){
        System.out.println(data);
        simpMessagingTemplate.convertAndSendToUser("823776","/messages",data);
    }
}
