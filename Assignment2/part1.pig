ratings2012 = LOAD 'assignment2/ratings_2012.txt'
    USING PigStorage('\t')
    AS (time:chararray, id1:int, id2:int, rating:int, comment:chararray);
ratings2013 = LOAD 'assignment2/ratings_2013.txt'
    USING PigStorage('\t')
    AS (time:chararray, id1:int, id2:int, rating:int, comment:chararray);

ratings2012_ordered = ORDER ratings2012 BY time ASC;
ratings2013_ordered = ORDER ratings2013 BY time ASC;

ratings2012_numbered = RANK ratings2012;
ratings2013_numbered = RANK ratings2013;

ratings2012_filtered = FILTER ratings2012_numbered
    BY comment MATCHES '.*Shoddy.*' OR comment MATCHES '.*Item was defective.*';
ratings2013_filtered = FILTER ratings2013_numbered
    BY comment MATCHES '.*Shoddy.*' OR comment MATCHES '.*Item was defective.*';


ratings2012_tagged = FOREACH ratings2012_filtered GENERATE
    CONCAT('line ', (chararray)rank_ratings2012) AS line,
    'file ratings_2012.txt' AS file,
    comment;
ratings2013_tagged = FOREACH ratings2013_filtered GENERATE
    CONCAT('line ', (chararray)rank_ratings2013) AS line,
    'file ratings_2013.txt' AS file,
    comment;

result = ORDER (UNION ratings2012_tagged, ratings2013_tagged) BY comment;
DUMP result;