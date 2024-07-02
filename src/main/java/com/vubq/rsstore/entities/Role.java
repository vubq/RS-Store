package com.vubq.rsstore.entities;

import com.vubq.rsstore.enums.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private ERole name;
}
