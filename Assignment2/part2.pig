data = LOAD 'assignment2/austlang_dataset_nh.txt'
    USING PigStorage('\t')
    AS ( 
        language_code:chararray, -- Primary key
        language_name:chararray,
        language_synonym:chararray,
        thesaurus_heading_language:chararray,
        thesaurus_heading_people:chararray,
        approximate_latitude_of_language_variety:chararray,
        approximate_longitude_of_language_variety:chararray,
        state_territory:chararray,
        uri:chararray
    );

lng_id = FOREACH data GENERATE language_code;

STORE lng_id INTO 'assignment2/austlang_rdb/lng_id' USING PigStorage('\t');