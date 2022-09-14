package com.github.dhslrl321.zsmq.core.queue;

import java.time.LocalDateTime;
import lombok.Value;

@Value(staticConstructor = "of")
public class QueueInfo {
    String name;
    int messagesAvailable;
    LocalDateTime created;
}
