package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    private int id = idGen++;
    private String name;
    private static Integer idGen = 1;

    public Category(String name) {
        this.name = name;
    }
}
