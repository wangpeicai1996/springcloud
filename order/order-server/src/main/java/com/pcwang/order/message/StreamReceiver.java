package com.pcwang.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

    @StreamListener(StreamClient.OUTUPUT)
    //将响应结果发送到指定的消息队列
    @SendTo(StreamClient.INUPUT2)
    public String processOut(Object message){
        log.info("StreamReceiver:{}", message);
        return "处理完毕";
    }

    /*
     *获取响应结果的方法
     */
    @StreamListener(StreamClient.INUPUT2)
    public  void processOut2(String message){
        log.info("StreamReceiver2:{}", message);
    }
}
