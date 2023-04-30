package com.inventoryDb.model;

import com.inventoryDb.enums.ProductEnum;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@ToString
public class Product {

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

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date lastModifiedDate;
}
