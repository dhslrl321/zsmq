package com.wonit.message;

import com.wonit.queue.value.QueueName;
import java.time.LocalDateTime;
import lombok.Value;

@Value(staticConstructor = "of")
public class Header {
    QueueName queueName;
    LocalDateTime publishedAt;
}
