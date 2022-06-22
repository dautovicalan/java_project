/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.alan.businessModel;

import java.util.Date;

/**
 *
 * @author Alan
 */
public class Movie {
    private int id;
    private String title;
    private Date pubDate;
    private String movieDescription;
    private String origTitle;
    private int duration;
    private String moviePicturePath;
    private String movieLink;
    private Date movieBegin;

    public Movie(String title, Date pubDate, String movieDescription, String origTitle, int duration, String moviePicturePath, String movieLink, Date movieBegin) {
        this.title = title;
        this.pubDate = pubDate;
        this.movieDescription = movieDescription;
        this.origTitle = origTitle;
        this.duration = duration;
        this.moviePicturePath = moviePicturePath;
        this.movieLink = movieLink;
        this.movieBegin = movieBegin;
    }

    public Movie(int id, String title, Date pubDate, String movieDescription, String origTitle, int duration, String moviePicturePath, String movieLink, Date movieBegin) {
        this.id = id;
        this.title = title;
        this.pubDate = pubDate;
        this.movieDescription = movieDescription;
        this.origTitle = origTitle;
        this.duration = duration;
        this.moviePicturePath = moviePicturePath;
        this.movieLink = movieLink;
        this.movieBegin = movieBegin;
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

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public String getOrigTitle() {
        return origTitle;
    }

    public void setOrigTitle(String origTitle) {
        this.origTitle = origTitle;
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

    public String getMovieLink() {
        return movieLink;
    }

    public void setMovieLink(String movieLink) {
        this.movieLink = movieLink;
    }

    public Date getMovieBegin() {
        return movieBegin;
    }

    public void setMovieBegin(Date movieBegin) {
        this.movieBegin = movieBegin;
    }

    @Override
    public String toString() {
        return title;
    }
}
