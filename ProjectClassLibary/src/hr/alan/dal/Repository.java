/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.alan.dal;

import hr.alan.businessModel.Actor;
import hr.alan.businessModel.AppUser;
import hr.alan.businessModel.Director;
import hr.alan.businessModel.Movie;
import hr.alan.businessModel.MovieCast;
import hr.alan.businessModel.Person;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Alan
 */
public interface Repository {
    List<Movie> selectMovies();
    List<Actor> selectActors();
    List<Director> selectDirectors() throws Exception;
    Optional<Movie> selectMovie(int id);
    void updateMovie(int id, Movie selectedMovie);
    int createMovie(Movie movie);
    void createMovies(List<Movie> movies) throws Exception;
    void deleteMovie(int id);
    Optional<Director> selectDirector(int id);
    int createDirector(Director director);
    void updateDirector(int id, Director director);
    void deleteDirector(int id);
    Optional<Actor> selectActor(int id);
    void updateActor(int id, Actor selectedActor);
    int createActor(Actor actor);
    void deleteActor(int id);
    
    int registerUser(AppUser user);
    Optional<AppUser> authUser(AppUser user);
    void createCastActor(int movieId, Actor actor);
    void createCastDirector(int movieId, Director director);
    List<MovieCast> selectMovieCast(int movieId);

    Optional<AppUser> authAdmin(AppUser user);

    void deleteAllDBData();
}
