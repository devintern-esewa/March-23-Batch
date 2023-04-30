package com.pratice.product.model;

import com.pratice.product.enums.ProductEnum;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product")
    @SequenceGenerator(name = "product", allocationSize = 1, sequenceName = "product_id")
    private Long id;
    private String name;
    private int quantity;

    @Enumerated(EnumType.STRING)
    private ProductEnum status;


}
