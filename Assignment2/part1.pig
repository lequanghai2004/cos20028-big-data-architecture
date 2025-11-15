-- Load full ratings data
ratings_raw = LOAD 'assignment2/ratings_*.txt'
    USING PigStorage('\t')
    AS (
        time:chararray,
        id1:int,
        id2:int,
        rating:int,
        comment:chararray
    );

-- Extract year from time
ratings_with_year = FOREACH ratings_raw GENERATE
    SUBSTRING(time, 0, 4) AS year,
    time,
    rating,
    comment;

-- Order records within each year by time
ratings_grouped = GROUP ratings_with_year BY year;

-- Rank records within each year
ranked = FOREACH ratings_grouped {
    ordered = ORDER ratings_with_year BY time ASC;
    ranked_records = RANK ordered;
    GENERATE FLATTEN(ranked_records);
}

-- Add location column
final = FOREACH ranked GENERATE
    CONCAT('ratings_', year, '.txt') AS location,
    time,
    rating,
    comment,
