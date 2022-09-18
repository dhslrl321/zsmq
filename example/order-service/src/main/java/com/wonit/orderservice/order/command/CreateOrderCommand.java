package com.wonit.orderservice.order.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateOrderCommand {
    private String userId;
    private String item;
    private Integer price;
}
