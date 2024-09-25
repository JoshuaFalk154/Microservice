package com.microservice.order;

import com.microservice.order.dto.OrderEvent;
import com.microservice.order.event.Binding;
import com.microservice.order.event.EventProducer;
import com.microservice.order.event.EventProducerService;
import com.microservice.order.event.MyEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryServiceClient inventoryServiceClient;
    private final ProductServiceClient productServiceClient;
    private final UserService userService;
    private final EventProducerService eventProducerService;

    public Order placeOrder(OrderPlaceDTO order) {
        userService.checkUserExistence(order.user_id());

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

        Order savedOrder = orderRepository.save(orderToSave);
        eventProducerService.sendMessageOrderPlaced(savedOrder);

        return savedOrder;
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
