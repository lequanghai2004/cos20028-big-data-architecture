from mrjob.job import MRJob
from mrjob.step import MRStep


class MovieRating(MRJob):
    def mapper(self, _, line):
        try:
            user_id, movie_id, rating, time = line.split(',')
            yield movie_id, (float(rating), 1)
        except ValueError:
            pass  # Skip lines with incorrect format

    def combiner(self, movie_id, rating_count_pairs):
        total_rating = 0
        total_count = 0
        for rating, count in rating_count_pairs:
            total_rating += rating
            total_count += count
        yield movie_id, (total_rating, total_count)

    def reducer(self, movie_id, rating_count_pairs):
        total_rating = 0
        total_count = 0
        for rating, count in rating_count_pairs:
            total_rating += rating
            total_count += count
        if total_count > 0:
            average_rating = total_rating / total_count
            yield movie_id, average_rating

    def steps(self):
        return [MRStep(self.mapper, self.combiner, self.reducer)]

# This script calculates the average movie rating using MapReduce paradigm.
# It reads input data in the format user_id,movie_id,rating,time.
# It outputs the average rating for each movie_id.
# To run the script, use the command: python mapreduce.py input.txt > output.txt
if __name__ == '__main__':
    MovieRating.run()
