package AverageWordLength;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;


public class WordMapper extends Mapper<Object, Text, Text, IntWritable> {

    private final static IntWritable wordLength = new IntWritable(1);
    private final static Text wordKey = new Text("Average Word Length");

    @Override
    protected void map(Object key, Text value, Context context)
        throws IOException, InterruptedException {
        
        String line = value.toString();
        String[] words = line.split("\\s+");
        for (String word : words) {
            if (!word.isEmpty()) {
                wordLength.set(word.length());
                wordKey.set(String.valueOf(word.charAt(0)));
                context.write(wordKey, wordLength);
            }
        }
    }
}
