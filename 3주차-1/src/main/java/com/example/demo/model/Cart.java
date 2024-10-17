package com.example.demo.model;

import java.util.Collections;
import java.util.List;

public class Cart {

    private List<LineItem> lineItem;
    private int totalPrice;

    public Cart(List<LineItem> lineItem, int totalPrice) {
        this.lineItem = lineItem;
        this.totalPrice = totalPrice;
    }

    public List<LineItem> getLineItem() {
        return Collections.unmodifiableList(lineItem);
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
