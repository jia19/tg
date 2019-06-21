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


 /*
  * Note Resteasy doesn't support CDI directly.
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

//	@GET
//	@Path("/list")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Game> listAll() {
//		return DBConnection.getDatastore().createQuery(Game.class).asList(); 
//	}
//	
	/**
     * Get Games has entered top list for a given market
     * @param game
     * @return
     */
    @PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @Path("/topHistory/{top}")
	public  List<Game>  listTopGamesInHistory(@PathParam("top") int top, Game game) {
    	List<Game> list = null;
    	switch(top) {
    	case 10:
    		list = DBConnection.getDatastore().createQuery(Game.class).field("market").equal(game.getMarket())
        			.field("top10Entry").greaterThan(0).asList();
    		break;
    	case 20:
    		list = DBConnection.getDatastore().createQuery(Game.class).field("market").equal(game.getMarket())
			.field("top20Entry").greaterThan(0).asList();
    		break;
    	case 50:
    		list = DBConnection.getDatastore().createQuery(Game.class).field("market").equal(game.getMarket())
			.field("top50Entry").greaterThan(0).asList();
    		break;
    	case 100:
    		list = DBConnection.getDatastore().createQuery(Game.class).field("market").equal(game.getMarket())
			.field("top100Entry").greaterThan(0).asList();
    		break;
    	case 200:
    		list = DBConnection.getDatastore().createQuery(Game.class).field("market").equal(game.getMarket())
			.field("top200Entry").greaterThan(0).asList();
    		break;
    	default:
    		break;
    	}
    	return list;

	}
    
	 
    @PUT
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/topGamesForDays/{top}/{days}")
	public  List<Game>  listTopGames(@PathParam("top") int top, @PathParam("days") int days, Game game) {
    	long entry = Calendar.getInstance().getTimeInMillis() - 24 * 3600 *1000;
    	
    	Query<Game> query =  DBConnection.getDatastore().createQuery(Game.class).field("market").equal(game.getMarket());
		
    	if(top == 10)
	    	 return query.field("top10Entry").greaterThan(entry).field("top10Exit").lessThanOrEq(0).asList();
	    if(top ==20)
    		return DBConnection.getDatastore().createQuery(Game.class).field("market").equal(game.getMarket())
	    			.field("top20Entry").greaterThan(entry).field("top20Exit").lessThanOrEq(0).asList();
	    			
    	else if(top ==50)
    		return DBConnection.getDatastore().createQuery(Game.class).field("market").equal(game.getMarket())
	    			.field("top50Entry").greaterThan(entry).field("top50Exit").lessThanOrEq(0).asList();
	    		
    	else
    		return null;
	}

	@PUT
	@Path("/favorite")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Game markFavourite(Game game) {
		Query<Game> query =  DBConnection.getDatastore().createQuery(Game.class).field("appId").equal(game.getAppId());
		UpdateOperations<Game> ops = DBConnection.getDatastore().createUpdateOperations(Game.class).set("favorite", game.isFavorite());
		if(query.get() != null) {
			DBConnection.getDatastore().update(query, ops);
		
			return DBConnection.getDatastore().createQuery(Game.class).field("appId").equal(game.getAppId()).get();
		}else
			return null;
	}

 	@PUT
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/rankHistory/{category}")
	public  Object listRankHistory(@PathParam("category") String category,Game game) {
 		Query<Game> query = DBConnection.getDatastore().createQuery(Game.class).field("appId").equal(game.getAppId())
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
