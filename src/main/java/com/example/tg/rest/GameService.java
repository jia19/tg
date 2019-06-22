/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tg.rest;

import java.util.Calendar;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import com.example.tg.connection.DBConnection;
import com.example.tg.models.Game;
import com.example.tg.models.GameView;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


 /**
  * Rest service class
  * Note: Resteasy doesn't support CDI directly.
  */
@Path("/games")
@RequestScoped
public class GameService {
 
    @GET
	@Path("/")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response index() {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity("").build();
	}

	/**
     * Get Games has entered top list for a given market
     * Note: Use JsonView to avoid flushing data
     * @param game
     * @return
     */
    @PUT
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
    @Path("/topHistory/{top}")
	public  String  listTopGamesInHistory(@PathParam("top") int top, Game game) {
    	List<Game> list = null;
    	String queryField = null;
		
    	switch(top) {
    	case 10:
    		queryField = "top10Entry";
    		
    		break;
    	case 20:
    		queryField = "top20Entry";
    		break;
    	case 50:
    		queryField = "top50Entry";
    		break;
    	case 100:
    		queryField = "top100Entry";
    		break;
    	case 200:
    		queryField = "top200Entry";
    		break;
    	default:
    		break;
    	}
    	
         try {
        	 if(queryField != null)
        		 list = DBConnection.getDatastore().createQuery(Game.class).field("market").equal(game.getMarket())
     			.field(queryField).notEqual(0).asList();
     	
        	 	ObjectMapper mapper = new ObjectMapper();
        	 	mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        		return  mapper.writerWithView(GameView.TopView.class).writeValueAsString(list);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;

	}
    
	 /**
	  * List all games has being in top list for a given days
	  * Note: Use JsonView to avoid flushing data
	  * @param top 
	  * @param days
	  * @param game
	  * @return
	  */
    @PUT
	//@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/topGamesForDays/{top}/{days}")
	public  String  listTopGames(@PathParam("top") int top, @PathParam("days") int days, Game game) {
    	List<Game> list = null;
    	long entry = Calendar.getInstance().getTimeInMillis() - 24 * 3600 *1000;
    	
    	String queryField = null;
		
    	if(top == 10)
    		queryField = "top10Entry";
	    	 
	    if(top ==20)
	    	queryField = "top20Entry";
	    			
    	else if(top ==50)
    		queryField = "top50Entry";   

        try {
       	 if(queryField != null)
       		 list = DBConnection.getDatastore().createQuery(Game.class).field("market").equal(game.getMarket())
	    		.field(queryField).greaterThan(0)
	    		.field(queryField).lessThanOrEq(entry)
	    		.field("top10Exit").equal(0).asList();
       	 	if(list != null) {
       	 		ObjectMapper mapper = new ObjectMapper();
       	 		mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
           		return  mapper.writerWithView(GameView.TopView.class).writeValueAsString(list);
       	 	}
	 	
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return null;
	}

	@PUT
	@Path("/favorite")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Game markFavourite(Game game) {
		Query<Game> query =  DBConnection.getDatastore().createQuery(Game.class).field("gameId").equal(game.getGameId())
				.field("market").equal(game.getMarket());
		UpdateOperations<Game> ops = DBConnection.getDatastore().createUpdateOperations(Game.class).set("favorite", game.isFavorite());
		if(query.get() != null) {
			DBConnection.getDatastore().update(query, ops);
		
			return DBConnection.getDatastore().createQuery(Game.class).field("gameId").equal(game.getGameId()).get();
		}else
			return null;
	}

 	@PUT
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/rankHistory/{category}")
	public  Object listRankHistory(@PathParam("category") String category,Game game) {
 		Query<Game> query = DBConnection.getDatastore().createQuery(Game.class).field("gameId").equal(game.getGameId())
 				.field("market").equal(game.getMarket());
 		Game found = (Game)query.get();
 		Object history = null;
 		if(category != null)
	    	switch(category) {
	    	 case "daily":
	    		 if(found != null)
	    			 history = found.getHistory();
	    		 break;
	    	 case "weekly":
	    		 if(found != null)
	    			 history = found.getHistoryWeek();
	    		 break;
	    	 case "monthly":
	    		 if(found != null)
	    			 history = found.getHistoryMonth();
	    		 break;
	    		 default:
	    			 System.out.println("Not recogined caterory for history!");
	    	}
    	return history;
    	
	}

}
