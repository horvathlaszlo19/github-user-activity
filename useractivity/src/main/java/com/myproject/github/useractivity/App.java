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
import com.myproject.github.event.GithubEvent;

public class App 
{
    public static void main( String[] args )
    {
    	    	
    	String name;
    	URI uri = null;

    	try {
    		if(args.length == 0) {
    			throw new IllegalArgumentException("Username was not give.");
    		}
    		
    		name = args[0];
    		uri = URI.create("https://api.github.com/users/" + name + "/events/public");
    		
    		
    	} catch(IllegalArgumentException e) {
    		System.err.println(e.getMessage());
    	}
                
        try {
        		
        	HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(uri)
					.GET()
					.build();
			
			HttpResponse<String> response = client
					.send(request, BodyHandlers.ofString());
			
			if(response.statusCode() != 200) throw new IllegalArgumentException("Name is invalid");
			
			
			Gson gson = new Gson();
			
			Type eventListType = new TypeToken<List<GithubEvent>>() {}.getType();
			List<GithubEvent> events = gson.fromJson(response.body(), eventListType);
			
			  
			for(GithubEvent event : events) {
				if(event.getType().equals("WatchEvent") ) {
					System.out.println(event.getActor().getLogin() +
							" started watching " + event.getRepo().getName() + 
							" at " + event.getCreated_at());
				} else if(event.getType().equals("PushEvent")) {
					System.out.println(event.getActor().getLogin() + " pushed to " +
										event.getRepo().getName() + 
										" at " + event.getCreated_at());
				} else if(event.getType().equals("CreateEvent")) {
					System.out.println(event.getActor().getLogin() + " created " + 
										event.getRepo().getName() + 
										" at " + event.getCreated_at());
				}
			}
			
			
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
