package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.dto.product.post.PostProductReq;
import com.example.demo.dto.product.post.PostProductRes;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public PostProductRes addProduct(PostProductReq postProductReq){
        Product product = PostProductReq.toEntity(postProductReq);
        productRepository.save(product);

        return PostProductRes.toDto(product);
    }
}
