package in.durgadas.aws.inventoryservice.service;

import in.durgadas.aws.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(String sku){
        return inventoryRepository.findBySku(sku).isPresent();
    }
}
