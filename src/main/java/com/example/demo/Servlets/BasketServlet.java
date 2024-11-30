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

import static com.example.demo.Database.DB.basket;

@WebServlet("/basket")
public class BasketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Parametrlarni olish
        String id = req.getParameter("productId");
        String categoryIdParam = req.getParameter("categoryId");
        String action = req.getParameter("action"); // Actionni tekshirish uchun yangi parametr

        if (id == null || action == null) {
            resp.sendRedirect("/main.jsp?categoryId=" + (categoryIdParam != null ? categoryIdParam : "0"));
            return;
        }

        // Mahsulotni olish
        int productId = Integer.parseInt(id);
        Optional<Product> productOptional = DB.PRODUCTS.stream()
                .filter(product -> product.getId() == productId)
                .findFirst();

        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            if ("remove".equals(action)) {
                // Mahsulotni basketdan o'chirish
                basket.remove(product);
            } else if ("select".equals(action)) {
                // Agar mahsulot oldin basketda bo'lmagan bo'lsa, qo'shish
                if (!basket.containsKey(product)) {
                    basket.put(product, 1); // Miqdorini 1 deb belgilash
                }
            }
        }

        // Asosiy sahifaga qaytarish
        resp.sendRedirect("/main.jsp?categoryId=" + (categoryIdParam != null ? categoryIdParam : "0"));
    }
}
