/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.alan.dal.sql;

import hr.alan.businessModel.Actor;
import hr.alan.businessModel.AppUser;
import hr.alan.businessModel.Director;
import hr.alan.businessModel.Movie;
import hr.alan.businessModel.MovieCast;
import hr.alan.businessModel.Person;
import hr.alan.dal.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Alan
 */
public class SqlRepository implements Repository{
    
    private static final String CREATE_MOVIE = "{ CALL createMovie (?,?,?,?,?,?,?) }";
    private static final String UPDATE_MOVIE = "{ CALL updateMovie (?,?,?,?,?,?) }";
    private static final String DELETE_MOVIE = "{ CALL deleteMovie (?) }";
    private static final String SELECT_MOVIES = "{ CALL selectMovies }";
    private static final String SELECT_MOVIE = "{ CALL selectMovie (?) }";
    private static final String SELECT_ACTORS = "{ CALL selectActors }";
    private static final String SELECT_ACTOR = "{ CALL selectActor (?) }";
    private static final String CREATE_ACTOR = "{ CALL createActor (?,?,?) }";
    private static final String UPDATE_ACTOR = "{ CALL updateActor (?,?,?) }";
    private static final String DELETE_ACTOR = "{ CALL deleteActor (?) }";
    private static final String SELECT_DIRECTORS = "{ CALL selectDirectors }";
    private static final String SELECT_DIRECTOR = "{ CALL selectDirector (?) }";
    private static final String CREATE_DIRECTOR = "{ CALL createDirector (?,?,?) }";
    private static final String DELETE_DIRECTOR = "{ CALL deleteDirector (?) }";
    private static final String UPDATE_DIRECTOR = "{ CALL updateDirector (?,?,?) }";
    private static final String CREATE_CAST_ACTOR = "{ CALL createCastActor (?,?) }";
    private static final String CREATE_CAST_DIRECTOR = "{ CALL createCastDirector (?,?) }";
    private static final String SELECT_MOVIE_CAST = "{ CALL selectMovieCast (?) }";
    
    
    private static final String REGISTER_USER = "{ CALL registerUser (?,?,?) }";
    private static final String AUTH_USER = "{ CALL authUser (?,?) }";
    
    private static final String AUTH_ADMIN = "{ CALL authAdmin (?,?) }";

