package com.sumologic.client.examples;

import com.sumologic.client.SumoException;
import com.sumologic.client.SumoLogic;
import com.sumologic.client.SumoLogicClient;
import com.sumologic.client.model.SearchResponse;

public class HelloWorld {

    public static void main(String[] args) {
        // Create a client
        SumoLogic client = new SumoLogicClient("user@demo.com", "password");

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
