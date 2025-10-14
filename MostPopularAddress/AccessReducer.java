package MostPopularAddress;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

public class AccessReducer extends Reducer<LongWritable, Text, LongWritable, Text> {

    @Override
    protected void reduce(LongWritable key, Iterable<Text> values, Context context) throws java.io.IOException, InterruptedException {


        StringBuilder ips = new StringBuilder();
        for (Text val : values) {
            if (ips.length() > 0) {
                ips.append(", ");
            }
            ips.append(val.toString());
        }
        context.write(key, new Text(ips.toString()));
    }
}
