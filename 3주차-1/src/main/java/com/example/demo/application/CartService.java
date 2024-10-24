package com.example.demo.application;

import com.example.demo.infrastructure.CartRepository;
import com.example.demo.infrastructure.ProductRepository;
import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public CartService(
            ProductRepository productRepository,
            CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    public Cart getCart() {
        Cart cart = cartRepository.find();

        List<String> productIds = cart.getProductIds();

        Map<String, Product> products = productRepository.collectProductsByIds(
                productIds);

        cart.setProductsForLineItems(products);

        return cart;
    }

    public void addProduct(String productId, int quantity) {
        Product product = productRepository.find(productId);

        Cart cart = getCart();
        cart.addProduct(product, quantity);

        cartRepository.save(cart);
    }
}
