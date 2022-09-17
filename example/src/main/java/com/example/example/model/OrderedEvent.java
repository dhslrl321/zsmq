package com.example.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderedEvent {
    private final Long orderId = IdGenerator.gen();
    private Long userId;
    private String item;
    private Integer price;
}
