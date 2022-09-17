package com.example.example.messaging.publisher;

import com.example.example.model.OrderedEvent;
import com.github.dhslrl321.zsmq.client.ZolaQueueMessageTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageSender {
    private final ZolaQueueMessageTemplate template;
    public String send(String event) {
        try {
            template.convertAndSend("ORDERED-QUEUE", event);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "event publish failed!!";
        }
    }
    public String send(OrderedEvent event) {
        try {
            template.convertAndSend("ORDERED-QUEUE", event);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "event publish failed!!";
        }
    }
}
