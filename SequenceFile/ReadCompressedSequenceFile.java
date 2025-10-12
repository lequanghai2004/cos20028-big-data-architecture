package SequenceFile;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class ReadCompressedSequenceFile extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {

        if(args.length != 2) {
            System.err.println("Usage: ReadCompressedSequenceFile <input path> <output path>");
            System.exit(-1);
        }

        Job job = Job.getInstance(getConf(), "Read Compressed Sequence File");
        job.setJarByClass(ReadCompressedSequenceFile.class);
        job.setNumReduceTasks(0);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.setInputFormatClass(SequenceFileInputFormat.class);
        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
     
        int exitCode = ToolRunner.run(new Configuration(), new ReadCompressedSequenceFile(), args);
        System.exit(exitCode);
    }
}
