package com.product_service.product_service.controller;

import com.product_service.product_service.ProductService;
import com.product_service.product_service.dto.ProductPost;
import com.product_service.product_service.dto.ProductResponse;
import com.product_service.product_service.entities.Product;
import com.product_service.product_service.repos.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService productService;

    @GetMapping("/product/{SKU}")
    public ProductResponse getProduct(@PathVariable String SKU) {
        Product product = productService.getProductBySKU(SKU);
        return new ProductResponse(product.getId(), product.getOwner_id(), product.getName(),
                product.getDescription(), product.getPrice(), product.getSKU());

    }

    @PostMapping("/product")
    public ProductResponse createProduct(@RequestHeader("id") String userId, @RequestBody ProductPost postProduct) {

        Product product = productService.createProduct(postProduct, userId);
        return new ProductResponse(product.getId(), product.getOwner_id(), product.getName(),
                product.getDescription(), product.getPrice(), product.getSKU());
    }

}
