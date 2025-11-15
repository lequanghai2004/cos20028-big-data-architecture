-- Load full ratings data
ratings_full = LOAD '/assignment2/ratings_*.txt'
    USING PigStorage('\t')
    AS (
        time:chararray,
        id1:int,
        id2:int,
        rating:int,
        comment:chararray
    );

-- Extract necessary fields and add file location
ratings_with_location = FOREACH ratings_full GENERATE
    CONCAT('ratings_', SUBSTRING(time, 0, 4), '.txt') AS file,
    time,
    rating,
    comment;

-- Filter comments
ratings_filtered = FILTER ratings_with_location
    BY (comment MATCHES '.*Shoddy.*' OR comment MATCHES '.*Item was defective.*');

-- Group by file
ratings_grouped = GROUP ratings_filtered BY file;

-- Order and rank within each group
ratings_numbered = FOREACH ratings_grouped {
    ordered = ORDER ratings_filtered BY time ASC;
    ranked = RANK(ordered);
    GENERATE FLATTEN(ranked) AS (line:int, file:chararray, time:chararray, rating:int, comment:chararray);
};

-- Dump final result
DUMP ratings_numbered;