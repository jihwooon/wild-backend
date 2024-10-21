package com.example.demo.model;

// Aggregate의 Root Entity가 아님
public class LineItem {

    private LineItemId id;
    private ProductId productId;
    private Money unitPrice;
    private int quantity;
    private Money totalPrice;

    // 외부에서 사용 불가.
    public void addQuantity(int quantity) {
        this.quantity += quantity;
        this.totalPrice = unitPrice.multiply(quantity);
    }

    public Money getTotalPrice() {
        return totalPrice;
    }
}
