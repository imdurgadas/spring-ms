package in.durgadas.aws.orderservice.controller;

import in.durgadas.aws.orderservice.dto.OrderRequest;
import in.durgadas.aws.orderservice.dto.OrderResponse;
import in.durgadas.aws.orderservice.model.Order;
import in.durgadas.aws.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(@RequestBody OrderRequest orderRequest){
        Order order = orderService.createOrder(orderRequest);
        return OrderResponse.builder().orderNumber(order.getOrderNumber()).id(order.getId()).build();
    }
}
