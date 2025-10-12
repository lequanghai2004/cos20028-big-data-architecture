package LineCount;

import org.apache.hadoop.util.Tool;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.fs.Path;


// The source data is built in the virtual machine image under the path of 
// /home/training/training_materials/developer/data. Our goal is using MapReduce to find out 
// how many lines are included in the “bible.tar.gz” file.
public class Driver extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {

        // Check input parameters
        if(args.length != 2) {
            System.out.printf("Usage: Driver <input dir> <output dir>\n");
            return -1;
        }

        // Create a Hadoop job and set its properties
        Configuration conf = this.getConf();
        Job job = Job.getInstance(conf, "Line Count");
        job.setJarByClass(Driver.class);

        // Specify Mapper and Reducer
        job.setMapperClass(LineMapper.class);
        job.setReducerClass(SumReducer.class);
        
        // Set output key/value types for Mapper
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        
        // Set output key/value types for Reducer
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        
        // Set input and output paths
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
        boolean success = job.waitForCompletion(true);
        return success ? 0 : 1;
    }
    
    public static void main(String[] args) throws Exception {
     
        int exitCode = ToolRunner.run(new Driver(), args);
        System.exit(exitCode);
    }
}
