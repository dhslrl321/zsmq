package com.example.example;

import com.github.dhslrl321.zsmq.annotation.ZolaConsumer;
import com.github.dhslrl321.zsmq.annotation.ZolaMessageListener;
import org.springframework.stereotype.Component;

@Component
@ZolaConsumer
public class MyConsumer {

    @ZolaMessageListener(queueName = "MY-QUEUE")
    public void listen(String message) {
        System.out.println("message = " + message);
    }
}
