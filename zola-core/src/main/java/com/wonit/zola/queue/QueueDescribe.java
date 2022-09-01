package com.wonit.zola.queue;

import java.util.List;
import lombok.Value;

@Value(staticConstructor = "of")
public class QueueDescribe {
    int totalQueueSize;
    List<QueueInfo> detail;
}
