package MostPopularAddress;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;


public class MonthPartitioner extends Partitioner<Text, Text> {
    @Override
    public int getPartition(Text key, Text value, int numPartitions) {
        // Key format: "10.0.0.1@@Jan
        String[] parts = key.toString().split("@@");
        if (parts.length > 1) {
            String month = parts[1];
            switch (month) {
                case "Jan":
                    return 0 % numPartitions;
                case "Feb":
                    return 1 % numPartitions;
                case "Mar":
                    return 2 % numPartitions;
                case "Apr":
                    return 3 % numPartitions;
                case "May":
                    return 4 % numPartitions;
                case "Jun":
                    return 5 % numPartitions;
                case "Jul":
                    return 6 % numPartitions;
                case "Aug":
                    return 7 % numPartitions;
                case "Sep":
                    return 8 % numPartitions;
                case "Oct":
                    return 9 % numPartitions;
                case "Nov":
                    return 10 % numPartitions;
                case "Dec":
                    return 11 % numPartitions;
                default:
                    return 0 % numPartitions; // Default partition for unknown months
            }
        } else {
            return 0 % numPartitions; // Default partition if format is unexpected
        }
    }
}
