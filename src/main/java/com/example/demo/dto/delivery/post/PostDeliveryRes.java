package com.example.demo.dto.delivery.post;

import com.example.demo.config.enums.DeliveryStatus;
import com.example.demo.domain.Delivery;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostDeliveryRes {
    private long id;

    private long customerId;

    private long ordersId;

    private String name;

    private String phoneNumber;

    private String address;

    private DeliveryStatus status;

    private int totalPrice;

    public static PostDeliveryRes toDto(Delivery delivery){
        PostDeliveryRes postDeliveryRes = PostDeliveryRes.builder()
                .id(delivery.getId())
                .customerId(delivery.getCustomer().getId())
                .ordersId(delivery.getOrders().getId())
                .name(delivery.getName())
                .phoneNumber(delivery.getPhoneNumber())
                .address(delivery.getAddress())
                .status(delivery.getStatus())
                .totalPrice(delivery.getTotalPrice())
                .build();
        return postDeliveryRes;
    }


}
