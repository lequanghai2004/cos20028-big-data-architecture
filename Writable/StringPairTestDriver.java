package Writable;

import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.reduce.LongSumReducer;


public class StringPairTestDriver extends Configured implements Tool {
    
    @Override
    public int run(String[] args) throws Exception {

        if (args.length != 2) {
            System.err.println("Usage: StringPairTestDriver <input path> <output path>");
            System.exit(-1);
        }

        Job job = Job.getInstance(getConf(), "String Pair Test");
        job.setJarByClass(StringPairTestDriver.class);

        job.setMapperClass(StringPairMapper.class);
        job.setReducerClass(LongSumReducer.class);

        job.setMapOutputKeyClass(StringPairWritable.class);
        job.setMapOutputValueClass(LongWritable.class);

        job.setOutputKeyClass(StringPairWritable.class);
        job.setOutputValueClass(LongSumReducer.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {

        int exitCode = ToolRunner.run(new StringPairTestDriver(), args);
        System.exit(exitCode);
    }
}
