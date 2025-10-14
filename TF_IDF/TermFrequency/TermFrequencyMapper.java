package TF_IDF.TermFrequency;

import java.io.IOException;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.ArrayWritable;


public class TermFrequencyMapper 
    extends Mapper<LongWritable, Text, ArrayWritable, LongWritable> {
    
    private final static LongWritable one = new LongWritable(1);

    @Override // For each term in the document, emit ((term, doc), 1)
    protected void map(LongWritable key, Text value, Context context) 
        throws IOException, InterruptedException {
        
        String line = value.toString();
        String[] parts = line.split("\t");
        if (parts.length == 2) {
            FileSplit fileSplit = (FileSplit)context.getInputSplit();
            String document = fileSplit.getPath().getName();
            String[] terms = parts[1].split("\\s+");
            // Do some cleaning if necessary
            // e.g., remove punctuation, case sensitivity, stop words, plural forms, tense, etc.
            for (String term : terms) {
                if (!term.isEmpty()) {
                    Writable[] temp = new Writable[] {new Text(term), new Text(document)};
                    ArrayWritable termDocument = new ArrayWritable(Writable.class, temp);
                    context.write(termDocument, one);
                }
            }
        } else {
            // Handle malformed lines if necessary
        }
    }
}