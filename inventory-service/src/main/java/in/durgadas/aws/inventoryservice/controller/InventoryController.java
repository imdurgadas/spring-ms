package in.durgadas.aws.inventoryservice.controller;

import in.durgadas.aws.inventoryservice.dto.InventoryResponse;
import in.durgadas.aws.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> getInventoryBySkus(@RequestParam List<String> skus){
        return inventoryService.getInventoryBySkus(skus);
    }
}
