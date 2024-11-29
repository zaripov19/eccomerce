package com.example.demo.Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.demo.Database.DB.basket;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Savatdagi barcha mahsulotlarni olib tashlash
        basket.clear();

        // Checkout jarayonidan keyin asosiy sahifaga yoki buyurtma sahifasiga yo'naltirish
        resp.sendRedirect("/main.jsp");
    }
}
