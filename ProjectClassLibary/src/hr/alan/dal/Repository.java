/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.alan.dal;

import hr.alan.businessModel.Actor;
import hr.alan.businessModel.Director;
import hr.alan.businessModel.Movie;
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
    void deleteMovie(int id);
    Optional<Director> selectDirector(int id);
    int createDirector(Director director);
    void updateDirector(int id, Director director);
    void deleteDirector(int id);
}
