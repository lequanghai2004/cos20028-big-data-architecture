import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * WordCount driver using ToolRunner.
 * This version supports command-line -D properties and cleaner config handling.
 */
public class WordCountShifted extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {

        // Ensure correct argument count
        if (args.length != 2) {
            System.out.println("Usage: WordCount <input dir> <output dir>");
            return -1;
        }

        // Create job instance with configuration
        Configuration conf = getConf();
        Job job = Job.getInstance(conf, "Word Count Shifted with ToolRunner");
        job.setJarByClass(WordCountShifted.class);

        // Set Mapper and Reducer classes
        job.setMapperClass(WordMapper.class);
        job.setReducerClass(SumReducer.class);

        // Set output key/value types
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // Set input and output paths
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // Print your name & student ID as required by assignment
        System.out.println("Name: Your Name | Student ID: 1234567");

        // Execute job and return exit code
        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new WordCount(), args);
        System.exit(exitCode);
    }
}