package java.com.microservice.inventory;

import jakarta.persistence.*;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String owner_id;

    @Column(nullable = false)
    private String name;

    @Column
    @Builder.Default
    private Long stock = 0L;

    @Column
    private String location;

    @Column(nullable = false)
    private String SKU;

    @CreationTimestamp
    Date createdAt;

    @UpdateTimestamp
    Date updatedAt;
}
