package com.vubq.rsstore.entities;

import com.vubq.rsstore.enums.EStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Builder
@Entity
@Table(name = "products")
public class Product {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private Double price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(optional = false)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Column(name = "created_at")
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EStatus status;
}
