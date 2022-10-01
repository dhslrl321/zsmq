package com.wonit.orderservice.order;

import com.wonit.orderservice.order.command.CreateOrderCommand;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping("/orders")
    public ResponseEntity<Result> order(@RequestBody CreateOrderCommand command) {
        try {
            service.order(command);
            return ResponseEntity.ok().body(Result.of(1, "order success"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Result.of(-1, "order failed"));
        }
    }

    @PatchMapping("/orders/{orderId}")
    public ResponseEntity<Result> cancel(@PathVariable Long orderId) {
        try {
            service.cancel(orderId);
            return ResponseEntity.ok().body(Result.of(1, "order cancel success"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Result.of(-1, "order cancel failed"));
        }
    }

    @Value(staticConstructor = "of")
    private static class Result {
        int code;
        String message;
    }
}
