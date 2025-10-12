package Writable;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import java.io.IOException;


public class StringPairMapper extends Mapper<LongWritable, Text, StringPairWritable, LongWritable> {

    private final static LongWritable one = new LongWritable(1);
    private final static StringPairWritable pair = new StringPairWritable();

    @Override
    public void map(LongWritable key, Text value, Context context)
        throws IOException, InterruptedException {
        
        String line = value.toString();
        String[] parts = line.split("\\s+");

        if(parts.length >= 2) {
            pair.set(parts[0], parts[1]);
            context.write(pair, one);
        }
    }
}
