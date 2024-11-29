package com.example.demo.Database;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DB {
    List<User> USERS = new ArrayList<User>(List.of(
            new User("Ali", "admin", "root123", true),
            new User("Vali", "user", "123", false)
    ));

    List<Category> CATEGORIES = new ArrayList(List.of(
            new Category("Ichimliklar"),
            new Category("Oziq-Ovqat")
    ));

    List<Product> PRODUCTS = new ArrayList(List.of(
            new Product(2, "olma", 2000, "C:/Users/zarip/OneDrive/Desktop/Myeccomerce/demo/src/main/java/com/example/demo/files/olma.jpg"),
            new Product(1, "Suv", 300, "C:/Users/zarip/OneDrive/Desktop/Myeccomerce/demo/src/main/java/com/example/demo/files/suv.jpg")
    ));


    public static Map<Product, Integer> basket = new HashMap<>();

}
