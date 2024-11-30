<%@ page import="static com.example.demo.Database.DB.basket" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.example.demo.entity.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Basket</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }

        .basket-container {
            margin-top: 30px;
            background: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .basket-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 1px solid #e9ecef;
            padding: 15px 0;
        }

        .basket-item:last-child {
            border-bottom: none;
        }

        .product-info img {
            border-radius: 5px;
            margin-right: 15px;
        }

        .product-info h5 {
            margin: 0;
            font-size: 1.1rem;
        }

        .product-info p {
            margin: 5px 0;
            font-size: 0.9rem;
        }

        .quantity-controls button {
            width: 35px;
            height: 35px;
            padding: 0;
            border-radius: 50%;
        }

        .total-section {
            background: #f1f3f5;
            padding: 15px;
            border-radius: 8px;
            font-size: 1.2rem;
            font-weight: bold;
            text-align: right;
        }

        .checkout-button, .back-button {
            text-align: right;
        }
    </style>
</head>
<body>
<div class="container">
    <%
        String checkoutMessage = request.getParameter("checkout");
        String errorMessage = request.getParameter("error");
        if ("success".equals(checkoutMessage)) {
    %>
    <div class="alert alert-success text-center">
        Checkout completed successfully! Your basket is now empty.
    </div>
    <%
    } else if ("loginRequired".equals(errorMessage)) {
    %>
    <div class="alert alert-danger text-center">
        Please log in to complete the checkout.
    </div>
    <%
        }
    %>

    <!-- Basket Content -->
    <div class="basket-container">
        <h1 class="mb-4 text-center">Your Basket</h1>
        <%
            if (basket == null || basket.isEmpty()) {
        %>
        <p class="text-center text-muted">Your basket is empty.</p>
        <%
        } else {
            double totalPrice = 0;
        %>
        <div class="basket-list">
            <%
                for (Map.Entry<Product, Integer> entry : basket.entrySet()) {
                    Product product = entry.getKey();
                    int quantity = entry.getValue();
                    double productTotal = product.getPrice() * quantity;
                    totalPrice += productTotal;
            %>
            <div class="basket-item">
                <div class="product-info d-flex align-items-center">
                    <img src="/file/<%= product.getId() %>" alt="<%= product.getName() %>"
                         style="width: 70px; height: 70px;">
                    <div>
                        <h5><%= product.getName() %></h5>
                        <p>Price: $<%= product.getPrice() %></p>
                        <p>Total: $<%= productTotal %></p>
                    </div>
                </div>
                <div class="d-flex align-items-center">
                    <div class="quantity-controls d-flex align-items-center">
                        <form action="/basketquantityServlet" method="get">
                            <input type="hidden" name="productId" value="<%= product.getId() %>">
                            <input type="hidden" name="action" value="decrement">
                            <button type="submit" class="btn btn-outline-secondary" <%= quantity == 1 ? "disabled" : "" %>>-</button>
                        </form>
                        <span class="mx-3"><%= quantity %></span>
                        <form action="/basketquantityServlet" method="get">
                            <input type="hidden" name="productId" value="<%= product.getId() %>">
                            <input type="hidden" name="action" value="increment">
                            <button type="submit" class="btn btn-outline-secondary">+</button>
                        </form>
                    </div>
                    <a href="/basket?productId=<%= product.getId() %>&action=remove" class="btn btn-danger ms-3">Remove</a>
                </div>
            </div>
            <%
                }
            %>
        </div>
        <div class="total-section mt-4">
            <p>Total Price: $<%= totalPrice %></p>
        </div>
        <div class="checkout-button mt-3">
            <form action="/checkout" method="post">
                <button type="submit" class="btn btn-primary">Checkout</button>
            </form>
        </div>
        <%
            }
        %>
        <div class="back-button mt-3">
            <a href="main.jsp" class="btn btn-secondary">Back</a>
        </div>
    </div>
</div>

<!-- Include Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
