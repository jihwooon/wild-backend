package com.example.demo.controllers;

import com.example.demo.controllers.dto.CartDto;
import com.example.demo.controllers.dto.CartDto.LineItemDto;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final MongoDatabase mongoDatabase;

    public CartController(MongoDatabase mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
    }

    @GetMapping
    CartDto detail() {
        MongoCollection<Document> collection = mongoDatabase.getCollection(
                "line_items");

        Collection<Document> documents = new ArrayList<>();

        collection.find().into(documents);

        List<LineItemDto> lineItems = documents.stream()
                .map(this::mapToDto)
                .toList();

        int totalPrice = lineItems.stream()
                .mapToInt(CartDto.LineItemDto::totalPrice)
                .sum();

        return new CartDto(
                lineItems,
                totalPrice
        );
    }

    private LineItemDto mapToDto(Document document) {
        String productId = document.getString("productId");
        Document productDocument = findProduct(productId);

        Integer unitPrice = document.getInteger("price");
        Integer quantity = document.getInteger("quantity");

        return new LineItemDto(
                document.getObjectId("_id").toString(),
                document.getString("product_id"),
                productDocument.getString("name"),
                unitPrice,
                quantity,
                unitPrice * quantity
        );
    }

    private Document findProduct(String productId) {
        return mongoDatabase.getCollection("products")
                .find(Filters.eq("_id", new ObjectId(productId)))
                .first();
    }
}
