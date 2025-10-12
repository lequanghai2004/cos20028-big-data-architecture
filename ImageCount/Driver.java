package ImageCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class Driver {

    public int run(String[] args) throws Exception {

        if (args.length != 2) {
            System.out.println("Usage: ImageCount <input dir> <output dir>");
            return -1;
        }

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Image Count");
        job.setJarByClass(Driver.class);

        job.setMapperClass(ImageTypeMapper.class);
        job.setNumReduceTasks(0); // No reducer needed
        
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
                
        boolean success = job.waitForCompletion(true);
        return success ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {

        int exitCode = new Driver().run(args);
        System.exit(exitCode);
    }
}
