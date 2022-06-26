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
               LocalDateTime.parse("2011-12-03T10:15:30", Movie.DATE_FORMATTER), 
               item.getDescription(), 
               0, 
               destination, 
               LocalDateTime.parse("2011-12-03T10:15:30", Movie.DATE_FORMATTER));
       
       Person director = new Director(item.getRedatelj(), item.getRedatelj());
       List<Person> listOfActors = parseActors(item.getGlumci());
       
              
       return new UploadData(movie, listOfActors, director, movieGenre);
   }
   
   private static List<Person> parseActors(String glumci) {
        if ("".equals(glumci)) {
           return new ArrayList<>();
       }
        List<Person> actors = new ArrayList<>();
        String[] arrayOfActors = glumci.trim().split(",", -1);
        
        
       for (int i = 0; i < arrayOfActors.length; i++) {
         String[] temp = arrayOfActors[i].trim().split(" ", -1);
         actors.add(new Actor(temp[0], temp[1]));
       }
        
        return actors;
    }
}