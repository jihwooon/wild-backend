package com.example.demo.model;

import java.util.Collections;
import java.util.List;

public class Cart {

    private List<LineItem> lineItems;

    public Cart(List<LineItem> lineItem) {
        this.lineItems = lineItem;
    }

    public List<LineItem> getLineItem() {
        return Collections.unmodifiableList(lineItems);
    }

    public int getTotalPrice() {
        return lineItems.stream()
                .mapToInt(LineItem::getTotalPrice)
                .sum();
    }
}
