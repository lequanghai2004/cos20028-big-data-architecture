package Question4;

import java.io.IOException;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class EntityMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    
    
    private int column; // Index of the target column
    private final static Text text = new Text(); // Reusable Text object for output key

    @Override
    protected void setup(Context context) {
        Configuration configuration = context.getConfiguration();
        column = configuration.getInt("column", 0);
    }

    @Override
    public void map(LongWritable key, Text value, Context context)
        throws IOException, InterruptedException {

        String[] elements = value.toString().split("\\t");
        String lng_code = elements[0];
        String[] attributes = elements[column].split(",| / ");
        for (String attribute : attributes) {
            String attr = attribute.trim();
            if (attr.isEmpty()) continue;
            text.set(lng_code + "\t" + attr);
            context.write(text, NullWritable.get());
        }
    }
}
