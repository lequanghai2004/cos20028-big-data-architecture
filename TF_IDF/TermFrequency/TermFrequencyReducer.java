package TF_IDF.TermFrequency;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.LongWritable;
import java.io.IOException;


public class TermFrequencyReducer
    extends Reducer<ArrayWritable, LongWritable, ArrayWritable, LongWritable> {

    @Override // Aggregate term frequency from (term, doc), [tf, tf, ...] to (term, doc), tf
    protected void reduce(ArrayWritable key, Iterable<LongWritable> values, Context context) 
        throws IOException, InterruptedException {
        
        long count = 0;
        for (LongWritable value : values) {
            count += value.get();
        }
        LongWritable tf = new LongWritable(count);
        context.write(key, tf);
    }    
}
