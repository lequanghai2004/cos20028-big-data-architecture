# Clean up previous HDFS directories if they exist
hadoop fs -rm -r assignment1

# Create necessary HDFS directories
hadoop fs -mkdir assignment1

# Upload input file to HDFS
hadoop fs -put austlang_dataset.csv assignment1/austlang_dataset

# Compile Java files
javac -classpath `hadoop classpath` stubs/*.java

# Create JAR file
jar cvf wc.jar stubs/*.class

# Run the Hadoop job
hadoop jar wc.jar stubs.Assignment1Driver assignment1/austlang_dataset assignment1/result

# Display the output
hadoop fs -cat assignment1/result/part-r-00000

# Clean up class files and JAR file 
rm stubs/*.class wc.jar