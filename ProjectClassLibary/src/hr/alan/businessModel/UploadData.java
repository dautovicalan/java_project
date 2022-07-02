/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.alan.businessModel;

import java.util.List;

/**
 *
 * @author Alan
 */
public class UploadData {
    private Movie movie;
    private List<Person> actors;
    private Genre genre;
    private Director director;

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public UploadData(Movie movie, List<Person> actors, Genre genre, Director director) {
        this.movie = movie;
        this.actors = actors;
        this.genre = genre;
        this.director = director;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Person> getActors() {
        return actors;
    }

    public void setActors(List<Person> actors) {
        this.actors = actors;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    
    
    
}
