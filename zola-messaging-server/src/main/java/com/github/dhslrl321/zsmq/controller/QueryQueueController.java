package com.github.dhslrl321.zsmq.controller;

import com.github.dhslrl321.zsmq.message.ZolaMessage;
import com.github.dhslrl321.zsmq.queue.QueueDescribe;
import com.github.dhslrl321.zsmq.queue.QueueName;
import com.github.dhslrl321.zsmq.queue.ZolaQueueDispatcher;
import com.github.dhslrl321.zsmq.controller.model.MessageModel;
import com.github.dhslrl321.zsmq.controller.model.ModelConverter;
import lombok.RequiredArgsConstructor;
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
        ZolaMessage zolaMessage = dispatcher.peekBy(QueueName.of(name));
        return ResponseEntity.ok(ModelConverter.convert(zolaMessage));
    }

    @GetMapping("/queues")
    public ResponseEntity<QueueDescribe> getAll() {
        return ResponseEntity.ok(dispatcher.describe());
    }
}
