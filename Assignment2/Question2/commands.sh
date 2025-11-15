# Compile Java files
javac -classpath `hadoop classpath` Question2/*.java

# Create JAR file
jar cvf wc.jar Question2/*.class

# Run the Hadoop job
hadoop jar wc.jar Question2.Driver 2 assignment2/austlang_dataset_nh.txt assignment2/austlang_rdb/lng_name

# Display the output
hadoop fs -cat assignment2/austlang_rdb/lng_name/part-r-00000