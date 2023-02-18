package com.example.demo.dto.delivery.patch;

import com.example.demo.domain.Delivery;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PatchDeliveryRes {
    private long deliveryId;
    private String name;
    private String phoneNumber;
    private String address;

    public static PatchDeliveryRes toDto(Delivery delivery){
        PatchDeliveryRes patchDeliveryRes = PatchDeliveryRes.builder()
                .name(delivery.getName())
                .phoneNumber(delivery.getPhoneNumber())
                .address(delivery.getAddress())
                .build();
        return patchDeliveryRes;
    }
}
