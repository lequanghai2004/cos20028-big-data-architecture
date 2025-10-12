package AverageWordLength;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;


public class AverageReducer extends Reducer<Text, IntWritable, Text, FloatWritable> {

    private final static FloatWritable result = new FloatWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) 
        throws IOException, InterruptedException {

        int totalLength = 0;
        int wordCount = 0;

        for (IntWritable val : values) {
            totalLength += val.get();
            wordCount++;
        }

        float averageLength = wordCount == 0 ? 0 : totalLength / wordCount;
        result.set(averageLength);
        context.write(key, result);
    }
}
