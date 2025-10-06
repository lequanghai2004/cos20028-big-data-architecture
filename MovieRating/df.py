from pyspark.sql import SparkSession
from pyspark.sql import Row
from pyspark.sql import functions

if __name__ == "__main__":
    spark = SparkSession.builder.appName("Popular Movie").getOrCreate()

    # Load the data
    lines = spark.sparkContext.textFile("ml-100k/ratings")
    movies = spark.sparkContext.textFile("ml-100k/movies")

    # Convert to a RRD of Row objects with (user_id, movie_id, rating)
    ratings_rdd = lines \
        .map(lambda line: line.split("\t")) \
        .map(lambda fields: Row(movie_id=int(fields[1]), rating=float(fields[2])))
    
    # Create a DataFrame for ratings
    ratings_df = spark.createDataFrame(ratings_rdd, ["movie_id", "rating"])

    # Compute average rating for each movie
    average_ratings_df = ratings_df.groupBy("movie_id").avg("rating")

    # Convert to a RDD of Row objects with (movie_id, title)
    titles_rdd = movies \
        .map(lambda line: line.split("|")) \
        .map(lambda fields: Row(movie_id=int(fields[0]), title=fields[1]))

    # Create a DataFrame for titles
    titles_df = spark.createDataFrame(titles_rdd, ["movie_id", "title"])

    # Compute the count of ratings for each movie
    counts_df = ratings_df.groupBy("movie_id").count()

    # Join average ratings with titles, filter for movies with avg rating > 3.5
    popular_movies = average_ratings_df \
        .filter(average_ratings_df["avg(rating)"] > 3.5) \
        .join(titles_df, "movie_id") \
        .join(counts_df, "movie_id") \
        .select("title", "avg(rating)")

    # Sort by average rating in descending order
    sorted_popular_movies = popular_movies.orderBy(functions.desc("avg(rating)"))
    
    # Show the results
    sorted_popular_movies.show()