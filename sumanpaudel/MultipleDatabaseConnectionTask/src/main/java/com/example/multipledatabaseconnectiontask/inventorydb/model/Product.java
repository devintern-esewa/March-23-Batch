package com.example.multipledatabaseconnectiontask.inventorydb.model;

import com.example.multipledatabaseconnectiontask.inventorydb.encdec.AttributeEncryptor;
import com.example.multipledatabaseconnectiontask.inventorydb.statusenum.ProductStatusEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @SequenceGenerator(name = "pro", sequenceName = "pro", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pro")
    private long id;
    @Column(name = "product_name")
    private String name;
    @Column(name = "product_code")
    @Convert(converter = AttributeEncryptor.class)
    private String code;
    @Enumerated(EnumType.STRING)
    private ProductStatusEnum status;
    @Column(name = "quantity")
    private double quantity;
    @Column(name = "price")
    private double price;
}
