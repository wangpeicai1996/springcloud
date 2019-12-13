package com.pcwang.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 利用springcloud组件stream完成消息队列使用
 */
public interface StreamClient {

    String INUPUT = "inputMessage";
    String OUTUPUT = "outputMessage";

    String INUPUT2 = "inputMessage2";
    String OUTUPUT2 = "outputMessage2";

    @Input(StreamClient.INUPUT)
    SubscribableChannel input();

    @Output(StreamClient.OUTUPUT)
    MessageChannel output();

    @Input(StreamClient.INUPUT2)
    SubscribableChannel input2();

    @Output(StreamClient.OUTUPUT2)
    MessageChannel output2();

}
