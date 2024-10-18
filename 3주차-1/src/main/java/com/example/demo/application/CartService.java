package com.example.demo.application;

import com.example.demo.infrastructure.LineItemDAO;
import com.example.demo.infrastructure.ProductDAO;
import com.example.demo.model.Cart;
import com.example.demo.model.LineItem;
import com.example.demo.model.Product;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final LineItemDAO lineItemDAO;
    private final ProductDAO productDAO;

    public CartService(LineItemDAO lineItemDAO, ProductDAO productDAO) {
        this.lineItemDAO = lineItemDAO;
        this.productDAO = productDAO;
    }

    public Cart getCart() {
        List<LineItem> lineItems = lineItemDAO.findAll();

        lineItems.forEach(lineItem -> {
            String productId = lineItem.getProductId();
            Product product = productDAO.find(productId);

            int unitPrice = product.getPrice();
            int quantity = lineItem.getQuantity();

            lineItem.setProductName(product.getName());
            lineItem.setUnitPrice(product.getPrice());
            lineItem.setTotalPrice(unitPrice * quantity);
        });

        int totalPrice = lineItems.stream()
                .mapToInt(LineItem::getTotalPrice)
                .sum();

        return new Cart(lineItems, totalPrice);
    }

    public void addProduct(String productId, int quantity) {
        List<LineItem> lineItems = lineItemDAO.findAll();

        LineItem lineItem = lineItems.stream()
                .filter(v -> v.getProductId().equals(productId))
                .findFirst()
                .orElse(null);

        if (lineItem == null) {
            lineItem = new LineItem(productId, quantity);
            lineItemDAO.add(lineItem);
            return;
        }

        lineItem.setQuantity(quantity);
        lineItemDAO.update(lineItem);
    }
}
