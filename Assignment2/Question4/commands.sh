javac -classpath `hadoop classpath` Question4/*.java
jar cvf wc.jar Question4/*.class

hadoop jar wc.jar Question4.Driver 1 assignment2/austlang_dataset_nh.txt assignment2/austlang_rdb/rel_code_name
hadoop jar wc.jar Question4.Driver 2 assignment2/austlang_dataset_nh.txt assignment2/austlang_rdb/rel_code_synonym
hadoop jar wc.jar Question4.Driver 3 assignment2/austlang_dataset_nh.txt assignment2/austlang_rdb/rel_code_thl
hadoop jar wc.jar Question4.Driver 4 assignment2/austlang_dataset_nh.txt assignment2/austlang_rdb/rel_code_thp
hadoop jar wc.jar Question4.Driver 7 assignment2/austlang_dataset_nh.txt assignment2/austlang_rdb/rel_code_st

pig Assignment2Part1.pig
hive -f Assignment2Part2.hql