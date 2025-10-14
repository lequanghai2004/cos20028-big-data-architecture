// The goal is to build a MapReduce-type program to analyse the log file.

// We want to determine which IP address most frequently accesses our server in a specific month from the access log.

// To answer this question, please design a MapReduce program with proper mechanisms for gathering the desired information from the data and answer the following questions.

// The source log file name is "2022_access_log.gz", which is located on the path "/home/training/training_materials/developer/data"

// Hint 1: You can copy and paste an Eclips project directly in the Package Explore, rename it into a new project, and then modify/create the corresponding contents in the project.

// Hint 2: You may consider using a partitioner to help you achieve the goal. Importing the HashMap with the statements below may help.
// import java.util.HashMap;

// Hint 3: You may want to import the Arrays and List data types from Java to contain your data. The following statements can help you achieve that.
// import java.util.Arrays;
// import java.util.List;

// Here is an example of creating a list from an array:

// List<String> mylist = Arrays.asList("Apple", "Banana", "Onion");

// Moreover, you may want to use the "sort" command with the "-k 2" and "-r" options in Linux to rearrange your output file for you. To put the sorted result in a new file, you can use the statement below:

// sort -k 2 -r inputfile > outputfile

// where -k 2 specifies the sort is based on the second column in the file, -r specifies the sort in descending order, and the ">" symbol stores the output on its left to the right as a file.

// However, please note that the sort is applied sequentially from the first to the last digit. A larger number may be sorted to the later part in the list when a smaller number has a larger value in the first digit. For example, 68 will be sorted at the front than 256 because the first digit 6 is greater than 2.

package MostPopularAddress;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class AddressMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

    private final static LongWritable one = new LongWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        // Each line in the log file is represented by 'value'
        String line = value.toString();

        // Split the line into parts based on spaces using regex to handle multiple spaces
        String[] parts = line.split("\\s+");
        
        // Check if the line has enough parts to avoid ArrayIndexOutOfBoundsException
        if (parts.length > 0) {
            // The IP address is typically the first part of the log entry
            Text ipAddress = new Text(parts[0]);
            // Emit the IP address with a count of one
            context.write(ipAddress, one);
        }
    }
}
