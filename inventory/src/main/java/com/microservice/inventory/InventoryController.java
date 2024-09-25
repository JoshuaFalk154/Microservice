package com.microservice.inventory;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final ProductService productService;
    private final Mapper mapper;

    @GetMapping("/product/{SKU}")
    public ProductResponse test(String SKU) {
        Product product = productService.getProductOrThrow(SKU);

        return mapper.productToProductResponse(product);
    }

    @PutMapping("/product/{SKU}")
    public ProductResponse updateProduct(@RequestBody ProductUpdate productUpdate, @PathVariable String SKU, @RequestHeader("id") String user_id) {
        Product product = productService.updateProductAsOwner(productUpdate, SKU, user_id);

        return mapper.productToProductResponse(product);

    }


}
