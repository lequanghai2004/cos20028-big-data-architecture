ratings2012 = LOAD 'assignment2/ratings_2012.txt'
    USING PigStorage('\t')
    AS (datetime:chararray, id1:int, id2:int, rating:int, comment:chararray);
ratings2013 = LOAD 'assignment2/ratings_2013.txt'
    USING PigStorage('\t')
    AS (datetime:chararray, id1:int, id2:int, rating:int, comment:chararray);

ratings2012_with_line = RANK ratings2012;
ratings2013_with_line = RANK ratings2013;

defective2012 = FILTER ratings2012_with_line BY comment MATCHES '.*Shoddy.*' OR comment MATCHES '.*Item was defective.*';
defective2013 = FILTER ratings2013_with_line BY comment MATCHES '.*Shoddy.*' OR comment MATCHES '.*Item was defective.*';

defective2012_tagged = FOREACH defective2012 GENERATE 'ratings_2012.txt' AS file, rank_ratings2012 AS line, comment;
defective2013_tagged = FOREACH defective2013 GENERATE 'ratings_2013.txt' AS file, rank_ratings2013 AS line, comment;

all_defective = UNION defective2012_tagged, defective2013_tagged;

DUMP all_defective;