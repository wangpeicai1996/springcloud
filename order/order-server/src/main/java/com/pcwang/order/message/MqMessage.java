package com.pcwang.order.message;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接受mq消息
 */
@Slf4j
@Component
public class MqMessage {

    //@RabbitListener(queues = "myQueue")

    /**
     *自动创建队列  @RabbitListener(queuesToDeclare = @Queue("myQueue"))
     */
   /*
    *自动创建队列并绑定交换器Exchange
    */
   @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
   ))
    public void process(String message){
        log.info("MqReceiver:{}",message);
    }

    /*
     *指定key值，对消息进行分组
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myKeyQueue"),
            exchange = @Exchange("myKeyExchange"),
            key = "test"
    ))
    public void processByKey(String message){
        log.info("key MqReceiver:{}",message);
    }
}
