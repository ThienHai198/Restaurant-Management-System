package com.restaurant.controller;

import com.restaurant.dao.MenuDAO;
import com.restaurant.model.MenuItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/manager/*")
public class ManagerServlet extends HttpServlet {
    private MenuDAO menuDAO;

    @Override
    public void init() {
        menuDAO = new MenuDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        try {
            if (path == null || path.equals("/menuManagement")) {
                List<MenuItem> menuItems = menuDAO.getAllMenuItems();
                request.setAttribute("menuItems", menuItems);
                request.getRequestDispatcher("/jsp/manager/menuManagement.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        try {
            if (path.equals("/addMenuItem")) {
                MenuItem item = new MenuItem();
                item.setName(request.getParameter("name"));
                item.setPrice(Double.parseDouble(request.getParameter("price")));
                item.setCategory(request.getParameter("category"));
                menuDAO.addMenuItem(item);
                response.sendRedirect(request.getContextPath() + "/manager/menuManagement");
            }
        } catch (SQLException e) {
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }
}