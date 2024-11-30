package com.example.demo.Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Foydalanuvchi sessiyasini olish
        HttpSession session = request.getSession(false);

        // Sessiyani tekshirish (login qilganmi yoki yo'q)
        if (session == null || session.getAttribute("user") == null) {
            // Agar login qilinmagan bo'lsa, login sahifasiga yo'naltirish
            response.sendRedirect("login.jsp?error=loginRequired");
        } else {
            // Agar login qilingan bo'lsa:
            // Basketni tozalash
            session.removeAttribute("basket");

            // Muvaffaqiyatli checkout sahifasiga yo'naltirish
            response.sendRedirect("basket.jsp?checkout=success");
        }
    }
}
