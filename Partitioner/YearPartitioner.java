package Practitioner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.HashMap;

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;

public class YearPartitioner extends Partitioner<Text, IntWritable> implements Configurable {

    private Configuration configuration;
    private static HashMap<String, Integer> yearMap = new HashMap<>();
    private String[] yearList = {"2019", "2020", "2021"};

    @Override
    public Configuration getConf() {

        return configuration;
    }

    @Override
    public void setConf(Configuration conf) {
    
        configuration = conf;
        for(int i = 0; i < yearList.length; i++) {
            yearMap.put(yearList[i], i);
        }
    }

    @Override
    public int getPartition(Text key, IntWritable value, int numPartitions) {
        
        return yearMap.getOrDefault(key.toString(), 0);
    }
}
