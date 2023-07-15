package in.durgadas.aws.inventoryservice.service;

import in.durgadas.aws.inventoryservice.dto.InventoryResponse;
import in.durgadas.aws.inventoryservice.model.Inventory;
import in.durgadas.aws.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;


    @Transactional(readOnly = true)
    public boolean isInStock(String sku) {
        return inventoryRepository.findBySku(sku).isPresent();
    }

    @Transactional(readOnly = true)
    public List<InventoryResponse> getInventoryBySkus(List<String> skus) {
        List<Inventory> inventories = inventoryRepository.findBySkuIn(skus);
        return inventories.stream().map(i -> InventoryResponse.builder().sku(i.getSku()).quantity(i.getQuantity()).build()).toList();

    }
}
