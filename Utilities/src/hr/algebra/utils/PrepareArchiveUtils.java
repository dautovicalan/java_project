/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.utils;

import hr.alan.businessModel.Movie;
import hr.alan.businessModel.MovieArchive;
import hr.alan.businessModel.MovieCast;
import hr.alan.businessModel.Person;
import hr.alan.dal.Repository;
import hr.alan.dal.RepositoryFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alan
 */
public class PrepareArchiveUtils {

    private static final Repository repo = RepositoryFactory.getRepository();
    private PrepareArchiveUtils() {
    }
    
    public static MovieArchive prepareArchive() throws Exception{
        List<MovieCast> movieCast = new ArrayList<>();
        List<Movie> movies = repo.selectMovies();
        movies.forEach((movie) -> {
            Set<Person> selectMovieCastActor = null;
            try {
                selectMovieCastActor = repo.selectMovieCastActor(movie.getId());
            } catch (Exception ex) {
                MessageUtils.showErrorMessage("Error", "Something went wrong. Contact BOSS");
            }
            movieCast.add(new MovieCast(selectMovieCastActor, movie));
        });
        return new MovieArchive(movieCast);
    }
    
}
