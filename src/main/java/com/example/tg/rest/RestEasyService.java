package com.example.tg.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class RestEasyService extends Application {
 
    private Set<Object> singletons = new HashSet<Object>();
 
    public RestEasyService() {
        singletons.add(new GameService());
    }
 
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

}