import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;

/* 
 *  Course: COS20028 - Big Data Architectures and Applications
 *  Assignment: MapReduce Application on Counting Words Matching a Pattern
 * 
 *  Student name: Le Quang Hai
 *  Student id: 104175779
 */
public class Assignment1Driver {

	public static void main(String[] args) throws Exception {

		// Starting message
		System.out.println();
		System.out.println("****************************************************************************");
		System.out.println("*****************  Starting Assignment 1 Program Execution  ****************");
		System.out.println("****************************************************************************");
		System.out.println("*****************  Student name: Le Quang Hai               ****************");
		System.out.println("*****************  Student id: 104175779                    ****************");
		System.out.println("****************************************************************************");
		System.out.println();

		// The expected arguments are the paths containing input and output data.
		if(args.length != 2) {
			System.out.println("Usage: Assignment1Driver <input dir> <output dir>\n");
			System.exit(-1);
		}

		// Create a new Job
		Job job = Job.getInstance(new Configuration(), "COS20028 Assignment1");

		// Set the JAR file that contains the driver, mapper, reducer
		job.setJarByClass(Assignment1Driver.class);
		job.setMapperClass(WordMapper.class);
		job.setReducerClass(SumReducer.class);

		// Set the input and output path(s) for the job from the arguments
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		// Set the output key and value type produced by the job
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		// Start the MapReduce job and wait for it to finish.
		System.err.println("Waiting for the job to complete...");
		boolean success = job.waitForCompletion(true);

		// Print appropriate message to the console and exit
		if (success) {
			System.out.println("Job was successful");
			System.out.println("View the output at " + args[1]);
			System.out.println("By executing the command: hdfs dfs -cat " + args[1] + "/part-r-00000");
			System.out.println("Or the command: hadoop fs -cat " + args[1] + "/part-r-00000");
			System.out.println();
			System.out.println("****************************************************************************");
			System.out.println("*****************  Ending Assignment 1 Program Execution    ****************");
			System.out.println("****************************************************************************");
			System.out.println();
			System.exit(0);
		} else {
			System.out.println("Job was not successful. Please troubleshoot and try again.");
			System.exit(1);
		}
	}
}