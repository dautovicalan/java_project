/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.alan.businessModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author Alan
 */
public class Movie {
    
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    
    private int id;
    private String title;
    private LocalDateTime pubDate;
    private String movieDescription;
    private int duration;
    private String moviePicturePath;
    private LocalDateTime movieBegin;
    private Genre genre;

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Movie(String title, LocalDateTime pubDate, String movieDescription, int duration, String moviePicturePath, LocalDateTime movieBegin) {
        this.title = title;
        this.pubDate = pubDate;
        this.movieDescription = movieDescription;
        this.duration = duration;
        this.moviePicturePath = moviePicturePath;
        this.movieBegin = movieBegin;
    }

    public Movie(int id, String title, LocalDateTime pubDate, String movieDescription, int duration, String moviePicturePath, LocalDateTime movieBegin, Genre genre) {
        this.id = id;
        this.title = title;
        this.pubDate = pubDate;
        this.movieDescription = movieDescription;
        this.duration = duration;
        this.moviePicturePath = moviePicturePath;
        this.movieBegin = movieBegin;
        this.genre = genre;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getPubDate() {
        return pubDate;
    }

    public void setPubDate(LocalDateTime pubDate) {
        this.pubDate = pubDate;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getMoviePicturePath() {
        return moviePicturePath;
    }

    public void setMoviePicturePath(String moviePicturePath) {
        this.moviePicturePath = moviePicturePath;
    }

    public LocalDateTime getMovieBegin() {
        return movieBegin;
    }

    public void setMovieBegin(LocalDateTime movieBegin) {
        this.movieBegin = movieBegin;
    }

    @Override
    public String toString() {
        return title;
    }
}
