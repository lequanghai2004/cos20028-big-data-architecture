-- Load the full ratings data with all fields
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
    comment;

ratings_filtered = FILTER ratings_with_location
    BY comment MATCHES '.*Shoddy.*' 
    OR comment MATCHES '.*Item was defective.*';

-- Group by file to prepare for ranking
ratings_grouped = GROUP ratings_filtered BY file;

-- Add line numbers within each file group based on time ordering
rating_ordered = FOREACH ratings_grouped {
    ordered = ORDER ratings_with_location BY time ASC;
    GENERATE FLATTEN(ranked) AS (line:int, file:chararray, time:chararray, rating:int, comment:chararray);
};

ratings_numbered = FOREACH ratings_grouped {
    ranked = RANK(ordered);
    GENERATE FLATTEN(ranked) AS (line:int, file:chararray, time:chararray, comment:chararray);
};

-- Dump the final result
DUMP rating_numbered;