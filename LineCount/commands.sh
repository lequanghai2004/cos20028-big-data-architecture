# Remove previous output directory if it exists
hadoop fs -rm -r linecounts

# Compile Java files
javac -classpath `hadoop classpath` LineCount/*.java

# Create JAR file
jar cvf wc.jar LineCount/*.class

# Run the Hadoop job
hadoop jar wc.jar LineCount.Driver bible.tar.gz linecounts

# Display the output
hadoop fs -cat linecounts/part-r-00000