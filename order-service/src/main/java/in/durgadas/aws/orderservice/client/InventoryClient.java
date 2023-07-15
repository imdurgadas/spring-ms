package in.durgadas.aws.orderservice.client;

import in.durgadas.aws.orderservice.client.dto.InventoryResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface InventoryClient {

    @GetExchange("/inventory")
    List<InventoryResponse> findInventoryBySkus(@RequestParam List<String> skus);
}
