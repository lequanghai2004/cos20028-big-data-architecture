from mrjob.job import MRJob
from mrjob.step import MRStep


class MovieRating(MRJob):
    def mapper(self, _, line):
        # Assuming the input format is: user_id,movie_id,rating
        try:
            user_id, movie_id, rating = line.split(',')
            rating = float(rating)
            yield movie_id, (rating, 1)  # Emit movie_id with (rating, count)
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
        return [
            MRStep(
                mapper=self.mapper,
                combiner=self.combiner,
                reducer=self.reducer
            )
        ]
