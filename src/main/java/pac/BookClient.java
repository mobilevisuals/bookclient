/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pac;

import java.io.IOException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.util.Arrays;
import java.util.List;

public class BookClient {
    
    private static ClientConfig config = new DefaultClientConfig();
    private static Client client = Client.create(config);
    private static WebResource service = client.resource(
            UriBuilder.fromUri("http://localhost:8080/REST_books").build());
    
    public static void main(String[] args) throws IOException {

        // getting XML data
        String xmlString = service.path("rest/BookService/books")
                .accept(MediaType.APPLICATION_XML).get(String.class);
        System.out.println(xmlString);
        System.out.println();
        
        //Getting book objects
        Book[] bookArray = service.path("rest/BookService/books")
                .accept(MediaType.APPLICATION_XML).get(Book[].class);
        
        for (Book b : bookArray){
            System.out.println("Book id: "+b.getId()+ " title: "+b.getTitle() +" author: "+b.getAuthor());
        }
        
        //Converting an array to List
        List<Book> bookList = Arrays.asList(bookArray);
       
    }
}

