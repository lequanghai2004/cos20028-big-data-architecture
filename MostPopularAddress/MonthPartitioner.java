package MostPopularAddress;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;


public class MonthPartitioner extends Partitioner<Text, Text> {
    @Override
    public int getPartition(Text key, Text value, int numPartitions) {
        // Key format: "ip@@month"
        String[] parts = key.toString().split("@@");
        if (parts.length != 2) {
            // fallback: hash whole key
            return (key.hashCode() & Integer.MAX_VALUE) % numPartitions;
        }
        String month = parts[1];
        return (month.hashCode() & Integer.MAX_VALUE) % numPartitions;
    }
}
