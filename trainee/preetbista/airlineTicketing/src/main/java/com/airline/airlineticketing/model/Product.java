package com.airline.airlineticketing.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    private Long id;
    private String name;
    private String code;
    private Long price;
    private Long quantity;

    public Product(Long id, String name, String code, Long price, Long quantity) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.price = price;
        this.quantity = quantity;
    }

    public Product() {
    }
}
