package stubs;

import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	// Pattern to match words starting with A, C, G, W, Y
	// followed by lowercase letters regardless of length
	private Pattern pattern = Pattern.compile("\\b[ACGWY][a-z]+\\b");

	// Reusable object to hold output
	private final static IntWritable one = new IntWritable(1);
	private final static Text word = new Text();
  
	// The map method runs once for each line of text in the input file.
	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		// Convert the line from Text to String
		String line = value.toString();

	    // Use the pattern to find words that match the expression
        Matcher matcher = pattern.matcher(line);
        
        // Write matched word to context with a count of one
        while (matcher.find()) {
        	String w = matcher.group();
            word.set(w);
            context.write(word, one);
        }
	}
}



