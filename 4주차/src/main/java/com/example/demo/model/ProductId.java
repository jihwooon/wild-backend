package com.example.demo.model;

public record ProductId(
        String productId
) {

    public ProductId generate() {
        return new ProductId("");
    }
}
