import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    // Variables to track top 2 words
    private String highestWord = "";
    private int highestCount = 0;
    private String secondHighestWord = "";
    private int secondHighestCount = 0;
    private int wordsAppearingOnce = 0;

    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {

        // Variable to hold the sum of counts for a word
        int count = 0;

        // Sum up the counts for each word
        for (IntWritable value : values) {
            count += value.get();
        }

        // Write the word and its total count to context
        context.write(key, new IntWritable(count));

        // Update counter for words appearing exactly once
        if (count == 1) {
            wordsAppearingOnce++;
        }

        // Update highest and second-highest counts and words
        if (count > highestCount) {
            // Move current highest to second highest
            secondHighestCount = highestCount;
            secondHighestWord = highestWord;
            highestCount = count;
            highestWord = key.toString();
        } else if (count > secondHighestCount && count < highestCount) {
            // Replace second highest
            secondHighestCount = count;
            secondHighestWord = key.toString();
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {

        // Write the highest occurring word and its counts
        if (!highestWord.isEmpty()) {
            context.write(new Text("HIGHEST_WORD: " + highestWord), new IntWritable(highestCount));
        }

        // Write the second highest occurring word and its counts
        if (!secondHighestWord.isEmpty()) {
            context.write(new Text("SECOND_HIGHEST_WORD: " + secondHighestWord), new IntWritable(secondHighestCount));
        }

        // Write the count of words that appeared exactly once
        context.write(new Text("WORDS_APPEARING_ONCE"), new IntWritable(wordsAppearingOnce));
    }
}