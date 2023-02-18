package com.example.demo.dto.order.post;

import com.example.demo.domain.Orders;
import com.example.demo.domain.Product;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostOrderRes {

    private long ordersId;

    private List<String> productListNames;

    public static PostOrderRes toDto(Orders orders, List<String> productListNames){
        PostOrderRes postOrderRes = PostOrderRes.builder()
                .ordersId(orders.getId())
                .productListNames(productListNames)
                .build();
        return postOrderRes;
    }
}
