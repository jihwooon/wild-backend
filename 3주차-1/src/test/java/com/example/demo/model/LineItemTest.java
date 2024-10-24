package com.example.demo.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class LineItemTest {

    @Test
    void addQuantity() {
        String productId = "productId";
        int originQuantity = 2;
        int quantity = 3;

        LineItem lineItem = new LineItem(productId, originQuantity);

        lineItem.addQuantity(quantity);

        assertThat(lineItem.getQuantity()).isEqualTo(originQuantity + quantity);
    }

    @Test
    void setProductAndGetTotalPrice() {
        int quantity = 2;
        Product product = new Product("product_1", "product #1", 5000);
        LineItem lineItem = new LineItem(product.getId(), quantity);

        lineItem.setProduct(product);

        assertThat(lineItem.getProductName()).isEqualTo(product.getName());
        assertThat(lineItem.getTotalPrice()).isEqualTo(product.getPrice() * quantity);
    }
}
