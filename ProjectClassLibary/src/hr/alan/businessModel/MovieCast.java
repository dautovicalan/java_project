/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.alan.businessModel;

/**
 *
 * @author Alan
 */
public class MovieCast {
    
    private Actor actor;
    private Director director;

    public MovieCast(Actor actor, Director director) {
        this.actor = actor;
        this.director = director;
    }

    public Actor getActor() {
        return actor;
    }

    public Director getDirector() {
        return director;
    }
    
    
}
