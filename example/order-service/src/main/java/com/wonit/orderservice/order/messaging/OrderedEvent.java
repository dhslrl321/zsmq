package com.wonit.orderservice.order.messaging;

import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Value(staticConstructor = "of")
public class OrderedEvent {
    Long orderId = ZonedDateTime.now().toEpochSecond();
    String userId;
    String item;
    Integer price;
}
