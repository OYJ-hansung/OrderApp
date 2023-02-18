package com.example.demo.dto.order_product;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderProductDTO {
    private long id;

    private long ordersId;

    private long productId;

    private int count;
}
