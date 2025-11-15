-----------------------------------------------------------------------

lng_name = FOREACH raw GENERATE language_name;

--------------------------------------------------------------------------------

lng_synonym = FOREACH raw GENERATE language_synonym;
lng_thl = FOREACH raw GENERATE thesaurus_heading_language;
lng_thp = FOREACH raw GENERATE thesaurus_heading_people;
lng_st = FOREACH raw GENERATE state_territory;

rel_code_name = FOREACH raw GENERATE
    language_code AS lng_code,
    language_name AS lng_name;

rel_code_name_ranked = RANK rel_code_name;

rel_code_name_with_id = FOREACH rel_code_name_ranked GENERATE
    rank_rel_code_name AS IDKey,
    lng_code,
    lng_name;

--------------------------------------------------------------------------------

rel_code_synonym
rel_code_thl
rel_code_thp
rel_code_st
