package com.example.demo.controller;

import com.example.demo.dto.order.post.PostOrderReq;
import com.example.demo.dto.order.post.PostOrderRes;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/set")
    public PostOrderRes setOrder(@RequestBody PostOrderReq postOrderReq, @RequestHeader long customerId){
        PostOrderRes postOrderRes = orderService.setOrder(postOrderReq,customerId);
        return postOrderRes;
    }

}
