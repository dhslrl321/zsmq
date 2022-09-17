package com.example.example.controller;

import com.example.example.messaging.publisher.MessageSender;
import com.example.example.model.OrderedEvent;
import com.github.dhslrl321.zsmq.client.ZolaQueueMessageTemplate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SomeController {

    private final MessageSender sender;

    @PostMapping("/api/json")
    public String push(@RequestBody OrderRequest body) {
        OrderedEvent event = new OrderedEvent(body.userId, body.item, body.price);
        return sender.send(event);
    }

    @Data
    @AllArgsConstructor
    private static class OrderRequest {
        Long userId;
        String item;
        Integer price;
    }
}
