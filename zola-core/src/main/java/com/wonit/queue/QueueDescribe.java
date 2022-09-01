package com.wonit.queue;

import com.wonit.queue.value.QueueInfo;
import java.util.List;
import lombok.Value;

@Value(staticConstructor = "of")
public class QueueDescribe {
    int totalQueueSize;
    List<QueueInfo> detail;
}
