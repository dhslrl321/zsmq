package com.github.dhslrl321.zsmq.controller;

import com.github.dhslrl321.zsmq.controller.model.MessageModel;
import com.github.dhslrl321.zsmq.controller.model.ModelConverter;
import com.github.dhslrl321.zsmq.controller.model.SimpleResponse;
import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import com.github.dhslrl321.zsmq.core.queue.QueueName;
import com.github.dhslrl321.zsmq.core.queue.ZolaQueueContainer;
import com.github.dhslrl321.zsmq.core.queue.ZolaSimpleQueue;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@CrossOrigin(value = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class CommandQueueController {

    private final ZolaQueueContainer container;

    @PostMapping("/queues")
    public ResponseEntity<SimpleResponse> createQ(@RequestBody Map<String, String> requestBody) {
        String name = requestBody.get("name");
        if (StringUtils.isBlank(name) || name.length() > 40) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        container.register(getNewZolaQueue(name));

        return ResponseEntity.status(HttpStatus.CREATED).body(new SimpleResponse("create success"));
    }

    @DeleteMapping("/queues/{name}")
    public ResponseEntity<SimpleResponse> deleteQ(@PathVariable String name) {
        if (StringUtils.isBlank(name) || name.length() > 40) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (!container.contains(name)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        container.removeBy(name);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new SimpleResponse("remove success"));
    }

    @PostMapping("/messages")
    public ResponseEntity<SimpleResponse> push(@RequestBody MessageModel request) {
        ZolaMessage zolaMessage = ModelConverter.convert(request);
        container.pushBy(zolaMessage);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SimpleResponse("message add success"));
    }

    @DeleteMapping("/queues/{name}/acknowledge")
    public ResponseEntity<SimpleResponse> ack(@PathVariable String name) {
        container.popBy(QueueName.of(name));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new SimpleResponse("ack success"));
    }

    private ZolaSimpleQueue getNewZolaQueue(String name) {
        return ZolaSimpleQueue.newInstance(QueueName.of(name));
    }
}
