package SequenceFile;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CreateSequenceFile extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {

        if(args.length != 1) {
            System.err.println("Usage: CreateSequenceFile <intput path> <output path>");
            System.exit(-1);
        }

        Job job = Job.getInstance(getConf(), "Create Sequence File");
        job.setJarByClass(CreateSequenceFile.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setOutputFormatClass(SequenceFileOutputFormat.class);
        job.setNumReduceTasks(0);
        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
     
        int exitCode = ToolRunner.run(new Configuration(), new CreateSequenceFile(), args);
        System.exit(exitCode);
    }
}
