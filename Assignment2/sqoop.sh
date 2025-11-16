sqoop export --connect "jdbc:mysql://localhost/indigenous" --username training --password training --table lng_id --export-dir /user/training/assignment2/austlang_rdb/lng_id --fields-terminated-by '\t' --m 1;
sqoop export --connect "jdbc:mysql://localhost/indigenous" --username training --password training --table lng_name --columns lng_name  --export-dir /user/training/assignment2/austlang_rdb/lng_name --fields-terminated-by '\t' --m 1;
sqoop export --connect "jdbc:mysql://localhost/indigenous" --username training --password training --table lng_synonym --columns lng_synonym  --export-dir /user/training/assignment2/austlang_rdb/lng_synonym --fields-terminated-by '\t' --m 1;
sqoop export --connect "jdbc:mysql://localhost/indigenous" --username training --password training --table lng_thl --columns lng_thl  --export-dir /user/training/assignment2/austlang_rdb/lng_thl --fields-terminated-by '\t' --m 1;
sqoop export --connect "jdbc:mysql://localhost/indigenous" --username training --password training --table lng_thp --columns lng_thp  --export-dir /user/training/assignment2/austlang_rdb/lng_thp --fields-terminated-by '\t' --m 1;
sqoop export --connect "jdbc:mysql://localhost/indigenous" --username training --password training --table lng_st --columns lng_st  --export-dir /user/training/assignment2/austlang_rdb/lng_st --fields-terminated-by '\t' --m 1;
sqoop export --connect "jdbc:mysql://localhost/indigenous" --username training --password training --table rel_code_name --columns lng_code,lng_name --export-dir /user/training/assignment2/austlang_rdb/rel_code_name --fields-terminated-by '\t' --m 1;
sqoop export --connect "jdbc:mysql://localhost/indigenous" --username training --password training --table rel_code_synonym --columns lng_code,lng_synonym --export-dir /user/training/assignment2/austlang_rdb/rel_code_synonym --fields-terminated-by '\t' --m 1;
sqoop export --connect "jdbc:mysql://localhost/indigenous" --username training --password training --table rel_code_thl --columns lng_code,lng_thl --export-dir /user/training/assignment2/austlang_rdb/rel_code_thl --fields-terminated-by '\t' --m 1;
sqoop export --connect "jdbc:mysql://localhost/indigenous" --username training --password training --table rel_code_thp --columns lng_code,lng_thp --export-dir /user/training/assignment2/austlang_rdb/rel_code_thp --fields-terminated-by '\t' --m 1;
sqoop export --connect "jdbc:mysql://localhost/indigenous" --username training --password training --table rel_code_st --columns lng_code,lng_st --export-dir /user/training/assignment2/austlang_rdb/rel_code_st --fields-terminated-by '\t' --m 1;


# cat hdfs dfs -cat /user/training/assignment2/austlang_rdb/lng_synonym/part-00000
# mysql --user=training --password=training indigenous 
# INSERT INTO lng_synonym (lng_synonym) VALUES ();

# hdfs dfs -cat /user/training/assignment2/austlang_rdb/lng_synonym/part-r-00000 | while read line; do
#   echo "INSERT INTO lng_synonym (lng_synonym) VALUES ('${line}');"
# done | mysql --user=training --password=training indigenous


# hdfs dfs -cat /user/training/assignment2/austlang_rdb/lng_synonym/part-r-00000 | while read line; do
#   safe_line=$(echo "$line" | sed "s/'/''/g")
#   echo "INSERT INTO lng_synonym (lng_synonym) VALUES ('$safe_line');"
# done | mysql --user=training --password=training indigenous
