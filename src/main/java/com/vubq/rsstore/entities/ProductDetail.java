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
@Table(name = "product_details")
public class ProductDetail {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private String id;

    @Column(name = "code")
    private String code;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "size_id", nullable = false)
    private Size size;

    @ManyToOne(optional = false)
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;

    @ManyToOne(optional = false)
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;

    @Column(name = "created_at")
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EStatus status;
}
