package com.example.demo.model;

import java.util.List;

// root Entity (Cart Aggregate)
public class Cart {

    private List<LineItem> lineItems;
    private Money totalPrice;

    public void addProduct(ProductId productId, int quantity) {
        LineItem lineItem = findLineItem(productId);
        if (lineItem != null) {
            lineItem.addQuantity(quantity);
            calculateTotalPrice();
            return;
        }
        lineItem = new LineItem();
        lineItems.add(lineItem);
        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        this.totalPrice = lineItems
                .stream()
                .map(LineItem::getTotalPrice)
                .reduce(Money.ZERO, Money::plus);
    }

    private LineItem findLineItem(ProductId productId) {
        return null;
    }
}
