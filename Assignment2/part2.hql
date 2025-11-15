DROP DATABASE IF EXISTS assignment2_part2 CASCADE;
CREATE DATABASE IF NOT EXISTS assignment2_part2;
USE assignment2_part2;
CREATE TABLE language_codes (language_code STRING);
LOAD DATA INPATH 'assignment2/austlang_rdb/lng_id' OVERWRITE INTO TABLE language_codes;

SELECT language_code, COUNT(*) AS duplicate_count
    FROM language_codes
    GROUP BY language_code
    HAVING duplicate_count > 1;
