package com.weixin;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.util.Iterator;

/**
 * Hello world!
 *
 */
public class App 
{
    public DynamoDB db = null;
    public App(String profile){
        db = getDB(profile);
    }

    private DynamoDB getDB(String profile){
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_WEST_2)
                .withCredentials(new ProfileCredentialsProvider(profile))
                .build();
        if(null != amazonDynamoDB){
            return new DynamoDB(amazonDynamoDB);
        }
        return null;
    }


    public static void main( String[] args ) throws Exception
    {
        System.out.println( "Hello World!" );
        App app = new App("sbg-network-e2e");
        Table tabe  = app.db.getTable("public-profile-backup");

        JsonParser parser = new JsonFactory().createParser(new File("message.json"));

        JsonNode rootNode = new ObjectMapper().readTree(parser);
        Iterator<JsonNode> iter = rootNode.iterator();

        ObjectNode currentNode;

        while (iter.hasNext()) {
            currentNode = (ObjectNode) iter.next();
            String id = currentNode.path("id")





    }


}
