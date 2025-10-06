from pyspark.sql import SparkSession
from pyspark.sql import Row
from pyspark.sql import functions

if __name__ == "__main__":
    spark = SparkSession.builder.appName("Popular Movie").getOrCreate()

    # Load the data
    lines = spark.sparkContext.textFile("ml-100k/u.data")

    # Convert to a RRD of Row objects with (user_id, movie_id, rating)
    movies = lines \
        .map(lambda line: line.split("\t")) \
        .map(lambda fields: Row(movie_id=int(fields[1]), rating=float(fields[2])))
    
    # Create a DataFrame from the RDD
    movie_df = spark.createDataFrame(movies)

    # Compute average rating for each movie
    average_ratings = movie_df \
        .groupBy("movie_id") \
        .avg("rating") \
        .withColumnRenamed("avg(rating)", "average_rating")
    
    # Compute the number of ratings for each movie
    rating_counts = movie_df \
        .groupBy("movie_id") \
        .count()
    
    # Join average ratings with rating counts
    movies_with_average_and_counts = average_ratings \
        .join(rating_counts, "movie_id") \
        .orderBy(functions.desc("average_rating"))
    
    # Show the results
    movies_with_average_and_counts.show()