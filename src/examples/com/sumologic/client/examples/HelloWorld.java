package com.sumologic.client.examples;

import com.sumologic.client.SumoException;
import com.sumologic.client.SumoLogicClient;
import com.sumologic.client.search.model.SearchResponse;

public class HelloWorld {

    public static void main(String[] args) {
        // Create a client
        SumoLogicClient client = new SumoLogicClient("user@demo.com", "password");

        try {
            // Search for log lines containing "error"
            SearchResponse response = client.search("error");

            // Print raw log lines
            System.out.println(response);
        } catch (SumoException e) {
            e.printStackTrace();
        }
    }
}
