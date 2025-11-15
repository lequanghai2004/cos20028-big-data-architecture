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
/*
    Schema:
    (
        time:chararray,
        id1:int,
        id2:int,
        rating:int,
        comment:chararray
    )
*/

-- Extract year from time
cleaned = FOREACH raw GENERATE
    SUBSTRING(time, 0, 4) AS year,
    time,
    rating,
    comment;
/* 
    Schema:
    (
        year:chararray,
        time:chararray,
        rating:int,
        comment:chararray
    )
*/

ordered = ORDER cleaned BY year, time ASC;

numbered = FOREACH (GROUP ordered ALL) {
    ranked_data = RANK ordered;
    GENERATE FLATTEN(ranked_data);
};

DUMP numbered;