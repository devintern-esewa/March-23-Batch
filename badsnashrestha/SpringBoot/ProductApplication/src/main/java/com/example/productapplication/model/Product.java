package com.example.productapplication.model;

import jakarta.persistence.*;
import lombok.*;

@Getter //to generate getter method for the class field
@Setter //to generate setter method for  the class field
@Entity
// indicates that the  annotated class is a JPA entity and should be persisted to a db using JPA. Creates an corresponding db table based on class field
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_info") //to customize db table name, should be snake_case
public class Product {
    @Id //to declare an entity as a primary key

    /*
   sequenceName: allows you to specify the name of the sequence that will be used to generate the primary key values
    name: used as reference to the generator when it is associated with @GeneratedValue
    allocationSize: to increment the value of primary key
     */
    @SequenceGenerator(sequenceName = "product_sequence",
            name = "product_name",
            allocationSize = 1)

    @GeneratedValue(strategy = GenerationType.AUTO,
            generator = "product_name")


    //to define name of the column
    //should be in snake_case
    @Column(name = "product_id")
    //should be camelCase
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    private double price;


}
