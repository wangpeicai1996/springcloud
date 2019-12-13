package com.pcwang.order.controller;

import com.pcwang.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessage {

    @Autowired
    private StreamClient streamClient;

    @GetMapping("/sendMessage")
    public void process(){
        String message = "Stream发送的消息";
        streamClient.output().send(MessageBuilder.withPayload(message).build());

    }
}
