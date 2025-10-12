package ImageCount;

import java.io.IOException;
import java.util.regex.*;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ImageTypeMapper extends Mapper<LongWritable, Text, Text, Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        String line = value.toString().toLowerCase();

        // Regex to match any filename ending with an image extension
        Pattern p = Pattern.compile("\\.(jpg|jpeg|png|gif|bmp|ico|tiff|webp)");
        Matcher m = p.matcher(line);

        if (m.find()) {
            String imageType = m.group(1);
            // Use Hadoop’s built-in counter dynamically
            context.getCounter("ImageTypes", imageType).increment(1);
        }
    }
}
