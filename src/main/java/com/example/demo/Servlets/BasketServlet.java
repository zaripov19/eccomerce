package com.example.demo.Servlets;

import com.example.demo.Database.DB;
import com.example.demo.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/basket")
public class BasketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("productId");
        String categoryIdParam = req.getParameter("categoryId");
        Integer productId = Integer.parseInt(id);
        Optional<Product> first = DB.PRODUCTS.stream().filter(item -> item.getId().equals(productId)).findFirst();
        boolean isProductInBasket = DB.basket.keySet().stream()
                .anyMatch(p -> p.getId().equals(productId));

        if (isProductInBasket) {
            DB.basket.remove(first.get().getId());
        } else {
            DB.basket.put(first.get(), productId);
        }

        resp.sendRedirect("/main.jsp?categoryId=" + (categoryIdParam != null ? categoryIdParam : "0"));
    }
}
