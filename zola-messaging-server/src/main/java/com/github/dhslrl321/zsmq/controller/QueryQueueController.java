package com.github.dhslrl321.zsmq.controller;

import com.github.dhslrl321.zsmq.controller.model.MessageModel;
import com.github.dhslrl321.zsmq.controller.model.ModelConverter;
import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import com.github.dhslrl321.zsmq.core.queue.QueueDescribe;
import com.github.dhslrl321.zsmq.core.queue.QueueName;
import com.github.dhslrl321.zsmq.core.queue.ZolaQueueContainer;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class QueryQueueController {

    private final ZolaQueueContainer container;

    @GetMapping("/queues/{name}")
    public ResponseEntity<MessageModel> peekHead(@PathVariable String name) {
        ZolaMessage zolaMessage = container.peekBy(QueueName.of(name));
        if (Objects.isNull(zolaMessage)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ModelConverter.convert(zolaMessage));
    }

    @GetMapping("/queues")
    public ResponseEntity<QueueDescribe> getQueues() {
        return ResponseEntity.ok(container.describe());
    }
}
