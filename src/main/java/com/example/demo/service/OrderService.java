package com.example.demo.service;
import com.example.demo.domain.Customer;
import com.example.demo.domain.OrderProduct;
import com.example.demo.domain.Orders;
import com.example.demo.domain.Product;
import com.example.demo.dto.order.post.PostOrderReq;
import com.example.demo.dto.order.post.PostOrderRes;
import com.example.demo.dto.order_product.OrderProductDTO;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderProductRepository;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrdersRepository ordersRepository;

    private final CustomerRepository customerRepository;

    private final OrderProductRepository orderProductRepository;

    private final ProductRepository productRepository;

    @Transactional(readOnly = false)
    public PostOrderRes setOrder(PostOrderReq postOrderReq, long customerId){
        // 고객 정보
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("not existing customer"));

        // 적어도 1개 이상 상품 주문
        if(postOrderReq.getOrderProductDtoList().size() < 1)
            throw new RuntimeException("At least one order should be placed");

        // 주문 번호 생성
        Orders orders = Orders.builder()
                .customer(customer)
                .build();

        ordersRepository.save(orders);

        // 주문에 상품을 담기
        List<OrderProductDTO> orderProductDtoList = postOrderReq.getOrderProductDtoList();
        List<String> productListNames = new ArrayList<>();

        orderProductDtoList.forEach(orderProductDTO -> {
            Product product = productRepository.findById(orderProductDTO.getProductId()).orElseThrow(() -> new RuntimeException("not existing product"));

            OrderProduct orderProduct = OrderProduct.builder()
                    .orders(orders)
                    .product(product)
                    .count(orderProductDTO.getCount())
                    .build();

            orderProductRepository.save(orderProduct);

            // response용 리스트
            productListNames.add(product.getProductName());
        });
        

        PostOrderRes postOrderRes = PostOrderRes.toDto(orders, productListNames);
        return postOrderRes;
    }
}
