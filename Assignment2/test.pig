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

-- -- Order within each group
-- ordered = FOREACH grouped {
--     sorted = FOREACH (ORDER cleaned BY time ASC) GENERATE;
--     GENERATE group AS year, sorted;
-- };


ranked = FOREACH grouped {
    ordered = ORDER cleaned BY time ASC;
    numbered = FOREACH (RANK ordered) GENERATE rank AS seq, *;
    GENERATE group AS year, numbered;
};

DUMP ranked;