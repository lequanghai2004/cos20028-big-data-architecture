package MostPopularAddress;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class AccessMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

    @Override // from offset (ip \t count) to count, ip
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        // Each line in the log file is represented by 'value'
        String line = value.toString();

        // Split the line into parts based on spaces using regex to handle multiple spaces
        String[] parts = line.split("\\t+");

        // Check if the line has enough parts to avoid ArrayIndexOutOfBoundsException
        if (parts.length > 1) {
            // The IP address is typically the first part of the log entry
            Text ipAddress = new Text(parts[0]);
            LongWritable count = new LongWritable(Long.parseLong(parts[1]));
            // Emit the IP address with a count of one
            context.write(ipAddress, count);
        }
    }
}
