package com.microservice.inventory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProductIdempotent(Product product) {
        Optional<Product> productOptional = productRepository.findBySKU(product.getSKU());

        if (productOptional.isPresent()) {
            return productOptional.get();
        }

        return productRepository.save(product);
    }

    public void deleteProductIdempotent(String SKU) {
        Optional<Product> productOptional = productRepository.findBySKU(SKU);
        if (productOptional.isPresent()) {
            productRepository.delete(productOptional.get());
        }
    }


    public void checkProductNotExist(String SKU) {
        if (!productExists(SKU)) {
            throw new RuntimeException("Product with SKU " + SKU + " does not exist");
        }
    }

    public Product getProductOrThrow(String SKU) {
        return productRepository.findBySKU(SKU)
                .orElseThrow(() -> new RuntimeException("Product with SKU " + SKU + " already exists"));

    }

    public boolean productExists(String SKU) {
        return productRepository.findBySKU(SKU).isPresent();

    }

    // TODO check user exists method


    public Product updateProductAsOwner(ProductUpdate productUpdate, String SKU, String user_id) {
        // TODO call check user exists method

        Product product = getProductOrThrow(SKU);

        if (productUpdate.getStock() != null) {
            product.setStock(productUpdate.getStock());
        }

        if (productUpdate.getLocation() != null) {
            product.setLocation(productUpdate.getLocation());
        }

        return productRepository.save(product);

    }
}
