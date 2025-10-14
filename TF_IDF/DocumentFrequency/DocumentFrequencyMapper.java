package TF_IDF.DocumentFrequency;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;


public class DocumentFrequencyMapper 
    extends Mapper<ArrayWritable, LongWritable, Text, LongWritable> {

    private final static LongWritable one = new LongWritable(1);

    @Override // Map from ((term, doc), tf) to (term, 1)
    protected void map(ArrayWritable key, LongWritable value, Context context) 
        throws java.io.IOException, InterruptedException {
        
        Writable[] pair = key.get();

        if (pair.length != 2
            || !(pair[0] instanceof Text)
            || !(pair[1] instanceof Text)) {
            // Handle malformed keys if necessary
            return;
        }
        
        Text term = (Text) pair[0];
        context.write(term, one);
    }
}
