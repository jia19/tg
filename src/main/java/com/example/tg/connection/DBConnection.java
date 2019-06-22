package com.example.tg.connection;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * Read db configuration files and set up db connection
 *
 */
@Singleton
@Startup
public class DBConnection {

	private static MongoClient mongoClient;
    private static Datastore datastore;

    public DBConnection() {
    }

    @PostConstruct
    public void init() {
        if ((mongoClient == null) && (datastore == null)) {
        	
            Morphia morphia = new Morphia();
            morphia.mapPackage("com.example.tg.models");
            

            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = loader.getResourceAsStream("db.properties");
            
            Properties properties = new Properties();
            try {
				properties.load(inputStream);
				String dbURI = properties.getProperty("uri");
				String dbName = properties.getProperty("db");
				
				if(dbURI != null && dbName != null) {
				
					MongoClientURI mongoClientURI = new MongoClientURI(dbURI + dbName);
					//MongoClientURI mongoClientURI = new MongoClientURI("mongodb://127.0.0.1:27017/tg");
		            mongoClient = new MongoClient(mongoClientURI);
		            datastore = morphia.createDatastore(mongoClient, dbName);
		            datastore.ensureIndexes();
				}
					
			} catch (IOException e) {
				
				e.printStackTrace();
			} finally {
				 try {
					inputStream.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
  
        }
    }
    
    public static Datastore getDatastore() {
        return datastore;
    }

    @PreDestroy
    public void closeConnection() {
        if (mongoClient != null) {
        	mongoClient.close();
        	mongoClient = null;
        }
        datastore = null;
    }

}