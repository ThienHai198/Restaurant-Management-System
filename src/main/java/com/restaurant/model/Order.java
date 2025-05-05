package com.restaurant.model;

import java.time.LocalDateTime;

public class Order {
    private int orderId;
    private int tableId;
    private String items;
    private String status;
    private LocalDateTime prepTime;

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public int getTableId() { return tableId; }
    public void setTableId(int tableId) { this.tableId = tableId; }
    public String getItems() { return items; }
    public void setItems(String items) { this.items = items; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getPrepTime() { return prepTime; }
    public void setPrepTime(LocalDateTime prepTime) { this.prepTime = prepTime; }
}