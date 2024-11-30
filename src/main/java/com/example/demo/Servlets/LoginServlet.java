package com.example.demo.Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        // Foydalanuvchini autentifikatsiya qilish (masalan, DB orqali tekshirish)
        if ("admin".equals(login) && "password".equals(password)) { // Bu faqat namuna uchun
            HttpSession session = request.getSession();
            session.setAttribute("user", login); // Sessiyaga foydalanuvchi ma'lumotini saqlash

            // Basketni tozalash
            session.removeAttribute("basket");

            // Asosiy sahifaga yo'naltirish
            response.sendRedirect("basket.jsp");
        } else {
            // Login muvaffaqiyatsiz bo'lsa
            response.sendRedirect("login.jsp?error=invalidCredentials");
        }
    }
}
