package com.example.demo.service;

import com.example.demo.config.enums.DeliveryStatus;
import com.example.demo.domain.*;
import com.example.demo.dto.delivery.patch.PatchDeliveryReq;
import com.example.demo.dto.delivery.patch.PatchDeliveryRes;
import com.example.demo.dto.delivery.post.PostDeliveryReq;
import com.example.demo.dto.delivery.post.PostDeliveryRes;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.DeliveryRepository;
import com.example.demo.repository.OrderProductRepository;
import com.example.demo.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final CustomerRepository customerRepository;

    private final OrdersRepository ordersRepository;

    private final OrderProductRepository orderProductRepository;

    private final DeliveryRepository deliveryRepository;

    @Transactional(readOnly = false)
    public PostDeliveryRes setDelivery(PostDeliveryReq postDeliveryReq,Long customerId){
        Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new RuntimeException("not existing customer"));
        Orders orders = ordersRepository.findById(postDeliveryReq.getOrdersId()).orElseThrow(()-> new RuntimeException("not existing order"));

        // 배송지 정보 입력 확인
        if(postDeliveryReq.getAddress().isEmpty())
            throw new RuntimeException("address is necessary");

        // 총 가격
        List<OrderProduct> orderProductList = orderProductRepository.findByOrdersId(postDeliveryReq.getOrdersId());
        int totalPrice = 0;
        for (OrderProduct orderProduct: orderProductList){
            totalPrice += orderProduct.getProduct().getProductPrice() * orderProduct.getCount();
        }

        // 배송정보 저장
        Delivery delivery = Delivery.builder()
                .customer(customer)
                .orders(orders)
                .name(postDeliveryReq.getName())
                .phoneNumber(postDeliveryReq.getPhoneNumber())
                .address(postDeliveryReq.getAddress())
                .totalPrice(totalPrice)
                .status(DeliveryStatus.WAITING)
                .build();
        deliveryRepository.save(delivery);

        return PostDeliveryRes.toDto(delivery);
    }

    @Transactional(readOnly = false)
    public void startDelivery(long deliveryId){
        // 배송정보 확인
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(()-> new RuntimeException("not existing delivery"));

        // 이미 배송중인지 확인
        if(delivery.getStatus() == DeliveryStatus.ONTHEWAY)
            throw new RuntimeException("Its currently on the way, cant change status");

        delivery.setStatus(DeliveryStatus.ONTHEWAY);
        deliveryRepository.save(delivery);
    }

    @Transactional(readOnly = false)
    public PatchDeliveryRes changeDeliveryInfo(PatchDeliveryReq patchDeliveryReq){
        // 배송정보 확인
        Delivery delivery = deliveryRepository.findById(patchDeliveryReq.getDeliveryId()).orElseThrow(()-> new RuntimeException("not existing delivery"));

        // 이미 배송중인지 확인
        if(delivery.getStatus() == DeliveryStatus.ONTHEWAY)
            throw new RuntimeException("Its currently on the way, can't change delivery info");

        Delivery EditedDelivery = Delivery.editDelivery(delivery, patchDeliveryReq);
        deliveryRepository.save(EditedDelivery);

        return PatchDeliveryRes.toDto(EditedDelivery);


    }

}
