package TF_IDF.Calculate_TF_IDF;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.Text;
import java.io.IOException;


// Join (term, doc), tf with term, df to ((term, doc), (tf, df))
// Type (term, doc) is ArrayWritable
// Type tf is LongWritable
// Type term is Text
// Type df is LongWritable
// Type (term, doc) is ArrayWritable
// Type (tf, df) is ArrayWritable

public class CalculateMapper 
    extends Mapper<ArrayWritable, ArrayWritable, ArrayWritable, ArrayWritable> {

    @Override // Join (term, doc), tf with term, df to ((term, doc), (tf, df))
    protected void map(ArrayWritable key, ArrayWritable value, Context context) 
        throws IOException, InterruptedException {
        
        Writable[] pair = key.get();
        Writable[] counts = value.get();

        if (pair.length != 2
            || !(pair[0] instanceof Text)
            || !(pair[1] instanceof Text)
            || counts.length != 2
            || !(counts[0] instanceof LongWritable)
            || !(counts[1] instanceof LongWritable)) {
            // Handle malformed keys if necessary
            return;
        }
        
        LongWritable tf = (LongWritable) counts[0];
        LongWritable df = (LongWritable) counts[1];
        Writable[] temp = new Writable[] {tf, df};
        ArrayWritable tf_df = new ArrayWritable(Writable.class, temp);
        context.write(key, tf_df);
    }    
}

