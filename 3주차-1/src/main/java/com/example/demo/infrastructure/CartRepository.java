package com.example.demo.infrastructure;


import com.example.demo.model.Cart;
import com.example.demo.model.LineItem;
import com.example.demo.model.Product;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepository {

    private final ProductRepository productRepository;
    private final LineItemRepository lineItemRepository;

    public CartRepository(ProductRepository productRepository,
            LineItemRepository lineItemRepository) {
        this.productRepository = productRepository;
        this.lineItemRepository = lineItemRepository;
    }

    public Cart find() {
        List<LineItem> lineItems = lineItemRepository.findAll();

        lineItems.forEach(lineItem -> {
            String productId = lineItem.getProductId();
            Product product = productRepository.find(productId);

            lineItem.setProduct(product);
        });

        return new Cart(lineItems);
    }

    public void save(Cart cart) {
        cart.getLineItems().forEach(lineItem -> {
            if (lineItem.getId() == null) {
                lineItemRepository.add(lineItem);
                return;
            }

            lineItemRepository.update(lineItem);
        });
    }
}
