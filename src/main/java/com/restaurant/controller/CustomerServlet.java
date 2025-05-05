package com.restaurant.controller;

import com.restaurant.dao.*;
import com.restaurant.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/customer/*")
public class CustomerServlet extends HttpServlet {
    private TableDAO tableDAO;
    private MenuDAO menuDAO;
    private OrderDAO orderDAO;
    private PaymentDAO paymentDAO;

    @Override
    public void init() {
        tableDAO = new TableDAO();
        menuDAO = new MenuDAO();
        orderDAO = new OrderDAO();
        paymentDAO = new PaymentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        try {
            if (path == null || path.equals("/order")) {
                List<Table> tables = tableDAO.getAvailableTables();
                List<MenuItem> menuItems = menuDAO.getAllMenuItems();
                request.setAttribute("tables", tables);
                request.setAttribute("menuItems", menuItems);
                request.getRequestDispatcher("/jsp/customer/order.jsp").forward(request, response);
            } else if (path.equals("/menu")) {
                List<MenuItem> menuItems = menuDAO.getAllMenuItems();
                request.setAttribute("menuItems", menuItems);
                request.getRequestDispatcher("/jsp/customer/menu.jsp").forward(request, response);
            } else if (path.equals("/orderStatus")) {
                int orderId = Integer.parseInt(request.getParameter("orderId"));
                Order order = orderDAO.getOrderById(orderId);
                request.setAttribute("order", order);
                request.getRequestDispatcher("/jsp/customer/orderStatus.jsp").forward(request, response);
            } else if (path.equals("/payment")) {
                int orderId = Integer.parseInt(request.getParameter("orderId"));
                Order order = orderDAO.getOrderById(orderId);
                request.setAttribute("order", order);
                request.setAttribute("menuItems", menuDAO.getAllMenuItems());
                request.getRequestDispatcher("/jsp/customer/payment.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        try {
            if (path.equals("/placeOrder")) {
                int tableId = Integer.parseInt(request.getParameter("tableId"));
                String[] itemIds = request.getParameterValues("items");
                tableDAO.occupyTable(tableId);
                Order order = new Order();
                order.setTableId(tableId);
                order.setItems(String.join(",", itemIds));
                order.setStatus("PENDING");
                orderDAO.saveOrder(order);
                request.getSession().setAttribute("orderId", order.getOrderId());
                response.sendRedirect(request.getContextPath() + "/customer/orderStatus?orderId=" + order.getOrderId());
            } else if (path.equals("/makePayment")) {
                int orderId = Integer.parseInt(request.getParameter("orderId"));
                double amount = Double.parseDouble(request.getParameter("amount"));
                String cardDetails = request.getParameter("cardDetails");
                Payment payment = new Payment();
                payment.setOrderId(orderId);
                payment.setAmount(amount);
                payment.setCardDetails(cardDetails);
                payment.setStatus("PENDING");
                paymentDAO.savePayment(payment);
                paymentDAO.updatePaymentStatus(payment.getPaymentId(), "COMPLETED");
                request.setAttribute("paymentSuccess", true);
                request.setAttribute("order", orderDAO.getOrderById(orderId));
                request.setAttribute("menuItems", menuDAO.getAllMenuItems());
                request.getRequestDispatcher("/jsp/customer/payment.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }
}