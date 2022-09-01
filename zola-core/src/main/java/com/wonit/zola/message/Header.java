package com.wonit.zola.message;

import com.wonit.zola.queue.value.QueueName;
import java.time.LocalDateTime;
import lombok.Value;

@Value(staticConstructor = "of")
public class Header {
    QueueName queueName;
    LocalDateTime publishedAt;
}
