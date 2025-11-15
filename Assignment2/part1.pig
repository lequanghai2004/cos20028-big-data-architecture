-- Load full ratings data
raw = LOAD 'assignment2/ratings_*.txt'
    USING PigStorage('\t')
    AS (
        time:chararray,
        id1:int,
        id2:int,
        rating:int,
        comment:chararray
    );

-- Extract year from time
with_year = FOREACH raw GENERATE
    SUBSTRING(time, 0, 4) AS year,
    time,
    rating,
    comment;

-- Order records within each year by time
grouped_by_year = GROUP ratings_with_year BY year;

ranked = FOREACH grouped_by_year {
    ordered = ORDER ratings_with_year BY time ASC;
    GENERATE FLATTEN(ordered);
};

DUMP ranked;