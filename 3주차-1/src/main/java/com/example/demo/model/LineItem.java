package com.example.demo.model;

public class LineItem {

    private String id;
    private String productId;
    private int quantity;

    private String productName;
    private int totalPrice;
    private int unitPrice;

    public LineItem(String id, String productId, int quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return productName;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }
}
