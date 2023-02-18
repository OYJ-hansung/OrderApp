package com.example.demo.dto.product.post;

import com.example.demo.domain.Product;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostProductReq {

    private String productName;

    private int productPrice;

    public static Product toEntity(PostProductReq postProductReq){
        Product product = Product.builder()
                .productName(postProductReq.getProductName())
                .productPrice(postProductReq.getProductPrice())
                .build();
        return product;
    }
}
