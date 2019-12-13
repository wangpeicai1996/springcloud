package com.pcwang.order;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * 发送mq消息
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MqSenderTest{

    @Autowired
    private AmqpTemplate amqpTemplate;

   @Test
    public void send(){
        amqpTemplate.convertAndSend("myQueue","now "+new Date());
    }

    @Test
    public void sendByKey(){
        amqpTemplate.convertAndSend("myKeyExchange","test","now "+new Date());
    }



}
