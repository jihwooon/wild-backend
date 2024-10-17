package com.example.demo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MongoTest {

    @Test
    void readProducts() {
        // TODO:
        // 1. DB에 접속하기
        String mongoURL = "mongodb://localhost:27017";
        MongoClient client = MongoClients.create(mongoURL);

        // 2. 데이터베이스 선택 -> demo
        String mongoDatabase = "demo";
        MongoDatabase database = client.getDatabase(mongoDatabase);

        // 3. colliction 목록 얻기 -> products

        MongoCollection<Document> collection = database.getCollection("products");

        List<Document> documents = new ArrayList<>();
        collection.find().into(documents);

        Document document = collection.find(
            Filters.eq("_id", new ObjectId("67105e5459e10f1b9980210d"))).first();

        System.out.println(document.getString("name"));
        System.out.println(document.getInteger("price"));
    }
}
