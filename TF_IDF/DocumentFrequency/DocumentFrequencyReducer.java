package TF_IDF.DocumentFrequency;

import java.io.IOException;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;


public class DocumentFrequencyReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
    
    @Override // Aggregate document frequency from term, [count, count, ...] to term, df
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) 
        throws IOException, InterruptedException {
        
        long count = 0L;
        for(LongWritable value : values) {
            count += value.get();
        }
        LongWritable df = new LongWritable(count);
        context.write(key, df);
    }
}
