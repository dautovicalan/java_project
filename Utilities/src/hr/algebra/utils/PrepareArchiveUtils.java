/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.utils;

import hr.alan.businessModel.Director;
import hr.alan.businessModel.Movie;
import hr.alan.businessModel.MovieArchive;
import hr.alan.businessModel.MovieCast;
import hr.alan.businessModel.Person;
import hr.alan.dal.Repository;
import hr.alan.dal.RepositoryFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
            try {
                Set<Person> selectMovieCastActor = null;
                selectMovieCastActor = repo.selectMovieCastActor(movie.getId());
                Optional<Person> peroKero = repo.selectMovieDirectors(movie.getId());
                Person theRealOg = peroKero.isPresent() ? peroKero.get() : new Director(-1, "No director", "No director");
                movieCast.add(new MovieCast(selectMovieCastActor, movie, (Director) theRealOg));
            } catch (Exception ex) {
                Logger.getLogger(PrepareArchiveUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return new MovieArchive(movieCast);
    }
    
}
