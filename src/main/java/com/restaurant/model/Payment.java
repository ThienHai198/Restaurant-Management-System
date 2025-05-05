package com.restaurant.model;

public class Payment {
    private int paymentId;
    private int orderId;
    private double amount;
    private String cardDetails;
    private String status;

    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getCardDetails() { return cardDetails; }
    public void setCardDetails(String cardDetails) { this.cardDetails = cardDetails; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}