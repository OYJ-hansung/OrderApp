package com.example.demo.dto.order.post;

import com.example.demo.domain.Orders;
import com.example.demo.domain.Product;
import com.example.demo.dto.order_product.OrderProductDTO;
import lombok.*;
import org.hibernate.criterion.Order;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostOrderReq {
    private List<OrderProductDTO> orderProductDtoList;
}
