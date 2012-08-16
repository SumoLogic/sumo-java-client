package com.sumologic.client.examples;

import com.sumologic.client.SumoClient;
import com.sumologic.client.SumoException;
import com.sumologic.client.model.SearchResult;

public class HelloWorld {

    public static void main( String[] args ) {
        // Create a client
        SumoClient client = new SumoClient( "user@demo.com", "password" );

        try {
            // Search for log lines containing "error"
            SearchResult result = client.search("error");

            // Print raw log lines
            System.out.println( result );
        } catch (SumoException e) {
            e.printStackTrace();
        }
    }
}
