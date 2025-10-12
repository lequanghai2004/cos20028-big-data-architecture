package LineCount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;


public class LineMapper extends Mapper<Object, Text, Text, IntWritable> {

    private final static IntWritable one = new IntWritable(1);
    private final static Text line = new Text("Total Lines");

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
    
        context.write(line, one);
    }
}