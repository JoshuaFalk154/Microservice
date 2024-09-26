package com.product_service.product_service.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product-table")
public class Product {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    Long id;

    @Column(nullable = false)
    String owner_id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    Double price;

    @Column(unique = true)
    String SKU;

    @CreationTimestamp
    Date createdAt;

    @UpdateTimestamp
    Date updatedAt;
}
