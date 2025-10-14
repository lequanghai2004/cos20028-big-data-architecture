package MostPopularAddress;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

public class AccessReducer extends Reducer<Text, LongWritable, Text, Iterable<LongWritable>> {

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws java.io.IOException, InterruptedException {

        context.write(key, values);
    }
}
