package com.example.demo.controller;

import com.example.demo.dto.delivery.patch.PatchDeliveryReq;
import com.example.demo.dto.delivery.patch.PatchDeliveryRes;
import com.example.demo.dto.delivery.post.PostDeliveryReq;
import com.example.demo.dto.delivery.post.PostDeliveryRes;
import com.example.demo.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/delivery")
@RestController
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping("/set")
    public PostDeliveryRes setDelivery(@RequestBody PostDeliveryReq postDeliveryReq, @RequestHeader Long customerId){
        PostDeliveryRes postDeliveryRes = deliveryService.setDelivery(postDeliveryReq, customerId);
        return postDeliveryRes;
    }

    @PatchMapping("/start/{deliveryId}")
    public String startDelivery(@PathVariable long deliveryId){
        deliveryService.startDelivery(deliveryId);
        return "배송시작 처리가 되었습니다.";
    }

    @PatchMapping("/change")
    public PatchDeliveryRes changeDeliveryInfo(@RequestBody PatchDeliveryReq patchDeliveryReq){
        PatchDeliveryRes patchDeliveryRes = deliveryService.changeDeliveryInfo(patchDeliveryReq);
        return patchDeliveryRes;
    }
}
