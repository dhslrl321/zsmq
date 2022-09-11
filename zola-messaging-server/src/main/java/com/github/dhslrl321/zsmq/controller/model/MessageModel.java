package com.github.dhslrl321.zsmq.controller.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageModel {

    private HeaderModel header;
    private String payload;

    @Data
    @AllArgsConstructor
    protected static final class HeaderModel {
        private String queueName;
        private LocalDateTime timestamp;
        private String mediaType;
    }
}
