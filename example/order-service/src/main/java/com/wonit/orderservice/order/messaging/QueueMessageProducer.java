package com.wonit.orderservice.order.messaging;

import com.github.dhslrl321.zsmq.client.ZolaQueueMessageTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueueMessageProducer {
    public static final String ORDER_CANCELED_QUEUE = "ORDER-CANCELED-QUEUE";
    public static final String PRE_ORDERED_QUEUE = "ORDERED-QUEUE";

    private final ZolaQueueMessageTemplate template;

    public void ordered(OrderedEvent event) {
        template.convertAndSend(PRE_ORDERED_QUEUE, event);
    }

    public void canceled(CanceledEvent event) {
        template.convertAndSend(ORDER_CANCELED_QUEUE, event);
    }
}
