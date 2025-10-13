import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private final static IntWritable one = new IntWritable(1);

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        /*
         * Convert the line, which is received as a Text object, to a String object.
         * Check the caseSensitive parameter from the configuration.
         */
        boolean caseSensitive = context
            .getConfiguration()
            .getBoolean("caseSensitive", false);
        String line = value.toString();
        if (!caseSensitive) {
            line = line.toLowerCase();
        }

        /*
         * The line.split("\\W+") call uses regular expressions to split the
         * line up by non-word characters.
         * 
         * If you are not familiar with the use of regular expressions in
         * Java code, search the web for "Java Regex Tutorial."
         */
        String[] words = line.split("\\W+");
        for (String word : words) {
            if (word.length() > 0) {
                Text text = new Text(word);
                context.write(text, one);
            }
        }
    }
}