package Question2;

import java.io.IOException;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.conf.Configuration;

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
        
        if(column < 0 || column >= elements.length) {
            return; // Invalid index, skip this record
        }

        String element = elements[column];
        String[] values = element.split(",| / ");

        for (String v : values) {
            String cleaned = v.trim();
            if (cleaned.isEmpty()) continue;
            text.set(cleaned);
            context.write(text, NullWritable.get());
        }
    }
}
