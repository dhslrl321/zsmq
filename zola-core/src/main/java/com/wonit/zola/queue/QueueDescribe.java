package com.wonit.zola.queue;

import com.wonit.zola.queue.value.QueueInfo;
import java.util.List;
import lombok.Value;

@Value(staticConstructor = "of")
public class QueueDescribe {
    int totalQueueSize;
    List<QueueInfo> detail;
}
