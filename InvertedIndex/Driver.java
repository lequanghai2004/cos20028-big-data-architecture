package InvertedIndex;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;

import org.apache.commons.io.FileUtils;


public class Driver extends Configured implements Tool {
    
    public int run(String[] args) throws Exception {

        if (args.length != 2) {
            System.err.printf(
                "Usage: %s [generic options] <input> <output>\n",
                getClass().getSimpleName());
            ToolRunner.printGenericCommandUsage(System.err);
            return -1;
        }

        String inpuPath = args[0];
        String outputPath = args[1];
        FileUtils.deleteDirectory(new File(outputPath));
        // System.setProperty("hadoop.home.dir", "");

        Job job = Job.getInstance(getConf(), "Inverted Index on Job Creation");
        job.setJobName("Inverted Index after Job Creation");
        job.setJarByClass(Driver.class);
        job.setMapperClass(NameMapper.class);
        job.setReducerClass(NameReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, new Path(inpuPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {

        int exitCode = ToolRunner.run(new Configuration(), new Driver(), args);
        System.exit(exitCode);
    }
}
