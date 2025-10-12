package LineCount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.reduce.IntSumReducer;

import java.io.IOException;


public class SumReducer extends IntSumReducer<Text> {

    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context)
        throws IOException, InterruptedException {

        super.reduce(key, values, context);
    }
}