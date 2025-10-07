from pyspark.sql import SparkSession, Row
from pyspark.ml.recommendation import ALS
from pyspark.sql.functions import lit

if __name__ == "__main__":
    spark = SparkSession.builder.appName("Movie Recommendation").getOrCreate()

    # Load the data
    movies = spark.sparkContext.textFile("ml-100k/movies")
    ratings = spark.sparkContext.textFile("ml-100k/ratings")