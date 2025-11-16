
DROP DATABASE IF EXISTS indigenous;
CREATE DATABASE indigenous;
USE indigenous

CREATE TABLE lng_id (
    lng_code VARCHAR(255) PRIMARY KEY COLLATE utf8mb4_bin,
    a_lng_lat VARCHAR(255),
    a_lng_lng VARCHAR(255),
    lng_uri VARCHAR(255)
);

CREATE TABLE lng_name (
    lng_name VARCHAR(255) PRIMARY KEY COLLATE utf8mb4_bin
);

CREATE TABLE lng_synonym (
    lng_synonym VARCHAR(255) PRIMARY KEY COLLATE utf8mb4_bin
);

CREATE TABLE lng_thl (
    lng_thl VARCHAR(255) PRIMARY KEY COLLATE utf8mb4_bin
);

CREATE TABLE lng_thp (
    lng_thp VARCHAR(255) PRIMARY KEY COLLATE utf8mb4_bin
);

CREATE TABLE lng_st (
    lng_st VARCHAR(255) PRIMARY KEY COLLATE utf8mb4_bin
);

CREATE TABLE rel_code_name (
    IDKey INT AUTO_INCREMENT PRIMARY KEY,
    lng_code VARCHAR(255),
    lng_name VARCHAR(255)
);

CREATE TABLE rel_code_synonym (
    IDKey INT AUTO_INCREMENT PRIMARY KEY,
    lng_code VARCHAR(255),
    lng_synonym VARCHAR(255)
);

CREATE TABLE rel_code_thl (
    IDKey INT AUTO_INCREMENT PRIMARY KEY,
    lng_code VARCHAR(255),
    lng_thl VARCHAR(255)
);

CREATE TABLE rel_code_thp (
    IDKey INT AUTO_INCREMENT PRIMARY KEY,
    lng_code VARCHAR(255),
    lng_thp VARCHAR(255)
);

CREATE TABLE rel_code_st (
    IDKey INT AUTO_INCREMENT PRIMARY KEY,
    lng_code VARCHAR(255),
    lng_st VARCHAR(255)
);