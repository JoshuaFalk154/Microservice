package com.product_service.product_service;

import com.product_service.product_service.dto.ProductPost;
import com.product_service.product_service.entities.Product;
import com.product_service.product_service.event.Binding;
import com.product_service.product_service.event.EventProducer;
import com.product_service.product_service.event.MyEvent;
import com.product_service.product_service.exceptions.ResourceNotFoundException;
import com.product_service.product_service.repos.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final EventProducer eventProducer;

    public Product getProductBySKU(String SKU) {
        return productRepository.findBySKU(SKU)
                .orElseThrow(() -> new ResourceNotFoundException("Product with SKU " + SKU + " does not exist"));
    }

    public Product createProductAsOwner(ProductPost productPost, String userId) {
        // TODO
        // check if user exists
        return createProduct(productPost, userId);
    }


    public Product createProduct(ProductPost productPost, String userId) {

        productRepository.findBySKU(productPost.SKU())
                .ifPresent(s -> {
                    throw new RuntimeException("Product with SKU already exists");
                });

        Product product = Product.builder()
                .owner_id(userId)
                .name(productPost.name())
                .description(productPost.description())
                .price(productPost.price())
                .SKU(productPost.SKU())
                .build();

        Product product1 = productRepository.save(product);
        MyEvent event = MyEvent.builder()
                .key(product1.getId())
                .eventType(MyEvent.Type.CREATE)
                .data(product1)
                .build();
        eventProducer.sendMessage(event, Binding.PRODUCT_CREATED);

        return product1;
    }

    public void deleteProductAsOwner(String SKU, String userId) {
        // TODO
        // check if user exists
        // check if user owner

        deleteProduct(SKU);
    }

    public void deleteProduct(String SKU) {
        Product product = productRepository.findBySKU(SKU)
                .orElseThrow(() -> new ResourceNotFoundException("Product with SKU " + SKU + " does not exist"));

        MyEvent event = MyEvent.builder()
                .key(product.getId())
                .eventType(MyEvent.Type.DELETE)
                .data(product)
                .build();
        eventProducer.sendMessage(event, Binding.PRODUCT_DELETED);

        productRepository.delete(product);
    }
}
