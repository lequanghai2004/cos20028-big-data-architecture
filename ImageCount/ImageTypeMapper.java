package ImageCount;

import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ImageTypeMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private static final Pattern p = Pattern.compile("\\.(jpg|jpeg|png|gif|webp|svg|ico|tiff|raw|cr2|nef|arw|heic|psd|indd|ai|eps)\\b");
    private static final Text text = new Text();
    private static final IntWritable one = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context)
        throws IOException, InterruptedException {

        String line = value.toString().toLowerCase();

        // Regex to match any filename ending with image extension
        Matcher m = p.matcher(line);

        if (m.find()) {
            String imageType = m.group(1);
            context.getCounter("ImageTypes", imageType).increment(1);
            text.set(imageType);
            context.write(text, one);
        }
    }
}
