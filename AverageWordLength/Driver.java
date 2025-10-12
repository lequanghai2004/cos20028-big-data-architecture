package AverageWordLength;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.Configured;


public class Driver extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {
        
        if (args.length != 2) {
            System.out.printf("Usage: Driver <input dir> <output dir>\n");
            return -1;
        }

        Configuration conf = this.getConf();
        Job job = Job.getInstance(conf, "Average Word Length");
        job.setJarByClass(Driver.class);
        job.setJobName("Average Word Length");

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(WordMapper.class);
        job.setReducerClass(AverageReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        boolean success = job.waitForCompletion(true);
        return success ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
     
        int exitCode = ToolRunner.run(new Driver(), args);
        System.exit(exitCode);
    }
}
