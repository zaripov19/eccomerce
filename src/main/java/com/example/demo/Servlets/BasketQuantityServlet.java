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

@WebServlet("/basketquantityServlet")
public class BasketQuantityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // So'rovdan parametrlarni olish
        String productIdParam = req.getParameter("productId");
        String action = req.getParameter("action");
        String categoryIdParam = req.getParameter("categoryId");

        // Xatoliklarni tekshirish: action va productId parametrlarining mavjudligini va to'g'ri ekanligini tekshirish
        if (productIdParam == null || action == null || (!"increment".equals(action) && !"decrement".equals(action))) {
            resp.sendRedirect("basket.jsp");
            return;
        }

        // productId-ni butun son (integer) ga o'zgartirish
        int productId = Integer.parseInt(productIdParam);

        // Mahsulotni ro'yxatdan topish
        Optional<Product> productOptional = DB.PRODUCTS.stream()
                .filter(product -> product.getId() == productId)
                .findFirst();

        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            // action qiymatiga qarab, mahsulot miqdorini oshirish yoki kamaytirish
            if ("decrement".equals(action)) {
                int currentQuantity = basket.getOrDefault(product, 0);

                if (currentQuantity > 1) {
                    // Miqdorni kamaytirish
                    basket.put(product, currentQuantity - 1);
                } else {
                    // Agar miqdor 0 ga teng bo'lsa, mahsulotni savatdan olib tashlash
                    basket.remove(product);
                }
            } else if ("increment".equals(action)) {
                // Miqdorni oshirish
                basket.put(product, basket.getOrDefault(product, 0) + 1);
            }
        }

        // Basket sahifasiga qaytish, categoryId ni saqlab qolish
        resp.sendRedirect("basket.jsp?categoryId=" + (categoryIdParam != null ? categoryIdParam : "0"));
    }
}
