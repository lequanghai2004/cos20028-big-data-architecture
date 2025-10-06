SELECT movie_id, COUNT(movie_id) as rating_counts
FROM ratings
GROUP BY movie_id
ORDER BY rating_counts DESC;