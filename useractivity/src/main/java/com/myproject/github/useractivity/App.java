package com.myproject.github.useractivity;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class App 
{
    public static void main( String[] args )
    {
                
        try {
        	
        	HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI("https://api.github.com/users/horvathlaszlo19/events/public"))
					.GET()
					.build();
			
			
			
			HttpResponse<String> response = HttpClient.newBuilder()
					.build()
					.send(request, BodyHandlers.ofString());
			
			Gson gson = new Gson();
			//Type userListType = new TypeToken<List<Item>>() {}.getClass();
			Item[] items = gson.fromJson(response.body(), Item[].class);
			
			for(Item item : items) {
				System.out.println(item.getId());
			}
			
			
			
			
			
			
		} catch (URISyntaxException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
