/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.alan.dal;

import hr.alan.businessModel.Actor;
import hr.alan.businessModel.AppUser;
import hr.alan.businessModel.Director;
import hr.alan.businessModel.Genre;
import hr.alan.businessModel.Movie;
import hr.alan.businessModel.Person;
import hr.alan.businessModel.UploadData;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author Alan
 */
public interface Repository {
    
    List<Movie> selectMovies() throws Exception;
    Optional<Movie> selectMovie(int id) throws Exception;
    int createMovie(Movie movie) throws Exception;
    void createMovies(List<UploadData> movies) throws Exception;
    void deleteMovie(int id) throws Exception;
    void updateMovie(int id, Movie selectedMovie) throws Exception;
    
    List<Person> selectActors() throws Exception;
    Optional<Actor> selectActor(int id) throws Exception;
    int createActor(Actor actor) throws Exception;
    void updateActor(int id, Actor selectedActor) throws Exception;
    void deleteActor(int id) throws Exception;
    
    Set<Person> selectMovieCastActor(int movieId) throws Exception;
    void createCastActor(int movieId, int actorId) throws Exception;
    void deleteActorFromMovie(int id, int id0) throws Exception;
    
    List<Genre> selectGenres() throws Exception;
    int createGenre(Genre movie) throws Exception;
    
    List<Person> selectDirectors() throws Exception;
    Optional<Director> selectDirector(int id) throws Exception;
    int createDirector(Director director) throws Exception;
    void updateDirector(int id, Director director) throws Exception;
    void deleteDirector(int id) throws Exception;
    void createCastDirector(int movieId, int directorId) throws Exception;
    void deleteDirectorFromMovie(int movieId, int directorId) throws Exception;
    
    int registerUser(AppUser user) throws Exception;
    Optional<AppUser> authAdmin(AppUser user) throws Exception;
    Optional<AppUser> authUser(AppUser user) throws Exception;


    void deleteAllDBData() throws Exception;

    Optional<Person> selectMovieDirectors(int movieId) throws Exception;
        
}
