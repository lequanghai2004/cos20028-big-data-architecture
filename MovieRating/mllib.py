from pyspark.sql import SparkSession, Row
from pyspark.ml.recommendation import ALS
from pyspark.sql.functions import lit

if __name__ == "__main__":
    spark = SparkSession.builder.appName("Movie Recommendation").getOrCreate()

    # Load the data
    movies_data = spark.sparkContext.textFile("ml-100k/movies")
    ratings_data = spark.sparkContext.textFile("ml-100k/ratings")

    # Parse to get (movieID, movieName) pairs
    movies_rdd = movies_data \
        .map(lambda line: line.split("|")) \
        .map(lambda tokens: Row(movieId=int(tokens[0]), movieName=tokens[1]))
    
    # Parse to get (userID, movieID, rating) tuples
    ratings_rdd = ratings_data \
        .map(lambda line: line.split("\t")) \
        .map(lambda tokens: Row(userId=int(tokens[0]), movieId=int(tokens[1]), rating=float(tokens[2])))
    
    # Convert RDDs to DataFrames
    movies_df = spark.createDataFrame(movies_rdd)
    ratings_df = spark.createDataFrame(ratings_rdd)

    # Train the ALS model
    als = ALS(maxIter=5, regParam=0.01, userCol="user_id", itemCol="movie_id", ratingCol="rating", coldStartStrategy="drop")
    model = als.fit(ratings_df)

    ######################################################################
    ## Example: Get recommendations for a specific user (userId = 0) #####
    ######################################################################

    user_ratings = ratings_rdd \
        .filter(lambda r: r.userId == 0) \
        .collect()
    for r in user_ratings:
        print(f"User 0 rated movie {r.movieId} with rating {r.rating}")

    popular_movies = ratings_df \
        .groupBy("movie_id") \
        .count() \
        .filter("count > 100") \
        .select("movie_id") \
        .withColumn("movie_id")

    recommandations = model \
        .transform(popular_movies, lit(0)) \
        .sort("prediction", ascending=False) \
        .take(20)