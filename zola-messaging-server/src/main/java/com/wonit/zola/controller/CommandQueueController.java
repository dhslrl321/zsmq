package com.wonit.zola.controller;

import com.wonit.zola.controller.model.MessageModel;
import com.wonit.zola.controller.model.ModelConverter;
import com.wonit.zola.controller.model.SimpleResponse;
import com.wonit.zola.message.ZolaMessage;
import com.wonit.zola.queue.ZolaQueueDispatcher;
import com.wonit.zola.queue.QueueName;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class CommandQueueController {

    private final ZolaQueueDispatcher dispatcher;

    @PostMapping("/messages")
    public ResponseEntity<SimpleResponse> addMessage(@RequestBody MessageModel request) {
        ZolaMessage zolaMessage = ModelConverter.convert(request);
        dispatcher.pushBy(zolaMessage);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SimpleResponse("message add success"));
    }

    @PatchMapping("/queues/{name}/acknowledge")
    public ResponseEntity<SimpleResponse> ack(@PathVariable String name) {
        dispatcher.popBy(QueueName.of(name));
        return ResponseEntity.status(HttpStatus.CREATED).body(new SimpleResponse("ack success"));
    }
}
