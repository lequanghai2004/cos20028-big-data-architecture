DROP DATABASE IF EXISTS assignment2_part2 CASCADE;
CREATE DATABASE IF NOT EXISTS assignment2_part2;
USE assignment2_part2;

CREATE TABLE lng_id (lng_code STRING, a_lng_lat STRING, a_lng_lng STRING, lng_uri STRING);
LOAD DATA INPATH 'assignment2/austlang_rdb/lng_id' OVERWRITE INTO TABLE lng_id;

CREATE TABLE lng_name (lng_name STRING);
LOAD DATA INPATH 'assignment2/austlang_rdb/lng_name' OVERWRITE INTO TABLE lng_name;

CREATE TABLE lng_synonym (lng_synonym STRING);
LOAD DATA INPATH 'assignment2/austlang_rdb/lng_synonym' OVERWRITE INTO TABLE lng_synonym;

CREATE TABLE lng_thl (lng_thl STRING);
LOAD DATA INPATH 'assignment2/austlang_rdb/lng_thl' OVERWRITE INTO TABLE lng_thl;

CREATE TABLE lng_thp (lng_thp STRING);
LOAD DATA INPATH 'assignment2/austlang_rdb/lng_thp' OVERWRITE INTO TABLE lng_thp;

CREATE TABLE lng_st (lng_st STRING);
LOAD DATA INPATH 'assignment2/austlang_rdb/lng_st' OVERWRITE INTO TABLE lng_st;

CREATE TABLE rel_code_name AS
    SELECT a.lng_code, b.lng_name
    FROM lng_id a
    JOIN lng_name b ON a.lng_code = b.lng_code;
