package SequenceFile;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class ReadCompressedSequenceFile extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {

        return 0;
    }

    public static void main(String[] args) throws Exception {
     
        int exitCode = ToolRunner.run(new Configuration(), new ReadCompressedSequenceFile(), args);
        System.exit(exitCode);
    }
}
