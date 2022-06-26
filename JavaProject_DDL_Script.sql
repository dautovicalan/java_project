--Admin, 123456789, SQL Login Creds
CREATE DATABASE JavaProject
USE JavaProject

CREATE TABLE UserRoles
(
	Id INT PRIMARY KEY IDENTITY(1,1),
	RoleName NVARCHAR(50) NOT NULL
)

CREATE TABLE Movie
(
	Id INT PRIMARY KEY IDENTITY(1,1),
	Title NVARCHAR(250) NOT NULL,
	PubDate NVARCHAR(90) NOT NULL,
	MovieDescription NVARCHAR(MAX) NOT NULL,
	OrigTitle NVARCHAR(250) NOT NULL,
	Duration INT NOT NULL,
	MovieGenreId INT FOREIGN KEY REFERENCES MovieGenre(Id),
	MoviePicturePath NVARCHAR(MAX) NULL,
	MovieLink NVARCHAR(MAX) NULL,
	MovieBegin NVARCHAR(90) NULL
)

ALTER PROC selectMovies
AS
BEGIN
	SELECT Movie.Id, Movie.Title, Movie.PubDate, Movie.MovieDescription, Movie.Duration, Movie.MoviePicturePath,
	Movie.MovieBegin, MovieGenre.Id AS GenreId, MovieGenre.GenreName AS GenreName
	FROM Movie
	INNER JOIN MovieGenre ON MovieGenre.Id = Movie.MovieGenreId
END

ALTER PROC selectMovie
	@movieId INT
AS
BEGIN
	SELECT Movie.Id, Movie.Title, Movie.PubDate, Movie.MovieDescription, Movie.Duration, Movie.MoviePicturePath,
	Movie.MovieBegin, MovieGenre.Id AS GenreId, MovieGenre.GenreName AS GenreName
	FROM Movie
	INNER JOIN MovieGenre ON MovieGenre.Id = Movie.MovieGenreId
	WHERE Movie.Id = @movieId
END

ALTER PROC createMovie
	@title NVARCHAR(250),
	@pubDate NVARCHAR(90),
	@description NVARCHAR(MAX),
	@duration INT,
	@movieBegin NVARCHAR(90),
	@moviePicturePath NVARCHAR(MAX),
	@genreId INT,
	@movieId INT OUTPUT
AS
BEGIN
	INSERT INTO Movie(Title, PubDate, MovieDescription, Duration, MovieBegin, MoviePicturePath, MovieGenreId)
	VALUES (@title, @pubDate, @description, @duration, @movieBegin, @moviePicturePath, @genreId)

	SET @movieId = SCOPE_IDENTITY()
END

ALTER PROC createGenre
	@title NVARCHAR(250),
	@genreId INT OUTPUT
AS
BEGIN
	IF EXISTS(SELECT * FROM MovieGenre WHERE GenreName = @title)
		BEGIN
			SELECT @genreId = Id FROM MovieGenre WHERE GenreName = @title
		END
	ELSE
		BEGIN
			INSERT INTO MovieGenre
			VALUES (@title)

			SET @genreId = SCOPE_IDENTITY()
		END
END

CREATE PROC selectGenres
AS
BEGIN
	SELECT * FROM MovieGenre
END

ALTER PROC updateMovie
	@title NVARCHAR(250),
	@pubDate NVARCHAR(90),
	@description NVARCHAR(MAX),
	@duration INT,
	@movieBegin NVARCHAR(90),
	@moviePicturePath NVARCHAR(MAX),
	@genreId INT,
	@movieId INT
AS
BEGIN
	UPDATE Movie
	SET Title = @title, MovieGenreId = @genreId, PubDate = @pubDate, MovieDescription = @description, Duration = @duration, MovieBegin = @movieBegin, MoviePicturePath = @moviePicturePath
	WHERE Id = @movieId
END

CREATE PROCEDURE deleteMovie
	@movieId INT	 
AS 
BEGIN 
	DELETE  
	FROM Movie
	WHERE Id = @movieId
END

CREATE TABLE MovieGenre
(
	Id INT PRIMARY KEY IDENTITY(1,1),
	GenreName NVARCHAR(50) NOT NULL
)

CREATE TABLE Actor
(
	Id INT PRIMARY KEY IDENTITY(1,1),
	FirstName NVARCHAR(25) NOT NULL,
	LastName NVARCHAR(25) NOT NULL
)

ALTER PROC selectActors
AS
BEGIN
	SELECT DISTINCT * FROM Actor GROUP BY Id, FirstName, LastName
END

CREATE PROC selectActor
	@actorId INT
AS
BEGIN
	SELECT * FROM Actor WHERE Id = @actorId
END

ALTER PROC createActor
	@firstName NVARCHAR(25),
	@lastName NVARCHAR(25),
	@actorId INT OUTPUT
AS
BEGIN
	IF EXISTS(SELECT * FROM Actor WHERE FirstName LIKE @firstName AND LastName LIKE @lastName)
		BEGIN
			SELECT @actorId = Id FROM Actor WHERE FirstName LIKE @firstName AND LastName LIKE @lastName
		END
	ELSE
		BEGIN
			INSERT INTO Actor
			VALUES (@firstName, @lastName)

			SET @actorId = SCOPE_IDENTITY()
		END
END

CREATE PROC updateActor
	@firstName NVARCHAR(25),
	@lastName NVARCHAR(25),
	@actorId INT
