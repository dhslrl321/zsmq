package com.wonit.zola.queue;

import lombok.Value;

@Value(staticConstructor = "of")
public class QueueName {
    String value;
}
