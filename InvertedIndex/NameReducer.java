package InvertedIndex;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class NameReducer extends Reducer<Text, Text, Text, Text> {

    public void reduce(Text key, Iterable<Text> values, Context context)
        throws IOException, InterruptedException {

        StringBuilder fileList = new StringBuilder();
        for (Text value : values) {
            if (fileList.length() > 0) {
                fileList.append(", ");
            }
            fileList.append(value.toString());
        }
        context.write(key, new Text(fileList.toString()));
    }    
}