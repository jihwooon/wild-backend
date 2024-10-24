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
    void getLineItem() {
        int quantity1 = 3;
        int quantity2 = 2;

        LineItem lineItem1 = createLineItem(product1, quantity1);
        LineItem lineItem2 = createLineItem(product2, quantity2);

        Cart cart = new Cart(List.of(lineItem1, lineItem2));

        assertThat(cart.getLineItem(product1.getId())).isEqualTo(lineItem1);
        assertThat(cart.getLineItem(product2.getId())).isEqualTo(lineItem2);
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

    @Test
    @DisplayName("비어있는 장바구니 상품을 담는다.")
    void addCart() {
        int quantity = 3;

        Cart cart = new Cart(List.of());

        cart.addProduct(product1, quantity);

        List<LineItem> lineItems = cart.getLineItems();
        assertThat(lineItems).hasSize(1);
        assertThat(lineItems.getFirst().getQuantity()).isEqualTo(quantity);
        assertThat(lineItems.getFirst().getUnitPrice()).isEqualTo(5000);
    }

    @Test
    @DisplayName("이미 담긴 장바구니에 상품을 담는다.")
    void addExistingCart() {
        int quantity = 2;
        int oldQuantity = 3;

        Cart cart = new Cart(List.of(
            createLineItem(product1, oldQuantity)
        ));

        cart.addProduct(product1, quantity);

        List<LineItem> lineItems = cart.getLineItems();
        assertThat(lineItems).hasSize(1);

        assertThat(lineItems.get(0).getQuantity()).isEqualTo(quantity + 3);
    }

    private LineItem createLineItem(Product product, int quantity) {
        LineItem lineItem = new LineItem(product.getId(), quantity);
        lineItem.setProduct(product);
        return lineItem;
    }
}
