package com.wonit.orderservice.order.messaging;

import java.time.ZonedDateTime;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value(staticConstructor = "of")
public class CanceledEvent {
    Long cancelId = ZonedDateTime.now().toEpochSecond();
    Long orderId;
}
