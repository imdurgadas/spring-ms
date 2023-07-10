package in.durgadas.aws.productservice.controller;

import in.durgadas.aws.productservice.dto.ProductRequest;
import in.durgadas.aws.productservice.dto.ProductResponse;
import in.durgadas.aws.productservice.model.Product;
import in.durgadas.aws.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        Product product = productService.createProduct(productRequest);
        return getProductResponse(product);
    }

    private static ProductResponse getProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
