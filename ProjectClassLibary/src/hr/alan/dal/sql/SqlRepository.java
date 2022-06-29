/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.alan.dal.sql;

import hr.alan.businessModel.Actor;
import hr.alan.businessModel.AppUser;
import hr.alan.businessModel.Director;
import hr.alan.businessModel.Genre;
import hr.alan.businessModel.Movie;
import hr.alan.businessModel.MovieCast;
import hr.alan.businessModel.Person;
import hr.alan.businessModel.UploadData;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;


/**
 *
 * @author Alan
 * @param <T>
 */
public class SqlRepository implements Repository{
    
    private static final String CREATE_MOVIE = "{ CALL createMovie (?,?,?,?,?,?,?,?,?) }";
    private static final String UPDATE_MOVIE = "{ CALL updateMovie (?,?,?,?,?,?,?,?,?) }";
    private static final String DELETE_MOVIE = "{ CALL deleteMovie (?) }";
    private static final String SELECT_MOVIES = "{ CALL selectMovies }";
    private static final String SELECT_MOVIE = "{ CALL selectMovie (?) }";
    private static final String SELECT_ACTORS = "{ CALL selectActors }";
    private static final String SELECT_GENRES = "{ CALL selectGenres }";
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
    private static final String SELECT_MOVIE_CAST_ACTOR = "{ CALL selectMovieCastActor (?) }";
    private static final String CREATE_GENRE = "{ CALL createGenre (?,?) }";

    
    private static final String REGISTER_USER = "{ CALL registerUser (?,?,?) }";
    private static final String AUTH_USER = "{ CALL authUser (?,?) }";
    
    private static final String AUTH_ADMIN = "{ CALL authAdmin (?,?) }";
    
    private static final String DELETE_ALL_DB_DATA = "{ CALL deleteAllDBData }";
    private static final String DELETE_ACTOR_FROM_MOVIE = "{ CALL deleteActorFromMovie (?,?) }";

