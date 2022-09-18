package com.wonit.orderservice.order;

import com.wonit.orderservice.order.command.CreateOrderCommand;
import com.wonit.orderservice.order.messaging.CanceledEvent;
import com.wonit.orderservice.order.messaging.OrderedEvent;
import com.wonit.orderservice.order.messaging.QueueMessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final QueueMessageProducer producer;

    public void order(CreateOrderCommand command) {
        OrderedEvent event = OrderedEvent.of(command.getUserId(), command.getItem(), command.getPrice());
        producer.ordered(event);
    }

    public void cancel(Long orderId) {
        CanceledEvent event = CanceledEvent.of(orderId);
        producer.canceled(event);
    }
}
