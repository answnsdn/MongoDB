package mapred.exam.air.multiple;

import java.io.IOException; 


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AirMultipleMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	//output 데이터를 담을 변수 선언
	static final IntWritable outputVal = new IntWritable(1);
	Text outputKey = new Text();
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if(key.get()>0) {
			String[] line = value.toString().split(",");
			if(line!=null) {
				if(!line[15].equals("NA") && Integer.parseInt(line[15])>0){
					outputKey.set("departure,"+line[1]);
					context.write(outputKey, outputVal);
				}else if(!line[15].equals("NA")) {
					outputKey.set("departnull,"+line[1]);
					context.write(outputKey, outputVal);
				}
				if(!line[14].equals("NA") && Integer.parseInt(line[14])>0){
					outputKey.set("arrival,"+line[1]);
					context.write(outputKey, outputVal);
				}else {
					outputKey.set("arrinull,"+line[1]);
					context.write(outputKey, outputVal);
				}
			}
		}
	}
}
