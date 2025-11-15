package Question3;

import java.io.IOException;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class EntityMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    
    private final static Text text = new Text(); // Reusable Text object for output key

    @Override
    public void map(LongWritable key, Text value, Context context)
        throws IOException, InterruptedException {

        String[] elements = value.toString().split("\\t");
        
        String lng_code = elements[0];
        String a_lng_lat = elements[5];
        String a_lng_lng = elements[6];
        String lng_uri = elements[8];

        text.set(lng_code + "\t" + a_lng_lat + "\t" + a_lng_lng + "\t" + lng_uri);
        context.write(text, NullWritable.get());
    }
}