    @Override
    public List<Movie> selectMovies() throws SQLException {
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
                        LocalDate.parse(rs.getString("MovieBegin"), Movie.POCETAK_FILMA_FORMATTER),
                        new Genre(rs.getInt("GenreId"),
                        rs.getString("GenreName")),
                        new Director(rs.getInt("DirectorId"),
                                rs.getString("DirectorFirstName"),
                                rs.getString("DirectorLastName"))
                )
                );
            }
        }
        return movies;
    }   

    @Override
    public List<Person> selectActors() throws SQLException {
        List<Person> actors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ACTORS);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                actors.add(new Actor(rs.getInt("Id"), 
                        rs.getString("FirstName"),
                        rs.getString("LastName")));
            }
        } 
        return actors;
    }

    @Override
    public List<Person> selectDirectors() throws Exception{
        List<Person> directors = new ArrayList<>();
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
    public Optional<Movie> selectMovie(int id) throws SQLException {
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
                                LocalDate.parse(rs.getString("MovieBegin"), Movie.POCETAK_FILMA_FORMATTER),
                                new Genre(rs.getInt("GenreId"),
                                    rs.getString("GenreName")),
                                new Director(rs.getInt("DirectorId"),
                                rs.getString("DirectorFirstName"),
                                rs.getString("DirectorLastName"))
                                )
                    );
                }

            }

        }
        return Optional.empty();
    }

    @Override
    public void updateMovie(int id, Movie data) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_MOVIE)) {
            stmt.setString("@" + "title", data.getTitle());
            stmt.setString("@" + "pubDate", data.getPubDate().format(Movie.DATE_FORMATTER));
            stmt.setString("@" + "description", data.getMovieDescription());
            stmt.setInt("@" + "duration", data.getDuration());
            stmt.setInt("@" + "genreId", data.getGenre().getId());
            stmt.setString("@" + "movieBegin", data.getMovieBegin().format(Movie.POCETAK_FILMA_FORMATTER));
            stmt.setString("@" + "moviePicturePath", data.getMoviePicturePath());
            stmt.setInt("@" + "directorId", data.getDirector().getId());
            stmt.setInt("@" + "movieId", id);

            stmt.executeUpdate();
        }
    }

    @Override
    public int createMovie(Movie movie) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {
            stmt.setString("@" + "title", movie.getTitle());
            stmt.setString("@" + "pubDate", movie.getPubDate().format(Movie.DATE_FORMATTER));
            stmt.setString("@" + "description", movie.getMovieDescription());
            stmt.setInt("@" + "duration", movie.getDuration());
            stmt.setInt("@" + "genreId", movie.getGenre().getId());
            stmt.setString("@" + "movieBegin", movie.getMovieBegin().format(Movie.POCETAK_FILMA_FORMATTER));
            stmt.setString("@" + "moviePicturePath", movie.getMoviePicturePath());
            stmt.setInt("@" + "directorId", movie.getDirector().getId());
            stmt.registerOutParameter("@" + "movieId", Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt("@" + "movieId");
        } 
    }
    
    @Override
    public void createMovies(List<UploadData> movies) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {

            for (UploadData movie : movies) {
                int createdGenre = createGenre(movie.getGenre());
                int createdDirector = createDirector((Director) movie.getMovie().getDirector());
                
                stmt.setString("@" + "title", movie.getMovie().getTitle());
                stmt.setString("@" + "pubDate", movie.getMovie().getPubDate().format(Movie.DATE_FORMATTER));
                stmt.setString("@" + "description", movie.getMovie().getMovieDescription());
                stmt.setInt("@" + "duration", movie.getMovie().getDuration());
                stmt.setString("@" + "movieBegin", movie.getMovie().getMovieBegin().format(Movie.POCETAK_FILMA_FORMATTER));
                stmt.setString("@" + "moviePicturePath", movie.getMovie().getMoviePicturePath());
                stmt.setInt("@" + "genreId", createdGenre);  
                stmt.setInt("@" + "directorId", createdDirector);
                stmt.registerOutParameter("@" + "movieId", Types.INTEGER);
                stmt.executeUpdate();
                int createdMovie = stmt.getInt("@" + "movieId");
                
                
                for (Person actor : movie.getActors()) {
                    int createdActor = createActor((Actor) actor);
                    createCastActor(createdMovie, createdActor);
                }
                
            }
        }
    }

    @Override
    public void deleteMovie(int id) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_MOVIE)) {

            stmt.setInt("@" + "movieId", id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Director> selectDirector(int id) throws SQLException {
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

        }

        return Optional.empty();
    }

    @Override
    public int createDirector(Director director) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_DIRECTOR)) {
            stmt.setString("@" + "firstName", director.getFirstName());
            stmt.setString("@" + "lastName", director.getLastName());
            stmt.registerOutParameter("@" + "directorId", Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt("@" + "directorId");
        }
    }

    @Override
    public void updateDirector(int id, Director director) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_DIRECTOR)) {
            stmt.setString("@" + "firstName", director.getFirstName());
            stmt.setString("@" + "lastName", director.getLastName());
            stmt.setInt("@" + "directorId", id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteDirector(int id) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_DIRECTOR)) {
            stmt.setInt("@" + "directorId", id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Actor> selectActor(int id) throws SQLException {
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

        }

        return Optional.empty();
    }

    @Override
    public void updateActor(int id, Actor actor) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_ACTOR)) {
            stmt.setString("@" + "firstName", actor.getFirstName());
            stmt.setString("@" + "lastName", actor.getLastName());
            stmt.setInt("@" + "actorId", id);

            stmt.executeUpdate();
        }
    }

    @Override
    public int createActor(Actor actor) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_ACTOR)) {
            stmt.setString("@" + "firstName", actor.getFirstName());
            stmt.setString("@" + "lastName", actor.getLastName());
            stmt.registerOutParameter("@" + "actorId", Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt("@" + "actorId");
        }
    }

    @Override
    public void deleteActor(int id) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_ACTOR)) {
            stmt.setInt("@" + "actorId", id);
            stmt.executeUpdate();
        }
    }

    @Override
    public int registerUser(AppUser user) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(REGISTER_USER)) {
            stmt.setString("@" + "username", user.getUserName());
            stmt.setString("@" + "userPassword", user.getUserPassword());
            stmt.registerOutParameter("@" + "userId", Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt("@" + "userId");
        }
    }

    @Override
    public Optional<AppUser> authUser(AppUser user) throws SQLException {
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

        } 
        return Optional.empty();
    }

    @Override
    public void createCastActor(int movieId, int actorId) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_CAST_ACTOR)) {
            stmt.setInt("@" + "actorId", actorId);
            stmt.setInt("@" + "movieId", movieId);
            stmt.executeUpdate();
        } 
    }

    @Override
    public void createCastDirector(int movieId, Director director) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_CAST_DIRECTOR)) {
            stmt.setInt("@" + "directorId", director.getId());
            stmt.setInt("@" + "movieId", movieId);
            stmt.executeUpdate();
        }
    }

    @Override
    public Set<Person> selectMovieCastActor(int movieId) throws SQLException {
       Set<Person> movieCastActors = new TreeSet<>();
       DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIE_CAST_ACTOR)) {
            stmt.setInt("@" + "movieId", movieId);
            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    movieCastActors.add(
                            new Actor(rs.getInt("Id"), rs.getString("FirstName"), 
                                    rs.getString("LastName"))
                    );
                }

            }

        }
        return movieCastActors;
    }

    @Override
    public Optional<AppUser> authAdmin(AppUser user) throws SQLException {
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

        }

        return Optional.empty();
    }

    @Override
    public void deleteAllDBData() throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_ALL_DB_DATA)) {
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteActorFromMovie(int movieId, int actorId) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_ACTOR_FROM_MOVIE)) {

            stmt.setInt("@" + "movieId", movieId);
            stmt.setInt("@" + "actorId", actorId);

            stmt.executeUpdate();
        }
    }

    @Override
    public int createGenre(Genre movie) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_GENRE)) {
            stmt.setString("@" + "title", movie.getGenreName());
            stmt.registerOutParameter("@" + "genreId", Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt("@" + "genreId");
        }
    }

    @Override
    public List<Genre> selectGenres() throws SQLException {
        List<Genre> genres = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_GENRES);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                genres.add(new Genre(rs.getInt("Id"), 
                        rs.getString("GenreName")));
            }
        }
        return genres;
    }
}
