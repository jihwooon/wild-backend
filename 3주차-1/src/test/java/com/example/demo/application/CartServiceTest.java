package com.example.demo.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.example.demo.infrastructure.LineItemDAO;
import com.example.demo.infrastructure.ProductDAO;
import com.example.demo.model.Cart;
import com.example.demo.model.LineItem;
import com.example.demo.model.Product;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CartServiceTest {

    private Product product1;
    private Product product2;
    private List<LineItem> lineItems;

    private LineItemDAO lineItemDAO;
    private ProductDAO productDAO;

    private CartService cartService;

    @BeforeEach
    void setUp() {
        product1 = new Product("product_1", "product #1", 5000);
        product2 = new Product("product_2", "product #2", 3000);
        lineItems = new ArrayList<>();

        lineItemDAO = mock(LineItemDAO.class);

        given(lineItemDAO.findAll()).willReturn(lineItems);

        productDAO = mock(ProductDAO.class);

        given(productDAO.find(product1.getId())).willReturn(product1);
        given(productDAO.find(product2.getId())).willReturn(product2);

        cartService = new CartService(lineItemDAO, productDAO);
    }

    @Test
    @DisplayName("장바구니가 비어 있으면 총 가격은 0원이 반환해야 한다.")
    void totalPriceIsZero() {
        clearCart();

        Cart cart = cartService.getCart();

        assertThat(cart.getTotalPrice()).isEqualTo(0);
    }

    @Test
    @DisplayName("장바구니에 상품이 추가 되면 총 가격은 0원이 반환해야 한다.")
    void calculateTotalPrice() {
        int quantity1 = 2;
        int quantity2 = 3;

        addProductToCart(product1, quantity1);
        addProductToCart(product2, quantity2);

        cartService = new CartService(lineItemDAO, productDAO);

        Cart cart = cartService.getCart();

        assertThat(cart.getTotalPrice()).isEqualTo(
                product1.getPrice() * quantity1
                        + product2.getPrice() * quantity2);
    }

    @Test
    @DisplayName("비어있는 장바구니 상품을 담는다.")
    void addCart() {
        String productId = product1.getId();
        int quantity = 2;

        cartService.addProduct(productId, quantity);

        verify(lineItemDAO).add(
                argThat(lineItem -> lineItem.getProductId().equals(productId)
                        && lineItem.getQuantity() == quantity));
    }

    @Test
    @DisplayName("이미 담긴 장바구니에 상품을 담는다.")
    void addExistingCart() {
        String productId = product2.getId();
        int quantity = 2;
        int oldQuantity = 3;

        lineItems.add(new LineItem(product1.getId(), oldQuantity));

        cartService.addProduct(productId, quantity);

        verify(lineItemDAO).update(
                argThat(lineItem -> lineItem.getProductId().equals(productId)
                        && lineItem.getQuantity() == oldQuantity + quantity));
    }

    private void addProductToCart(Product product, int quantity) {
        List<LineItem> lineItems = lineItemDAO.findAll();

        String id = "item-" + (lineItems.size() + 1);
        lineItems.add(new LineItem(id, product.getId(), quantity));
    }

    private void clearCart() {
        lineItems.clear();
    }
}
