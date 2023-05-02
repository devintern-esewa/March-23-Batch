package com.multipledatabase.inventory_db.entity;


import com.multipledatabase.inventory_db.enums.ProductEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private int qty;
    private int price;

    public Product(String name,String code,ProductEnum status,int qty,int price) {
    }


}
