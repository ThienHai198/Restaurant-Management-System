package com.restaurant.controller;

import com.restaurant.dao.OrderDAO;
import com.restaurant.dao.MenuDAO;
import com.restaurant.model.Order;
import com.restaurant.model.OrderItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/cashier/*")
public class CashierServlet extends HttpServlet {
    private OrderDAO orderDAO;
    private MenuDAO menuDAO;

    @Override
    public void init() {
        orderDAO = new OrderDAO();
        menuDAO = new MenuDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        try {
            if ("/billing".equals(path)) {
                String orderIdParam = request.getParameter("orderId");
                if (orderIdParam != null && orderIdParam.matches("\\d+")) {
                    int orderId = Integer.parseInt(orderIdParam);
                    Order order = orderDAO.getOrderId(orderId);
                    List<OrderItem> orderItems = orderDAO.getOrderItemsByOrderId(orderId);

                    if (order != null) {
                        request.setAttribute("order", order);
                        request.setAttribute("orderItems", orderItems);
                        request.setAttribute("menuItems", menuDAO.getAllMenuItems());
                        request.getRequestDispatcher("/jsp/cashier/billing.jsp").forward(request, response);
                    } else {
                        request.setAttribute("error", "Order not found.");
                        request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("error", "Invalid order ID.");
                    request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Unknown path: " + path);
                request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }
}
