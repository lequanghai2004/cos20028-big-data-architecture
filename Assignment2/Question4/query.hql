DROP DATABASE IF EXISTS indigenous CASCADE;
CREATE DATABASE IF NOT EXISTS indigenous;
USE indigenous;

CREATE TABLE lng_id (lng_code STRING, a_lng_lat STRING, a_lng_lng STRING, lng_uri STRING);
CREATE TABLE lng_name (lng_name STRING);
CREATE TABLE lng_synonym (lng_synonym STRING);
CREATE TABLE lng_thl (lng_thl STRING);
CREATE TABLE lng_thp (lng_thp STRING);
CREATE TABLE lng_st (lng_st STRING);

CREATE TABLE rel_code_name (lng_code STRING, name STRING);
CREATE TABLE rel_code_synonym (lng_code STRING, synonym STRING);
CREATE TABLE rel_code_thl (lng_code STRING, thl STRING);
CREATE TABLE rel_code_thp (lng_code STRING, thp STRING);
CREATE TABLE rel_code_st (lng_code STRING, st STRING);

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