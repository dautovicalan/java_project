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

CREATE PROC selectMovies
AS
BEGIN
	SELECT * FROM Movie
END

CREATE PROC selectMovie
	@movieId INT
AS
BEGIN
	SELECT * FROM Movie WHERE Id = @movieId
END

ALTER PROC createMovie
	@title NVARCHAR(250),
	@pubDate NVARCHAR(90),
	@description NVARCHAR(MAX),
	@duration INT,
	@movieBegin NVARCHAR(90),
	@moviePicturePath NVARCHAR(MAX),
	@movieId INT OUTPUT
AS
BEGIN
	INSERT INTO Movie(Title, PubDate, MovieDescription, Duration, MovieBegin, MoviePicturePath)
	VALUES (@title, @pubDate, @description, @duration, @movieBegin, @moviePicturePath)

	SET @movieId = SCOPE_IDENTITY()
END

CREATE PROC updateMovie
	@title NVARCHAR(250),
	@pubDate NVARCHAR(90),
	@description NVARCHAR(MAX),
	@duration INT,
	@movieBegin NVARCHAR(90),
	@moviePicturePath NVARCHAR(MAX),
	@movieId INT
AS
BEGIN
	UPDATE Movie
	SET Title = @title, PubDate = @pubDate, MovieDescription = @description, Duration = @duration, MovieBegin = @movieBegin, MoviePicturePath = @moviePicturePath
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

CREATE PROC selectActors
AS
BEGIN
	SELECT * FROM Actor
END

CREATE PROC selectActor
	@actorId INT
AS
BEGIN
	SELECT * FROM Actor WHERE Id = @actorId
END

CREATE PROC createActor
	@firstName NVARCHAR(25),
	@lastName NVARCHAR(25),
	@actorId INT OUTPUT
AS
BEGIN
	INSERT INTO Actor
	VALUES (@firstName, @lastName)

	SET @actorId = SCOPE_IDENTITY()
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

CREATE PROC selectMovieCast
	@movieId INT
AS
BEGIN
	SELECT Actor.FirstName AS ActorFirstName, Actor.LastName AS ActorLastName, Director.FirstName AS DirectorFirstName,
	Director.LastName AS DirectorLastName
	FROM MovieCast
	INNER JOIN Actor ON Actor.Id = MovieCast.ActorId
	INNER JOIN Director ON Director.Id = MovieCast.DirectorId
	WHERE MovieId = @movieId
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