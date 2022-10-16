package com.github.dhslrl321.zsmq.integration;

import com.github.dhslrl321.zsmq.annotation.ZolaConsumer;
import com.github.dhslrl321.zsmq.annotation.ZolaMessageListener;
import com.github.dhslrl321.zsmq.listener.DeletionPolicy;
import org.springframework.stereotype.Component;

@Component
@ZolaConsumer
public class FooZolaMessageListener {
    @ZolaMessageListener(queueName = "TEST-QUEUE-NAME", deletionPolicy = DeletionPolicy.ALWAYS)
    public void listenForTest(String message) {
        System.out.print(message);
    }
}
