/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.alan.businessModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Alan
 */
@XmlAccessorType(XmlAccessType.FIELD)    
public class Movie {
    
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss z").withZone(ZoneId.systemDefault());
    public static final DateTimeFormatter POCETAK_FILMA_FORMATTER = DateTimeFormatter.ofPattern("d.M.yyyy");
    
    @XmlAttribute
    private int id;
    @XmlElement(name = "title")
    private String title;
    private LocalDateTime pubDate;
    @XmlElement(name = "movieDesc")
    private String movieDescription;
    @XmlElement(name = "duration")
    private int duration;
    @XmlElement(name = "picturePath")
    private String moviePicturePath;
    @XmlJavaTypeAdapter(PubDateAdapter.class)
    @XmlElement(name = "movieBegin")
    private LocalDate movieBegin;
    @XmlElement(name = "genre")
    private Genre genre;

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Movie(String title, LocalDateTime pubDate, String movieDescription, int duration, String moviePicturePath, LocalDate movieBegin, Genre genre) {
        this.title = title;
        this.pubDate = pubDate;
        this.movieDescription = movieDescription;
        this.duration = duration;
        this.moviePicturePath = moviePicturePath;
        this.movieBegin = movieBegin;
        this.genre = genre;
    }

    public Movie(int id, String title, LocalDateTime pubDate, String movieDescription, int duration, String moviePicturePath, LocalDate movieBegin, Genre genre) {
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

    public LocalDate getMovieBegin() {
        return movieBegin;
    }

    public void setMovieBegin(LocalDate movieBegin) {
        this.movieBegin = movieBegin;
    }

    @Override
    public String toString() {
        return title;
    }
}
