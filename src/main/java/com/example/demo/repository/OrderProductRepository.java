package com.example.demo.repository;

import com.example.demo.domain.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Long> {
    List<OrderProduct> findByOrdersId(long ordersId);
}
