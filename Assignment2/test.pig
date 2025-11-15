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
cleaned = FOREACH raw GENERATE
    SUBSTRING(time, 0, 4) AS year,
    time,
    rating,
    comment;

-- Group by year
grouped = GROUP cleaned BY year;

-- Order and rank within each year
ranked = FOREACH grouped {
    ordered = ORDER cleaned BY time ASC;
    ranked_data = FOREACH ordered GENERATE
        RANK() OVER (ORDER BY time ASC) AS line,
        year,
        time,
        rating,
        comment;
    GENERATE FLATTEN(ranked_data);
};

DUMP ranked;