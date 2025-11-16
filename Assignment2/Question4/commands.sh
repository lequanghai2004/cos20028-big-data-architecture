javac -classpath `hadoop classpath` Question4/*.java;
jar cvf wc4.jar Question4/*.class;

hadoop jar wc4.jar Question4.Driver 1 assignment2/austlang_dataset_nh.txt assignment2/austlang_rdb/rel_code_name;
hadoop jar wc4.jar Question4.Driver 2 assignment2/austlang_dataset_nh.txt assignment2/austlang_rdb/rel_code_synonym;
hadoop jar wc4.jar Question4.Driver 3 assignment2/austlang_dataset_nh.txt assignment2/austlang_rdb/rel_code_thl;
hadoop jar wc4.jar Question4.Driver 4 assignment2/austlang_dataset_nh.txt assignment2/austlang_rdb/rel_code_thp;
hadoop jar wc4.jar Question4.Driver 7 assignment2/austlang_dataset_nh.txt assignment2/austlang_rdb/rel_code_st;

pig Assignment2Part1.pig
hive -f Assignment2Part2.hql

hive -f /Question4/query.sql
mysql --user=training --password=training < Question4/query.sql

for table in lng_id  rel_code_name rel_code_synonym rel_code_thl rel_code_thp rel_code_st; do
  hdfs dfs -cat /user/training/assignment2/austlang_rdb/$table/part-m-00000 | wc -l
done

lng_name lng_thl lng_thp lng_st

sqoop export \
  --connect "jdbc:mysql://localhost/indigenous" \
  --username training \
  --password training \
  --table lng_id \
  --export-dir /user/training/assignment2/austlang_rdb/lng_id \
  --fields-terminated-by '\t' \
  --m 1
sqoop export \
  --connect "jdbc:mysql://localhost/indigenous" \
  --username training \
  --password training \
  --table lng_name \
  --export-dir /user/training/assignment2/austlang_rdb/lng_name \
  --fields-terminated-by '\t' \
  --m 1

sqoop export \
  --connect "jdbc:mysql://localhost/indigenous" \
  --username training \
  --password training \
  --table lng_synonym \
  --export-dir /user/training/assignment2/austlang_rdb/lng_synonym\
  --fields-terminated-by '\t' \
  --m 1

sqoop export \
  --connect "jdbc:mysql://localhost/indigenous" \
  --username training \
  --password training \
  --table lng_thl \
  --export-dir /user/training/assignment2/austlang_rdb/lng_thl \
  --fields-terminated-by '\t' \
  --m 1
sqoop export \
  --connect "jdbc:mysql://localhost/indigenous" \
  --username training \
  --password training \
  --table lng_thp \
  --export-dir /user/training/assignment2/austlang_rdb/lng_thp\
  --fields-terminated-by '\t' \
  --m 1
sqoop export \
  --connect "jdbc:mysql://localhost/indigenous" \
  --username training \
  --password training \
  --table lng_st \
  --export-dir /user/training/assignment2/austlang_rdb/lng_st \
  --fields-terminated-by '\t' \
  --m 1


sqoop export \
  --connect "jdbc:mysql://localhost/indigenous" \
  --username training \
  --password training \
  --table rel_code_name \
  --columns lng_code,lng_name \
  --export-dir /user/training/assignment2/austlang_rdb/rel_code_name \
  --fields-terminated-by '\t' \
  --m 1
sqoop export \
  --connect "jdbc:mysql://localhost/indigenous" \
  --username training \
  --password training \
  --table rel_code_synonym \
  --columns lng_code,lng_synonym \
  --export-dir /user/training/assignment2/austlang_rdb/rel_code_synonym \
  --fields-terminated-by '\t' \
  --m 1
sqoop export \
  --connect "jdbc:mysql://localhost/indigenous" \
  --username training \
  --password training \
  --table rel_code_thl \
  --columns lng_code,lng_thl \
  --export-dir /user/training/assignment2/austlang_rdb/rel_code_thl \
  --fields-terminated-by '\t' \
  --m 1
sqoop export \
  --connect "jdbc:mysql://localhost/indigenous" \
  --username training \
  --password training \
  --table rel_code_thp \
  --columns lng_code,lng_thp \
  --export-dir /user/training/assignment2/austlang_rdb/rel_code_thp \
  --fields-terminated-by '\t' \
  --m 1
sqoop export \
  --connect "jdbc:mysql://localhost/indigenous" \
  --username training \
  --password training \
  --table rel_code_st \
  --columns lng_code,lng_st \
  --export-dir /user/training/assignment2/austlang_rdb/rel_code_st \
  --fields-terminated-by '\t' \
  --m 1
