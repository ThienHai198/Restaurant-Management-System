package com.restaurant.model;

import java.time.LocalDateTime;

public class Order {
    private int orderId;
    private int tableId;
    private int customerId;
    private String status;
    private LocalDateTime prepTime;

    public int getOrderId() { 
        return orderId; 
    }
    public void setOrderId(int orderId) { 
        this.orderId = orderId; 
    }

    public int getTableId() { 
        return tableId; 
    }
    public void setTableId(int tableId) { 
        this.tableId = tableId; 
    }

    public int getCustomerId() {
         return customerId;
    }
    public void setCustomerId(int customerId) { 
        this.customerId = customerId; 
    }

    public String getStatus() { 
        return status; 
    }
    public void setStatus(String status) { 
        this.status = status; 
    }

    public LocalDateTime getPrepTime() { 
        return prepTime; 
    }
    public void setPrepTime(LocalDateTime prepTime) { 
        this.prepTime = prepTime; 
    }
}
