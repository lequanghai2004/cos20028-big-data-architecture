package TF_IDF.DocumentFrequency;

import java.io.IOException;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;


public class DocumentFrequencyReducer extends Reducer<Text, ArrayWritable, ArrayWritable, ArrayWritable> {
    @Override
    protected void reduce(Text key, Iterable<ArrayWritable> values, Context context) 
        throws IOException, InterruptedException {
        
        Text term = key;
        
        for (ArrayWritable value : values) {
           
            Writable[] pair = value.get();
            if (pair.length != 3
                || !(pair[0] instanceof Text)
                || !(pair[1] instanceof LongWritable)
                || !(pair[2] instanceof LongWritable)) {
                // Handle malformed values if necessary
                continue;
            }
            
            Text document = (Text) pair[0];
            LongWritable termFrequency = (LongWritable) pair[1];
            LongWritable documentFrequency = (LongWritable) pair[2];
        }
    }
}
