package MostPopularAddress;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class SumReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context)
        throws IOException, InterruptedException {
        
        // Initialize a sum variable to accumulate the counts
        long sum = 0;

        // Sum all the counts for the given IP address (key)
        for (LongWritable val : values) {
            sum += val.get();
        }

        // Create a LongWritable to hold the sum
        LongWritable count = new LongWritable(sum);

        // Emit the IP address and its total count
        context.write(key, count);
    }
    
}
