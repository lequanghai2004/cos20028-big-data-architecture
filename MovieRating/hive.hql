CREATE VIEW IF NOT EXISTS top_movie_ids AS
SELECT movie_id, COUNT(movie_id) AS rating_counts
FROM ratings
GROUP BY movie_id
ORDER BY rating_counts DESC;

SELECT movies.title, top_movie_ids.rating_counts
FROM top_movie_ids
JOIN movies 
ON top_movie_ids.movie_id = movies.id;

DROP VIEW top_movie_ids;

-------------------------------------------------------------------------------- 

CREATE TABLE ratings (
    user_id INT,
    movie_id INT,
    rating INT,
    time INT
) ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
STORED AS TEXTFILE;

LOAD DATA LOCAL INPATH 'ml-100k/ratings' INTO TABLE ratings;