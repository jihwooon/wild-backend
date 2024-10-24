package com.example.demo.model;

public class LineItem {

    private String id;
    private String productId;
    private int quantity;

    private String productName;
    private int unitPrice;

    public LineItem(String id, String productId, int quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public LineItem(String productId, int quantity) {
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

    public int getUnitPrice() {
        return unitPrice;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public int getTotalPrice() {
        return this.unitPrice * this.quantity;
    }

    public void setProduct(Product product) {
        this.productName = product.getName();
        this.unitPrice = product.getPrice();
    }
}
