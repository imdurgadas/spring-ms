package in.durgadas.aws.orderservice.service;

import in.durgadas.aws.orderservice.dto.OrderLineItemsDto;
import in.durgadas.aws.orderservice.dto.OrderRequest;
import in.durgadas.aws.orderservice.model.Order;
import in.durgadas.aws.orderservice.model.OrderLineItems;
import in.durgadas.aws.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    public Order createOrder(OrderRequest orderRequest){
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItems(getOrderLineItemsFromDtoList(orderRequest.getOrderLineItemsDtoList()))
                .build();

        return orderRepository.save(order);
    }

    private List<OrderLineItems> getOrderLineItemsFromDtoList(List<OrderLineItemsDto> orderLineItemsDtoList) {
        return orderLineItemsDtoList.stream().map(this::mapToDto).toList();
    }

    private OrderLineItems mapToDto(OrderLineItemsDto o) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSku(o.getSku());
        orderLineItems.setPrice(o.getPrice());
        orderLineItems.setQuantity(o.getQuantity());
        return orderLineItems;
    }
}
