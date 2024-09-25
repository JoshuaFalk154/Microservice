package com.microservice.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryServiceClient inventoryServiceClient;
    private final  ProductServiceClient productServiceClient;


    public Order placeOrder(OrderPlaceDTO order) {
        // TODO
        // check user exists

        Double productPrice = productServiceClient.getProductFromProductService(order.SKU()).price();
        Long stock = inventoryServiceClient.getProductFromInventoryService(order.SKU()).stock();

        if (stock < order.quantity()) {
            throw new RuntimeException("The product with SKU " + order.SKU() + " does not have " + order.quantity() + " quantities left. Please try again later");
        }
        Double totalPrice = productPrice * order.quantity();

        Order orderToSave = Order.builder()
                .user_id(order.user_id())
                .productSKU(order.SKU())
                .quantity(order.quantity())
                .totalPrice(totalPrice)
                .build();

        return orderRepository.save(orderToSave);
    }

    public OrderResponse orderToOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .user_id(order.getUser_id())
                .productSku(order.getProductSKU())
                .quantity(order.getQuantity())
                .totalPrice(order.getTotalPrice())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}
