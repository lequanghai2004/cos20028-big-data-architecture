# Remove previous output directory if it exists
hadoop fs -rm -r linecount

# Compile Java files
javac -classpath `hadoop classpath` LineCount/*.java

# Create JAR file
jar cvf LineCount/wc.jar LineCount/*.class

# Run the Hadoop job
hadoop jar LineCount/wc.jar LineCount.Driver bible.tar.gz linecount

# Display the output
hadoop fs -cat linecount/part-r-00000