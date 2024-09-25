package com.microservice.order;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@RequiredArgsConstructor
@Table(name = "order-table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private Long user_id;

    @Column(nullable = false)
    private Long product_id;

    @Column(nullable = false)
    private Long quantity;

    //@Column(nullable = false)
    private Double totalPrice;

    @CreationTimestamp
    Date createdAt;

    @UpdateTimestamp
    Date updatedAt;
}
