raw = LOAD 'assignment2/ratings_*.txt'
    USING PigStorage('\t')
    AS (
        time:chararray,
        id1:int,
        id2:int,
        rating:int,
        comment:chararray
    );

ranked = RANK raw;

DUMP ranked;