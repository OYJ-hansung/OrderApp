package com.example.demo.dto.delivery.patch;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PatchDeliveryReq {
    private long deliveryId;
    private String name;
    private String phoneNumber;
    private String address;
}
