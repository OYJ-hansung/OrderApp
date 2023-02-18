package com.example.demo.controller;

import com.example.demo.domain.Product;
import com.example.demo.dto.product.post.PostProductReq;
import com.example.demo.dto.product.post.PostProductRes;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    private PostProductRes addProduct(@RequestBody PostProductReq postProductReq){
        PostProductRes postProductRes = productService.addProduct(postProductReq);
        return postProductRes;
    };
}
