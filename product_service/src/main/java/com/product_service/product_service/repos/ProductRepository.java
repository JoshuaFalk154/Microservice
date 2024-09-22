package com.product_service.product_service.repos;

import com.product_service.product_service.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySKU(String SKU);
    void deleteBySKU(String SKU);
}
