package in.durgadas.aws.orderservice.service;

import in.durgadas.aws.orderservice.client.InventoryClient;
import in.durgadas.aws.orderservice.client.dto.InventoryResponse;
import in.durgadas.aws.orderservice.dto.OrderLineItemsDto;
import in.durgadas.aws.orderservice.dto.OrderRequest;
import in.durgadas.aws.orderservice.model.Order;
import in.durgadas.aws.orderservice.model.OrderLineItems;
import in.durgadas.aws.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public Order createOrder(OrderRequest orderRequest){
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItems(getOrderLineItemsFromDtoList(orderRequest.getOrderLineItemsDtoList()))
                .build();

        List<String> skus = order.getOrderLineItems().stream()
                .map(OrderLineItems::getSku).toList();

        //TODO Call Inventory Service and check if in stock
        List<InventoryResponse> inventoryResult = inventoryClient.findInventoryBySkus(skus);
        log.info("Inventory Results: {}" , inventoryResult);
        boolean allProductsInStock = inventoryResult.stream().allMatch(res -> res.getQuantity() > 0);

        if (allProductsInStock){
            return orderRepository.save(order);
        }else {
            throw new IllegalArgumentException("Product is not in stock, try again later");
        }
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
