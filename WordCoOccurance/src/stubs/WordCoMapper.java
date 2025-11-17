package stubs;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCoMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        
        String line = value.toString();
        String[] words = line.split("\\W+");

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < words.length; j++) {
                if (i != j) {
                    String coWord = words[j];
                    String pair = word + "," + coWord;
                    context.write(new Text(pair), new IntWritable(1));
                }
            }
        }
    }
}
