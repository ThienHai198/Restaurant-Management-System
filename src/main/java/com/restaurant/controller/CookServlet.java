package com.restaurant.controller;

import com.restaurant.dao.OrderDAO;
import com.restaurant.model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/cook/*")
public class CookServlet extends HttpServlet {
    private OrderDAO orderDAO;

    @Override
    public void init() {
        orderDAO = new OrderDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        try {
            if (path == null || path.equals("/cookDashboard")) {
                List<Order> orders = orderDAO.getPendingOrders();
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("/jsp/cook/cookDashboard.jsp").forward(request, response);
            } else if (path.equals("/schedule")) {
                int orderId = Integer.parseInt(request.getParameter("orderId"));
                Order order = orderDAO.getOrderById(orderId);
                request.setAttribute("order", order);
                request.getRequestDispatcher("/jsp/cook/schedule.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        try {
            if (path.equals("/setPrepTime")) {
                int orderId = Integer.parseInt(request.getParameter("orderId"));
                String prepTimeStr = request.getParameter("prepTime");
                LocalDateTime prepTime = LocalDateTime.parse(prepTimeStr);
                orderDAO.updateOrderPrepTime(orderId, prepTime);
                response.sendRedirect(request.getContextPath() + "/cook/cookDashboard");
            }
        } catch (SQLException e) {
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }
}