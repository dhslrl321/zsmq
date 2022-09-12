package com.example.example;

import com.github.dhslrl321.zsmq.client.ZolaQueueMessageTemplate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MessageProducer {

    private final ZolaQueueMessageTemplate template;

    public void send() {
        template.convertAndSend("MY-QUEUE", "foo");
    }
}
