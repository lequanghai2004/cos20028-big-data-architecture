package TF_IDF.TermFrequency;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.LongWritable;
import java.io.IOException;


public class TermFrequencyReducer
    extends Reducer<ArrayWritable, LongWritable, ArrayWritable, LongWritable> {

    @Override // Sum up the counts for each (term, documentId) pair
    protected void reduce(ArrayWritable key, Iterable<LongWritable> values, Context context) 
        throws IOException, InterruptedException {
        
        long sum = 0;
        for (LongWritable value : values) {
            sum += value.get();
        }
        LongWritable count = new LongWritable(sum);
        context.write(key, count);
    }    
}
