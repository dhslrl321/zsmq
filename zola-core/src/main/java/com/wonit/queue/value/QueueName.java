package com.wonit.queue.value;

import lombok.Value;

@Value(staticConstructor = "of")
public class QueueName {
    String value;
}