AS
BEGIN
	UPDATE Actor
	SET FirstName = @firstName, LastName = @lastName
	WHERE Id = @actorId
END

CREATE PROCEDURE deleteActor
	@actorId INT	 
AS 
BEGIN 
	DELETE  
	FROM Actor
	WHERE Id = @actorId
END

CREATE TABLE Director
(
	Id INT PRIMARY KEY IDENTITY(1,1),
	FirstName NVARCHAR(25) NOT NULL,
	LastName NVARCHAR(25) NOT NULL
)

CREATE PROC selectDirectors
AS
BEGIN
	SELECT * FROM Director
END

CREATE PROC selectDirector
	@directorId INT
AS
BEGIN
	SELECT * FROM Director WHERE Id = @directorId
END

CREATE PROC createDirector
	@firstName NVARCHAR(25),
	@lastName NVARCHAR(25),
	@directorId INT OUTPUT
AS
BEGIN
	INSERT INTO Director
	VALUES (@firstName, @lastName)

	SET @directorId = SCOPE_IDENTITY()
END

CREATE PROC updateDirector
	@firstName NVARCHAR(25),
	@lastName NVARCHAR(25),
	@directorId INT
AS
BEGIN
	UPDATE Director
	SET FirstName = @firstName, LastName = @lastName
	WHERE Id = @directorId
END

CREATE PROCEDURE deleteDirector
	@directorId INT	 
AS 
BEGIN 
	DELETE  
	FROM Director
	WHERE Id = @directorId
END

CREATE TABLE MovieCast
(
	Id INT PRIMARY KEY IDENTITY(1,1),
	MovieId INT FOREIGN KEY REFERENCES Movie(Id),
	ActorId INT FOREIGN KEY REFERENCES Actor(Id)
)

CREATE TABLE AppUser
(
	Id INT PRIMARY KEY IDENTITY(1,1),
	UserName NVARCHAR(20) UNIQUE NOT NULL,
	UserPassword NVARCHAR(MAX) NOT NULL
)

ALTER PROC registerUser
	@username NVARCHAR(20),
	@userPassword NVARCHAR(MAX),
	@userId INT OUTPUT
AS
BEGIN
	IF EXISTS(SELECT * FROM AppUser WHERE UserName = @username)
		BEGIN
			SET @userId = -1
		END
	ELSE
		BEGIN
			INSERT INTO AppUser
			VALUES(@username, @userPassword)

			SET @userId = SCOPE_IDENTITY()
		END
END

CREATE PROC authUser
	@username NVARCHAR(20),
	@userPassword NVARCHAR(MAX)
AS
BEGIN
	SELECT * FROM AppUser WHERE UserName = @username AND UserPassword = @userPassword
END

CREATE TABLE AppAdmin
(
	Id INT PRIMARY KEY IDENTITY(1,1),
	UserName NVARCHAR(20) UNIQUE NOT NULL,
	UserPassword NVARCHAR(20) NOT NULL
)

CREATE PROC authAdmin
	@username NVARCHAR(20),
	@userPassword NVARCHAR(MAX)
AS
BEGIN
	SELECT * FROM AppAdmin WHERE UserName = @username AND UserPassword = @userPassword
END

CREATE TABLE MovieCast
(
	ActorId INT FOREIGN KEY REFERENCES Actor(Id),
	DirectorId INT FOREIGN KEY REFERENCES Director(Id),
	MovieId INT FOREIGN KEY REFERENCES Movie(Id)
)

CREATE PROC createCast
	@actorId INT,
	@directorId INT,
	@movieId INT
AS
BEGIN
	INSERT INTO MovieCast
	VALUES(@actorId, @directorId, @movieId)
END

CREATE PROC createCastActor
	@actorId INT,
	@movieId INT
AS
BEGIN
	INSERT INTO MovieCast (ActorId, MovieId)
	VALUES(@actorId, @movieId)
END

CREATE PROC createCastDirector
	@directorId INT,
	@movieId INT
AS
BEGIN
	INSERT INTO MovieCast (DirectorId, MovieId)
	VALUES(@directorId, @movieId)
END

ALTER PROC selectMovieCastActor
	@movieId INT
AS
BEGIN
	SELECT Actor.Id, Actor.FirstName, Actor.LastName
	FROM MovieCast
	INNER JOIN Actor ON Actor.Id = MovieCast.ActorId
	WHERE MovieId = @movieId
END

CREATE PROC deleteActorFromMovie
	@movieId INT,
	@actorId INT
AS
BEGIN
	DELETE FROM MovieCast
	WHERE MovieId = @movieId AND ActorId = @actorId
END

CREATE PROC deleteAllDBData
AS
BEGIN
	-- disable referential integrity
	EXEC sp_MSForEachTable 'ALTER TABLE ? NOCHECK CONSTRAINT ALL' 

	EXEC sp_MSForEachTable 'DELETE FROM ?' 

	-- enable referential integrity again 
	EXEC sp_MSForEachTable 'ALTER TABLE ? WITH CHECK CHECK CONSTRAINT ALL' 
END

CREATE PROC createInitAppAdmin
AS
BEGIN
	INSERT INTO AppAdmin
	VALUES('admin', '123456789')
END

EXEC createInitAppAdmin

SELECT Movie.Title, MovieGenre.GenreName
FROM Movie
INNER JOIN MovieGenre ON MovieGenre.Id = Movie.MovieGenreId