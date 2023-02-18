package com.example.demo.dto.delivery.post;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostDeliveryReq {
    private long ordersId;
    private String name;
    private String phoneNumber;
    private String address;
}
