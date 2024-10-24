package com.example.demo.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CartTest {

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        product1 = new Product("product_1", "product #1", 5000);
        product2 = new Product("product_2", "product #2", 3000);
    }

    @Test
    @DisplayName("getTotalPrice -빈 장바구니")
    void getTotalPriceValueZero() {
        Cart cart = new Cart(List.of());

        assertThat(cart.getTotalPrice()).isEqualTo(0);
    }

    @Test
    @DisplayName("getTotalPrice - 상품이 하나 담겨을 때")
    void getTotalPrice() {
        int quantity = 3;

        LineItem lineItem = createLineItem(product1, quantity);

        Cart cart = new Cart(List.of(lineItem));

        assertThat(cart.getTotalPrice())
            .isEqualTo(product1.getPrice() * quantity);
    }

    @Test
    @DisplayName("getTotalPrice - 상품이 하나 담겨을 때")
    void getTotalPriceWithManyLineItems() {
        int quantity1 = 3;
        int quantity2 = 2;

        Cart cart = new Cart(List.of(
            createLineItem(product1, quantity1),
            createLineItem(product2, quantity2)
        ));

        assertThat(cart.getTotalPrice())
            .isEqualTo(product1.getPrice() * quantity1 + product2.getPrice() * quantity2);
    }

    private LineItem createLineItem(Product product, int quantity) {
        LineItem lineItem = new LineItem(product.getId(), quantity);
        lineItem.setProduct(product);
        return lineItem;
    }
}
