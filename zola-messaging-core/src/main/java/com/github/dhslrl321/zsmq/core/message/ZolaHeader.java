package com.github.dhslrl321.zsmq.core.message;

import com.github.dhslrl321.zsmq.core.queue.QueueName;
import java.time.LocalDateTime;
import lombok.Value;

@Value(staticConstructor = "of")
public class ZolaHeader {
    QueueName queueName;
    LocalDateTime timestamp;
    MediaTypes mediaTypes;
}
