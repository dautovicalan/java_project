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
    private Person director;
    private Genre genre;

    public UploadData(Movie movie, List<Person> actors, Person director, Genre genre) {
        this.movie = movie;
        this.actors = actors;
        this.director = director;
        this.genre = genre;
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

    public Person getDirector() {
        return director;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    
    
    
}