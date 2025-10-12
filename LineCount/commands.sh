# Compile Java files
javac -classpath `hadoop classpath` *.java

# Create JAR file
jar cvf wc.jar *.class

# Run the Hadoop job
hadoop jar wc.jar Driver bible linecounts

# Display the output
hadoop fs -cat linecounts/part-r-00000