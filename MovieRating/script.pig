-- Load movie ratings data from HDFS
movie_ratings = LOAD 'hdfs://user/data/movie_ratings.csv'
    AS (
        userId: int,
        movieId: int,
        rating: int,
        ratingTime: int);

-- Display the loaded data
DUMP movie_ratings LIMIT 10;

-- Load movie metadata from HDFS
movie_metadata = LOAD 'hdfs://user/data/movie_ratings.csv'
    USING PigStorage('|')
    AS (
        movieId: int,
        movieTitle: chararray,
        releaseDate: chararray,
        videoRelease: chararray,
        imdbLink: chararray);

-- Transform the movie metadata to extract year from release date
transformed_movie_metadata = FOREACH movie_metadata GENERATE (
    movieId, 
    movieTitle, 
    ToUnixTime(ToDate(releaseDate, 'dd-MMM-yyyy')) AS releaseTime);

-- Group movie ratings by movieId
grouped_movie_ratings = GROUP movie_ratings by movieId;

-- Calculate average ratings for each movie
average_movie_ratings = FOREACH grouped_movie_ratings GENERATE (
    group AS movieId,
    AVG(movie_ratings.rating) AS averageRating);

-- Describe the schemas of the relations
DESCRIBE movie_ratings;
DESCRIBE movie_metadata;
DESCRIBE transformed_movie_metadata;
DESCRIBE grouped_movie_ratings;
DESCRIBE average_movie_ratings;


-- Filter movies with average rating
five_star_movies = FILLTER average_movie_ratings BY averageRating > 5.0;
five_star_movies_full = JOIN five_star_movies BY movieId, transformed_movie_metadata BY movieId;
five_star_movies_sorted = ORDER five_star_movies_full BY averageRating DESC;


-- Store the results back to HDFS
STORE five_star_movies_sorted 
    INTO 'hdfs://user/data/five_star_movies' 
    USING PigStorage(',');