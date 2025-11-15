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

-- Order within each group
ordered = FOREACH grouped {
    sorted = ORDER cleaned BY time ASC;
    GENERATE group AS year, sorted;
};

DUMP ranked;