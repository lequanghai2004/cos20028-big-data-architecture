package Question4;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Driver extends Configured implements Tool {

    public int run(String[] args) throws Exception {
    
        if (args.length != 3) {
            System.err.println("Usage: Driver <column> <input_path> <output_path>");
            return -1;
        }

        int column = Integer.parseInt(args[0]);

        Configuration conf = getConf();
        conf.setInt("column", column); // pass column index to Mapper via config
        Job job = Job.getInstance(conf, "Assignment2 Part2 Question4");

        job.setJarByClass(Driver.class);
        job.setMapperClass(EntityMapper.class);
        job.setNumReduceTasks(0);

        job.setOutputKeyClass(Text.class); // The unique value of the field
        job.setOutputValueClass(NullWritable.class); // No value needed in output

        FileInputFormat.addInputPath(job, new Path(args[1]));
        FileOutputFormat.setOutputPath(job, new Path(args[2]));

        return job.waitForCompletion(true) ? 0 : 1;
    }
    
    public static void main(String[] args) throws Exception {

        int exitCode = ToolRunner.run(new Driver(), args);
        System.exit(exitCode);
    }
}