package com.github.dhslrl321.zsmq.controller.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeaderModel {
    private String queueName;
    private LocalDateTime timestamp;
    private String mediaType;
}
