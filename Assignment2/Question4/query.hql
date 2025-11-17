DROP DATABASE IF EXISTS indigenous CASCADE;
CREATE DATABASE IF NOT EXISTS indigenous;
USE indigenous;

CREATE TABLE lng_id (lng_code STRING, a_lng_lat STRING, a_lng_lng STRING, lng_uri STRING)
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' STORED AS TEXTFILE;
CREATE TABLE lng_name (lng_name STRING)
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' STORED AS TEXTFILE;
CREATE TABLE lng_synonym (lng_synonym STRING)
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' STORED AS TEXTFILE;
CREATE TABLE lng_thl (lng_thl STRING)
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' STORED AS TEXTFILE;
CREATE TABLE lng_thp (lng_thp STRING)
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' STORED AS TEXTFILE;
CREATE TABLE lng_st (lng_st STRING)
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' STORED AS TEXTFILE;
CREATE TABLE rel_code_name (lng_code STRING, lng_name STRING)
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' STORED AS TEXTFILE;
CREATE TABLE rel_code_synonym (lng_code STRING, lng_synonym STRING)
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' STORED AS TEXTFILE;
CREATE TABLE rel_code_thl (lng_code STRING, lng_thl STRING)
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' STORED AS TEXTFILE;
CREATE TABLE rel_code_thp (lng_code STRING, lng_thp STRING)
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' STORED AS TEXTFILE;
CREATE TABLE rel_code_st (lng_code STRING, lng_st STRING)
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' STORED AS TEXTFILE;

LOAD DATA INPATH 'assignment2/austlang_rdb/lng_id' OVERWRITE INTO TABLE lng_id;
LOAD DATA INPATH 'assignment2/austlang_rdb/lng_name' OVERWRITE INTO TABLE lng_name;
LOAD DATA INPATH 'assignment2/austlang_rdb/lng_synonym' OVERWRITE INTO TABLE lng_synonym;
LOAD DATA INPATH 'assignment2/austlang_rdb/lng_thl' OVERWRITE INTO TABLE lng_thl;
LOAD DATA INPATH 'assignment2/austlang_rdb/lng_thp' OVERWRITE INTO TABLE lng_thp;
LOAD DATA INPATH 'assignment2/austlang_rdb/lng_st' OVERWRITE INTO TABLE lng_st;
LOAD DATA INPATH 'assignment2/austlang_rdb/rel_code_name' OVERWRITE INTO TABLE rel_code_name;
LOAD DATA INPATH 'assignment2/austlang_rdb/rel_code_synonym' OVERWRITE INTO TABLE rel_code_synonym;
LOAD DATA INPATH 'assignment2/austlang_rdb/rel_code_thl' OVERWRITE INTO TABLE rel_code_thl;
LOAD DATA INPATH 'assignment2/austlang_rdb/rel_code_thp' OVERWRITE INTO TABLE rel_code_thp;
LOAD DATA INPATH 'assignment2/austlang_rdb/rel_code_st' OVERWRITE INTO TABLE rel_code_st;