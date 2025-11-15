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
    temp = FOREACH ordered GENERATE
        year,
        time,
        rating,
        comment,
        RANK() AS rank;
    GENERATE FLATTEN(temp);
};

DUMP ranked;