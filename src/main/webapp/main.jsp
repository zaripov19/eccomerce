<%@ page import="com.example.demo.entity.Product" %>
<%@ page import="com.example.demo.Database.DB" %>
<%@ page import="com.example.demo.entity.Category" %>
<%@ page import="static com.example.demo.Database.DB.basket" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .button-panel button {
            width: 100%;
            margin-bottom: 10px;
            border-radius: 10px;
            font-size: 16px;
        }

        .product-card {
            border: none;
            border-radius: 10px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s ease, box-shadow 0.2s ease;
        }

        .product-card:hover {
            transform: scale(1.05);
            box-shadow: 0px 6px 12px rgba(0, 0, 0, 0.15);
        }

        .product-img {
            max-width: 80px;
            margin: 10px auto;
        }

        .basket-btn {
            position: fixed;
            top: 20px;
            right: 20px;
            z-index: 1000;
        }
    </style>
</head>
<body>
<div class="container my-4">
    <div class="row">
        <!-- Left Sidebar with Buttons -->
        <div class="col-md-3">
            <form action="main.jsp" method="get" class="button-panel">
                <button
                        name="categoryId"
                        value="0"
                        class="btn <%= request.getParameter("categoryId") == null || "0".equals(request.getParameter("categoryId")) ? "btn-primary" : "btn-outline-primary" %>">
                    All
                </button>
                <%
                    for (Category category : DB.CATEGORIES) {
                %>
                <button
                        name="categoryId"
                        value="<%= category.getId() %>"
                        class="btn <%= String.valueOf(category.getId()).equals(request.getParameter("categoryId")) ? "btn-primary" : "btn-outline-primary" %>">
                    <%= category.getName() %>
                </button>
                <%
                    }
                %>
            </form>
        </div>

        <!-- Products Section -->
        <div class="col-md-9">
            <!-- Basket Button -->
            <!-- Basket and Login Buttons -->
            <div class="basket-btn d-flex align-items-center">
                <!-- Login Button -->
                <a class="btn btn-warning me-2" href="login.jsp">Login</a>
                <!-- Basket Button -->
                <form action="basket.jsp" method="get">
                    <input type="hidden" name="categoryId"
                           value="<%= request.getParameter("categoryId") != null ? request.getParameter("categoryId") : "0" %>">
                    <button class="btn btn-success btn-lg">Basket</button>
                </form>
            </div>


            <!-- Product List -->
            <div class="row g-4">
                <%
                    String categoryIdParam = request.getParameter("categoryId");
                    int categoryId = (categoryIdParam != null) ? Integer.parseInt(categoryIdParam) : 0;

                    for (Product product : DB.PRODUCTS) {
                        if (categoryId == 0 || product.getCategoryId() == categoryId) {
                %>
                <div class="col-md-6 col-lg-4">
                    <div class="card product-card h-100 d-flex flex-column justify-content-between">
                        <div class="text-center">
                            <h5 class="card-title"><%= product.getName() %>
                            </h5>
                            <img class="product-img" src="/file/<%=product.getId()%>" alt="">
                            <p class="text-muted"><strong>Price:</strong> $<%= product.getPrice() %>
                            </p>
                        </div>
                        <div class="text-center">
                            <%
                                boolean isProductInBasket = basket.keySet().stream()
                                        .anyMatch(p -> p.getId() == product.getId());
                                if (isProductInBasket) {
                            %>
                            <a class="btn btn-danger w-100"
                               href="/basket?productId=<%=product.getId()%>&categoryId=<%=categoryId%>&action=remove">Remove</a>
                            <%
                            } else {
                            %>
                            <a class="btn btn-primary w-100"
                               href="/basket?productId=<%=product.getId()%>&categoryId=<%=categoryId%>&action=select">Select</a>
                            <%
                                }
                            %>
                        </div>

                    </div>
                </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
    </div>
</div>

<!-- Include Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
