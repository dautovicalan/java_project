/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.alan.dal;

import hr.alan.businessModel.Actor;
import hr.alan.businessModel.Movie;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Alan
 */
public interface Repository {
    List<Movie> selectMovies() throws Exception;

    List<Actor> selectActors() throws Exception;
}
