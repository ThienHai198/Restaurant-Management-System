package com.restaurant.controller;

import com.restaurant.dao.OrderDAO;
import com.restaurant.dao.MenuDAO;
import com.restaurant.model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

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
            if (path.equals("/billing")) {
                int orderId = Integer.parseInt(request.getParameter("orderId"));
                Order order = orderDAO.getOrderById(orderId);
                request.setAttribute("order", order);
                request.setAttribute("menuItems", menuDAO.getAllMenuItems());
                request.getRequestDispatcher("/jsp/cashier/billing.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }
}