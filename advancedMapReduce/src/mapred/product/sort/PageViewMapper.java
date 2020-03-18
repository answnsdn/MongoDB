package mapred.product.sort;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PageViewMapper 
		extends Mapper<LongWritable, Text, Mykey,IntWritable>{
	Mykey outputKey = new Mykey();//output key
	//output Value
	static final IntWritable one = new IntWritable(1);
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Mykey, IntWritable>.Context context)
			throws IOException, InterruptedException {
		String[] line = value.toString().split("\t");
		if(line!=null & line.length>0) {
				outputKey.setProductId(line[2]);
				outputKey.setUserId(line[9]);
				context.write(outputKey, one);
		}
	}
}

