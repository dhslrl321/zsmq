package com.github.dhslrl321.zsmq.controller;

import com.github.dhslrl321.zsmq.queue.QueueName;
import com.github.dhslrl321.zsmq.queue.SimpleZolaQueue;
import com.github.dhslrl321.zsmq.queue.ZolaQueueContainer;
import com.github.dhslrl321.zsmq.controller.model.SimpleResponse;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class CreateQueueController {

    private final ZolaQueueContainer dispatcher;

    @PostMapping("/queues")
    public ResponseEntity<SimpleResponse> create(@RequestBody Map<String, String> requestBody) {
        String name = requestBody.get("name");
        dispatcher.register(getNewZolaQueue(name));

        return ResponseEntity.status(HttpStatus.CREATED).body(new SimpleResponse("create success"));
    }

    private SimpleZolaQueue getNewZolaQueue(String name) {
        return SimpleZolaQueue.newInstance(QueueName.of(name));
    }
}
