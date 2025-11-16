javac -classpath `hadoop classpath` Question3/*.java
jar cvf wc3.jar Question3/*.class
hadoop jar wc3.jar Question3.Driver assignment2/austlang_dataset_nh.txt assignment2/austlang_rdb/lng_id


pig Assignment2Part1.pig
hive -f Assignment2Part2.hql