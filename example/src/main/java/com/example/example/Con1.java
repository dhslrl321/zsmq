package com.example.example;

import com.github.dhslrl321.zsmq.annotation.ZolaConsumer;
import com.github.dhslrl321.zsmq.annotation.ZolaMessageListener;
import org.springframework.stereotype.Component;

@Component
@ZolaConsumer
public class Con1 {
    @ZolaMessageListener(queueName = "seoul2")
    public void aa(String a) {
        System.out.println("a = " + a);
    }
}
