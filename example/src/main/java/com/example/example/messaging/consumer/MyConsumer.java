package com.example.example.messaging.consumer;

import com.github.dhslrl321.zsmq.annotation.ZolaConsumer;
import com.github.dhslrl321.zsmq.annotation.ZolaMessageListener;
import com.github.dhslrl321.zsmq.client.ZolaQueueMessageTemplate;
import com.github.dhslrl321.zsmq.commons.Serializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@ZolaConsumer
@RequiredArgsConstructor
public class MyConsumer {

    private final ZolaQueueMessageTemplate template;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    static class Foo {
        String payload;
    }
    @ZolaMessageListener(queueName = "seoul")
    public void listen(String message) {
        Foo deserialize = Serializer.deserialize(message, Foo.class);

        System.out.println("deserialize = " + deserialize.getPayload());
    }
}
