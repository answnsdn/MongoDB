package mapred.exam.stock;

import java.io.IOException; 


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StockMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	//output 데이터를 담을 변수 선언
	static final IntWritable outputVal = new IntWritable(1);
	Text outputKey = new Text();
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if(key.get()>0) {
			String[] line = value.toString().split(",");
			if(line!=null & line.length>0 & line[0].equals("NASDAQ")) {
				outputKey.set(line[2].substring(0,4));
				Double result = Double.parseDouble(line[6])-Double.parseDouble(line[3]);
				if(result>0) {
					context.write(outputKey, outputVal);
				}
			}
		}
		
		
	}
	
	
}
