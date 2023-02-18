package com.example.demo.domain;
import com.example.demo.config.enums.DeliveryStatus;
import com.example.demo.dto.delivery.patch.PatchDeliveryReq;
import lombok.*;
import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders orders;

    private String name;

    private String phoneNumber;

    private String address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private int totalPrice;

    public static Delivery editDelivery(Delivery delivery, PatchDeliveryReq patchDeliveryReq){
        delivery.setName(patchDeliveryReq.getName());
        delivery.setPhoneNumber(patchDeliveryReq.getPhoneNumber());
        delivery.setAddress(patchDeliveryReq.getAddress());

        return delivery;
    }
}
