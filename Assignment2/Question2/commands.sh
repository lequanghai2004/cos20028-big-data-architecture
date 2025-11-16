javac -classpath `hadoop classpath` Question2/*.java;
jar cvf wc2.jar Question2/*.class;

# Run the Hadoop job
hadoop jar wc2.jar Question2.Driver 1 assignment2/austlang_dataset_nh.txt assignment2/austlang_rdb/lng_name;
hadoop jar wc2.jar Question2.Driver 2 assignment2/austlang_dataset_nh.txt assignment2/austlang_rdb/lng_synonym;
hadoop jar wc2.jar Question2.Driver 3 assignment2/austlang_dataset_nh.txt assignment2/austlang_rdb/lng_thl;
hadoop jar wc2.jar Question2.Driver 4 assignment2/austlang_dataset_nh.txt assignment2/austlang_rdb/lng_thp;
hadoop jar wc2.jar Question2.Driver 7 assignment2/austlang_dataset_nh.txt assignment2/austlang_rdb/lng_st;