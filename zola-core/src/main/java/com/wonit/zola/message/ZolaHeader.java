package com.wonit.zola.message;

import com.wonit.zola.queue.QueueName;
import java.time.LocalDateTime;
import lombok.Value;

@Value(staticConstructor = "of")
public class ZolaHeader {
    QueueName queueName;
    LocalDateTime publishedAt;
}
