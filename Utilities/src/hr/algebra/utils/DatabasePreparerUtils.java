/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.utils;

import hr.alan.businessModel.Movie;
import hr.alan.xmlModels.Item;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
   
   public static Movie prepareMovie(Item item) throws IOException{
       
       String destination = DIR + File.separator  + UUID.randomUUID() + EXT;
       FileUtils.copyFromUrl(item.getPlakat(), destination);
              
       return new Movie(
               item.getTitle(), 
               LocalDateTime.parse("2011-12-03T10:15:30", Movie.DATE_FORMATTER), 
               item.getDescription(), 
               0, 
               destination, 
               LocalDateTime.parse("2011-12-03T10:15:30", Movie.DATE_FORMATTER));
   }
}