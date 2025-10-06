from pyspark import SparkConf, SparkContext

'''
    Example of using RDDs to compute average movie ratings.
    The dataset used is the MovieLens 100K dataset, which can be found at: https://grouplens.org/datasets/movielens/100k/
    The dataset consists of two files:
    - u.data: user_id | movie_id | rating | timestamp
    - u.item: movie_id | movie_title | release_date | video_release_date | IMDb_URL | [genre flags]
'''

if __name__ == "__main__":
    spark_config = SparkConf().setAppName("MovieRating")
    spark_context = SparkContext(conf=spark_config)

    # Load data in recillable distributed dataset
    movies = spark_context.textFile("ml-100k/movies")
    ratings = spark_context.textFile("ml-100k/ratings")

    # Convert to (movie_id, (rating, 1)) pairs
    movie_ratings = ratings \
        .map(lambda line: line.split('\t')) \
        .map(lambda fields: (int(fields[1]), (float(fields[2]), 1)))

    # Reduce to (movie_id, (total_rating, total_count)) pairs
    movie_rating_totals_and_counts = movie_ratings \
        .reduceByKey(lambda m1, m2: (m1[0] + m2[0], m1[1] + m2[1]))
    
    # Map to (movie_id, average_rating) pairs
    movie_average_ratings = movie_rating_totals_and_counts \
        .mapValues(lambda total_and_count: total_and_count[0] / total_and_count[1])
    
    # Sort by average rating in descending order
    movie_sorted_average_ratings = movie_average_ratings \
        .sortBy(lambda pair: pair[1], ascending=False)
    
    # Print the top 10 movies with highest average ratings with their titles
    movie_titles = movies \
        .map(lambda line: line.split('|')) \
        .map(lambda fields: (int(fields[0]), fields[1])) \
        .collectAsMap()
    for (movie_id, avg_rating) in movie_sorted_average_ratings.take(10):
        print(f"{movie_titles[movie_id]}: {avg_rating:.2f}") 

