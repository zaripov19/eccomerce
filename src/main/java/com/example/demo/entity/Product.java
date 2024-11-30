package com.example.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {


    private Integer id = idGen++;
    private int categoryId;
    private String name;
    private int price;
    private static Integer idGen = 1;
    private String productImage;

    public Product(int categoryId, String name, int price, String productImage) {
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
        this.productImage = productImage;
    }
}
