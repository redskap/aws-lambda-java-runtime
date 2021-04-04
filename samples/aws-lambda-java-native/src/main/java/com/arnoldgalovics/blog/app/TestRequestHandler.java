package com.arnoldgalovics.blog.app;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.HashMap;
import java.util.UUID;

public class TestRequestHandler implements RequestHandler<TestRequest, String> {
    @Override
    public String handleRequest(TestRequest input, Context context) {
        AmazonDynamoDB dynamo = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.EU_CENTRAL_1).build();
        PutItemRequest putItemRequest = new PutItemRequest();
        putItemRequest.setTableName("test-db");
        HashMap<String, AttributeValue> items = new HashMap<>();
        items.put("id", new AttributeValue(UUID.randomUUID().toString()));
        items.put("name", new AttributeValue(input.getName()));
        putItemRequest.setItem(items);
        dynamo.putItem(putItemRequest);
        return input.getName();
    }
}
