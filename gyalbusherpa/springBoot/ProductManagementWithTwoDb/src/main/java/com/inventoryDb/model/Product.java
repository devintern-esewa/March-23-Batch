package com.inventoryDb.model;

import com.Times;
import com.inventoryDb.enums.ProductEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@ToString
public class Product extends Times {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "don")
    @SequenceGenerator(name = "don", sequenceName = "product", allocationSize = 1)
    private long id;
    private String name;
    private String code;
    @Enumerated(EnumType.STRING)
    private ProductEnum productStatus;
    private double quantity;
    private double price;
}
