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

            // Month extraction logic
            // Sample log format: "10.223.157.186 - - [15/Jul/2009:15:50:51 -0700] "GET / HTTP/1.1" 200 9157"
            String[] part2s = line.split("\\[\\d{2}/\\w{3}/");
            if(part2s.length > 0) {
                String month = part2s[0].substring(part2s[0].length() - 4, part2s[0].length() - 1);
                context.write(new Text(ipAddress.toString() + "@@" + month), one);
            }
        }
    }
}
