package mapred.product.sort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class MyKeyPartitioner extends Partitioner<Mykey, IntWritable>{

	@Override
	public int getPartition(Mykey key, IntWritable value, int numPartitions) {
		
		return key.getProductId().hashCode() % numPartitions;
	}

}
