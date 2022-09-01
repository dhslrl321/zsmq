package com.wonit.controller;

import com.wonit.controller.model.MessageModel;
import com.wonit.controller.model.ModelConverter;
import com.wonit.controller.model.SimpleResponse;
import com.wonit.message.Message;
import com.wonit.queue.QueueDescribe;
import com.wonit.queue.ZolaQueueDispatcher;
import com.wonit.queue.value.QueueName;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class QueryQueueController {

    private final ZolaQueueDispatcher dispatcher;

    @GetMapping("/queues/{name}")
    public ResponseEntity<MessageModel> get(@PathVariable String name) {
        Message message = dispatcher.peekBy(QueueName.of(name));
        return ResponseEntity.ok(ModelConverter.convert(message));
    }

    @GetMapping("/queues")
    public ResponseEntity<QueueDescribe> getAll() {
        return ResponseEntity.ok(dispatcher.describe());
    }
}
