# Compile Java files
javac -classpath `hadoop classpath` Question3/*.java

# Create JAR file
jar cvf wc.jar Question3/*.class

# Run the Hadoop job
hadoop jar wc.jar Question3.Driver assignment2/austlang_dataset_nh.txt assignment2/austlang_rdb/lng_id
