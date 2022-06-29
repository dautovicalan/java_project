/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.utils;

import hr.alan.businessModel.Actor;
import hr.alan.businessModel.Director;
import hr.alan.businessModel.Genre;
import hr.alan.businessModel.Movie;
import hr.alan.businessModel.Person;
import hr.alan.businessModel.UploadData;
import hr.alan.xmlModels.Item;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Alan
 */
public class DatabasePreparerUtils {

   private DatabasePreparerUtils(){
   }
   
   private static final String DIR = "assets";
   private static final String EXT = ".jpg";
   
   public static UploadData prepareMovie(Item item) throws IOException{
       
       String destination = DIR + File.separator  + UUID.randomUUID() + EXT;
       FileUtils.copyFromUrl(item.getPlakat(), destination);
       
       Genre movieGenre = new Genre(item.getZanr());
 
       Movie movie = new Movie(
               item.getTitle(), 
               LocalDateTime.parse(item.getPubDate(), Movie.DATE_FORMATTER), 
               item.getDescription(), 
               item.getTrajanje().length() > 0 ? Integer.parseInt(item.getTrajanje())
               : 0, 
               destination, 
               LocalDate.parse(item.getPocetak(), Movie.POCETAK_FILMA_FORMATTER),
               movieGenre, 
               (Director) parseDirector(item.getRedatelj()));
       
       List<Person> listOfActors = parseActors(item.getGlumci());
            
       return new UploadData(movie, listOfActors, movieGenre);
   }
   
   private static Person parseDirector(String redatelj) {
       if ("".equals(redatelj)) {
           return new Director("No First Name", "No Last Name");
       }
       String[] split = redatelj.split(" ", -1);
       return new Director(split[0].trim(), split[1].trim());
    }
   
   private static List<Person> parseActors(String glumci) {
        if ("".equals(glumci)) {
           return new ArrayList<>();
       }
        List<Person> actors = new ArrayList<>();
        String[] arrayOfActors = glumci.trim().split(",", -1);
        
        
       for (String arrayOfActor : arrayOfActors) {
           String[] temp = arrayOfActor.trim().split(" ", -1);
           actors.add(new Actor(temp[0], temp[1]));
       }
        
        return actors;
    }
}