package Partitioner;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;


public class LogFileMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context)
        throws IOException, InterruptedException {
        
        // Format: 192.168.0.1 - - [12/Dec/2020:10:15:30 +0000] "GET /index.html HTTP/1.1" 200 1024
        String line = value.toString();

        // Into 2 parts: 
        //     192.168.0.1 - - 12/Dec/
        //     2020:10:15:30 +0000] "GET /index.html HTTP/1.1" 200 1024
        String[] parts = line.split("\\[\\d{2}/\\w{3}/");

        if(parts.length > 0) {
            String year = parts[1].substring(0, 4);
            word.set(year);
            context.write(word, one);
        }
    }
}