    @Override
    public List<Movie> selectMovies() {
        List<Movie> movies = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIES);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt("Id"), 
                        rs.getString("Title"), 
                        LocalDateTime.parse(rs.getString("PubDate"), Movie.DATE_FORMATTER), 
                        rs.getString("MovieDescription"), 
                        rs.getInt("Duration"), 
                        rs.getString("MoviePicturePath"), 
                        LocalDateTime.parse(rs.getString("MovieBegin"), Movie.DATE_FORMATTER))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return movies;
    }   

    @Override
    public List<Actor> selectActors() {
        List<Actor> actors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ACTORS);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                actors.add(new Actor(rs.getInt("Id"), 
                        rs.getString("FirstName"),
                        rs.getString("LastName")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return actors;
    }

    @Override
    public List<Director> selectDirectors() throws Exception{
        List<Director> directors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_DIRECTORS);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println(rs.getString("FirstName"));
                directors.add(new Director(rs.getInt("Id"), 
                        rs.getString("FirstName"),
                        rs.getString("LastName")));
            }
        } 
        return directors;
    }

    @Override
    public Optional<Movie> selectMovie(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIE)) {
            stmt.setInt("@" + "movieId", id);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(
                            new Movie(
                                rs.getInt("Id"), 
                                rs.getString("Title"), 
                                LocalDateTime.parse(rs.getString("PubDate"), Movie.DATE_FORMATTER), 
                                rs.getString("MovieDescription"), 
                                rs.getInt("Duration"), 
                                rs.getString("MoviePicturePath"), 
                                LocalDateTime.parse(rs.getString("MovieBegin"), Movie.DATE_FORMATTER)
                                )
                    );
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Optional.empty();
    }

    @Override
    public void updateMovie(int id, Movie data) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_MOVIE)) {
            stmt.setString("@" + "title", data.getTitle());
            stmt.setString("@" + "pubDate", data.getPubDate().format(Movie.DATE_FORMATTER));
            stmt.setString("@" + "description", data.getMovieDescription());
            stmt.setInt("@" + "duration", data.getDuration());
            stmt.setString("@" + "movieBegin", data.getMovieBegin().format(Movie.DATE_FORMATTER));
            stmt.setString("@" + "moviePicturePath", data.getMoviePicturePath());
            stmt.setInt("@" + "movieId", id);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int createMovie(Movie movie) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {
            stmt.setString("@" + "title", movie.getTitle());
            stmt.setString("@" + "pubDate", movie.getPubDate().format(Movie.DATE_FORMATTER));
            stmt.setString("@" + "description", movie.getMovieDescription());
            stmt.setInt("@" + "duration", movie.getDuration());
            stmt.setString("@" + "movieBegin", movie.getMovieBegin().format(Movie.DATE_FORMATTER));
            stmt.setString("@" + "moviePicturePath", movie.getMoviePicturePath());
            stmt.registerOutParameter("@" + "movieId", Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt("@" + "movieId");
        } catch (SQLException ex) {
            Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public void deleteMovie(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_MOVIE)) {

            stmt.setInt("@" + "movieId", id);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Optional<Director> selectDirector(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_DIRECTOR)) {
            stmt.setInt("@" + "directorId", id);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(
                            new Director(
                                    rs.getInt("Id"),
                                    rs.getString("FirstName"), 
                                    rs.getString("LastName"))
                    );
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Optional.empty();
    }

    @Override
    public int createDirector(Director director) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_DIRECTOR)) {
            stmt.setString("@" + "firstName", director.getFirstName());
            stmt.setString("@" + "lastName", director.getLastName());
            stmt.registerOutParameter("@" + "directorId", Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt("@" + "directorId");
        } catch (SQLException ex) {
            Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public void updateDirector(int id, Director director) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_DIRECTOR)) {
            stmt.setString("@" + "firstName", director.getFirstName());
            stmt.setString("@" + "lastName", director.getLastName());
            stmt.setInt("@" + "directorId", id);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteDirector(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_DIRECTOR)) {
            stmt.setInt("@" + "directorId", id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Optional<Actor> selectActor(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ACTOR)) {
            stmt.setInt("@" + "actorId", id);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(
                            new Actor(
                                    rs.getInt("Id"),
                                    rs.getString("FirstName"), 
                                    rs.getString("LastName"))
                    );
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Optional.empty();
    }

    @Override
    public void updateActor(int id, Actor actor) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_ACTOR)) {
            stmt.setString("@" + "firstName", actor.getFirstName());
            stmt.setString("@" + "lastName", actor.getLastName());
            stmt.setInt("@" + "actorId", id);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int createActor(Actor actor) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_ACTOR)) {
            stmt.setString("@" + "firstName", actor.getFirstName());
            stmt.setString("@" + "lastName", actor.getLastName());
            stmt.registerOutParameter("@" + "actorId", Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt("@" + "actorId");
        } catch (SQLException ex) {
            Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public void deleteActor(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_ACTOR)) {
            stmt.setInt("@" + "actorId", id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int registerUser(AppUser user) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(REGISTER_USER)) {
            stmt.setString("@" + "username", user.getUserName());
            stmt.setString("@" + "userPassword", user.getUserPassword());
            stmt.registerOutParameter("@" + "userId", Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt("@" + "userId");
        } catch (SQLException ex) {
            Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public Optional<AppUser> authUser(AppUser user) {
       DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(AUTH_USER)) {
            stmt.setString("@" + "username", user.getUserName());
            stmt.setString("@" + "userPassword", user.getUserPassword());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(
                            new AppUser(
                                    rs.getInt("Id"),
                                    rs.getString("UserName"), 
                                    rs.getString("UserPassword"))
                    );
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Optional.empty();
    }

    @Override
    public void createCastActor(int movieId, Actor actor) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_CAST_ACTOR)) {
            stmt.setInt("@" + "actorId", actor.getId());
            stmt.setInt("@" + "movieId", movieId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createCastDirector(int movieId, Director director) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_CAST_DIRECTOR)) {
            stmt.setInt("@" + "directorId", director.getId());
            stmt.setInt("@" + "movieId", movieId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<MovieCast> selectMovieCast(int movieId) {
       List<MovieCast> movieCast = new ArrayList<>();
       DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIE_CAST)) {
            stmt.setInt("@" + "movieId", movieId);
            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    movieCast.add(
                            new MovieCast(new Actor(rs.getString("ActorFirstName"), 
                                    rs.getString("ActorLastName")), new Director
                                (rs.getString("DirectorFirstName"), rs.getString("DirectorLastName"))));
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return movieCast;
    }

    @Override
    public Optional<AppUser> authAdmin(AppUser user) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(AUTH_ADMIN)) {
            stmt.setString("@" + "username", user.getUserName());
            stmt.setString("@" + "userPassword", user.getUserPassword());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(
                            new AppUser(
                                    rs.getInt("Id"),
                                    rs.getString("UserName"), 
                                    rs.getString("UserPassword"))
                    );
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Optional.empty();
    }
}
