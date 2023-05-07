package com.multipledatabase.inventorydb.entity;


import com.multipledatabase.inventorydb.enums.ProductEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String code;
    @Enumerated(EnumType.STRING)
    private ProductEnum status;
    private double qty;
    private double price;
    @CreationTimestamp
    private LocalDateTime created;


    public Product(int id, String name, String code, ProductEnum status, int qty, int price) {

        this.id=id;
        this.name=name;
        this.code=code;
        this.status=status;
        this.qty=qty;
        this.price=price;
    }
}
