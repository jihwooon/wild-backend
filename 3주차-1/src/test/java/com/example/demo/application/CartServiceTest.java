package com.example.demo.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.example.demo.infrastructure.CartRepository;
import com.example.demo.infrastructure.ProductRepository;
import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CartServiceTest {

    private Product product;
    private Cart cart;

    private ProductRepository productRepository;
    private CartRepository cartRepository;

    private CartService cartService;

    @BeforeEach
    void setUp() {
        product = new Product("product_1", "product #1", 5000);

        cartRepository = mock(CartRepository.class);
        cart = new Cart(List.of());

        given(cartRepository.find()).willReturn(cart);

        productRepository = mock(ProductRepository.class);

        given(productRepository.find(product.getId())).willReturn(product);

        cartService = new CartService(productRepository,
                cartRepository);
    }

    @Test
    void getCart() {
        Cart cart = cartService.getCart();

        assertThat(cart.getTotalPrice()).isEqualTo(0);
    }

    @Test
    void addProduct() {
        String productId = product.getId();
        int quantity = 2;

        cartService.addProduct(productId, quantity);

        verify(cartRepository).save(any());
    }
}
