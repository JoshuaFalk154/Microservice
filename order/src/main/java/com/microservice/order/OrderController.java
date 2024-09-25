package com.microservice.order;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponse placeOrder(@RequestBody OrderPlaceDTO order) {
        Order orderResponse = orderService.placeOrder(order);

        return orderService.orderToOrderResponse(orderResponse);
    }


}
