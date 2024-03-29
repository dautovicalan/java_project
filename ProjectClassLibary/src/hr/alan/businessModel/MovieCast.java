/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.alan.businessModel;

import java.util.List;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Alan
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class MovieCast {
    
    @XmlElement(name = "movie")
    private Movie movie;
    @XmlElement(name = "actor")
    private Set<Person> actors;
    @XmlElement(name = "director")
    private Person director;

    public Person getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public MovieCast(Set<Person> actors, Movie movie, Director director) {
        this.actors = actors;
        this.movie = movie;
        this.director = director;
    }

    public Set<Person> getActor() {
        return actors;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public MovieCast() {
    }
    
}
