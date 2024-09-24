package com.product_service.product_service.service;

import com.product_service.product_service.dto.ProductEvent;
import com.product_service.product_service.dto.ProductPost;
import com.product_service.product_service.entities.Product;
import com.product_service.product_service.exceptions.ResourceNotFoundException;
import com.product_service.product_service.repos.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;
    private final EventProducerService eventProducerService;


    public Product createProductAsOwner(ProductPost productPost, String userId) {
        userService.checkUserExistence(userId);

        return createProduct(productPost, userId);
    }


    @Transactional
    public Product createProduct(ProductPost productPost, String userId) {
        checkProductAlreadyExists(productPost.SKU());

        Product product = Product.builder()
                .owner_id(userId)
                .name(productPost.name())
                .description(productPost.description())
                .price(productPost.price())
                .SKU(productPost.SKU())
                .build();

        Product product1 = productRepository.save(product);
        eventProducerService.sendMessageProductCreated(product1);

        return product1;
    }

    public void deleteProductAsOwner(String SKU, String userId) {
        userService.checkUserExistence(userId);
        checkUserIsOwner(SKU, userId);

        deleteProduct(SKU);
    }

    @Transactional
    public void deleteProduct(String SKU) {
        Product product = getProductBySKU(SKU);
        eventProducerService.sendMessageProductDeleted(product);
        productRepository.delete(product);
    }

    public void checkUserIsOwner(String SKU, String userId) {
        checkUserIsOwner(getProductBySKU(SKU), userId);
    }

    public boolean userIsOwner(Product product, String userId) {
        return userId.equals(product.getOwner_id());
    }

    public void checkUserIsOwner(Product product, String userId) {
        if (!userIsOwner(product, userId)) {
            throw new RuntimeException("User with id " + userId + " is not owner of Product with SKU " + product.getSKU());
        }
    }

    public Product getProductBySKU(String SKU) {
        return productRepository.findBySKU(SKU)
                .orElseThrow(() -> new ResourceNotFoundException("Product with SKU " + SKU + " does not exist"));
    }

    public void checkProductAlreadyExists(String SKU) {
        productRepository.findBySKU(SKU)
                .ifPresent(s -> {
                    throw new RuntimeException("Product with SKU already exists");
                });
    }


}
