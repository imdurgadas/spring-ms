package in.durgadas.aws.inventoryservice;

import in.durgadas.aws.inventoryservice.model.Inventory;
import in.durgadas.aws.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory iphone13 = Inventory.builder().sku("iphone13").quantity(100).build();
			Inventory iphone13Pro = Inventory.builder().sku("iphone13pro").quantity(0).build();
			inventoryRepository.save(iphone13);
			inventoryRepository.save(iphone13Pro);
		};
	}
}
