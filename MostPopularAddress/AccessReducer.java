package MostPopularAddress;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

public class AccessReducer extends Reducer<LongWritable, Text, LongWritable, Text> {

    @Override
    protected void reduce(LongWritable key, Iterable<Text> values, Context context) throws java.io.IOException, InterruptedException {


        String listOfIps = "";
        for (Text val : values) {
            if (!listOfIps.isEmpty()) {
                listOfIps += ", ";
            }
            listOfIps += val.toString();
        }
        context.write(key, new Text(listOfIps));
    }
}
