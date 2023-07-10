package in.durgadas.aws.productservice.service;

import in.durgadas.aws.productservice.dto.ProductRequest;
import in.durgadas.aws.productservice.model.Product;
import in.durgadas.aws.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(ProductRequest productRequest) {
        // Convert to Model
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice()).build();

        product = productRepository.save(product);
        log.info("Product saved with id: {} for product: {}", product.getId(), product.getName());
        return product;
    }
}
