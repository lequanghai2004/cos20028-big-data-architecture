package InvertedIndex;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


public class NameMapper extends Mapper<LongWritable, Text, Text, Text> {

    private final static Text firstName = new Text();
    private final static Text fileName = new Text();

    public void map(LongWritable key, Text value, Context context)
        throws IOException, InterruptedException {

        String line = value.toString();
        String[] fields = line.split(",", -1);
        
        if(fields != null && fields.length == 9 && fields[0].length() > 0) {
           firstName.set(fields[0]);
           fileName.set(((FileSplit)context.getInputSplit()).getPath().getName());
           context.write(firstName, fileName);     
        }
    }
}