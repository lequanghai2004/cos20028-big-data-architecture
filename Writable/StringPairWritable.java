package Writable;

import org.apache.hadoop.io.WritableComparable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class StringPairWritable implements WritableComparable<StringPairWritable> {
    
    private String left;
    private String right;

    public StringPairWritable() {

        left = new String();
        right = new String();
    }

    public StringPairWritable(String l, String r) {
        
        left = l;
        right = r;
    }

    public void write(DataOutput out) throws IOException {
        
        out.writeUTF(left);
        out.writeUTF(right);
    }

    public void set(String l, String r) {
        
        left = l;
        right = r;
    }

    public void readFields(DataInput in) throws IOException {
        
        left = in.readUTF();
        right = in.readUTF();
    }

    public int compareTo(StringPairWritable other) {

        return left.compareTo(other.left) != 0 
            ? left.compareTo(other.left) 
            : right.compareTo(other.right);
    }

    @Override
    public boolean equals(Object obj) {
        
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        StringPairWritable other = (StringPairWritable) obj;
        return left.equals(other.left) && right.equals(other.right);
    }

    @Override
    public int hashCode() {
        
        final int prime = 31;
        int result = 1;
        result = prime * result + ((left == null) ? 0 : left.hashCode());
        result = prime * result + ((right == null) ? 0 : right.hashCode());
        return result;
    }

    public String toString() {
        
        return "(" + left + "," + right + ")";
    }
}
