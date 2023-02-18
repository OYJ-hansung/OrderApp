package com.example.demo.dto.product.post;

import com.example.demo.domain.Product;
import lombok.*;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostProductRes {
    private long id;

    private String productName;

    private int productPrice;

    public static PostProductRes toDto(Product product){
        PostProductRes postProductRes = PostProductRes.builder()
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .build();
        return postProductRes;
    }
}
