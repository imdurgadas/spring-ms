package in.durgadas.aws.orderservice.config;

import in.durgadas.aws.orderservice.client.InventoryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    public static final String INVENTORY = "http://localhost:8082";

    @Bean
    public InventoryClient inventoryClient() {
        WebClient client = WebClient.builder().baseUrl(INVENTORY).build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
        return factory.createClient(InventoryClient.class);
    }
}
