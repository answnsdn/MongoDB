package mapred.air.sort;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AirSortMapper 
	extends Mapper<LongWritable, Text,CustomKey,IntWritable>{
	CustomKey outputKey = new CustomKey();//output key
	//output Value
	static final IntWritable one = new IntWritable(1);
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, CustomKey, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if(key.get()>0) {
			String[] line = value.toString().split(",");
			if(line!=null & line.length>0) {
				if(!line[15].equals("NA") && 
						Integer.parseInt(line[15])>0) {
					/*outputKey.set(line[0]+"년 "
										+line[1]+"월"); 기존방식*/
					//커스터마이징을 위한 개별 방식
					outputKey.setYear(line[0]);
					outputKey.setMonth(new Integer(line[1]));
<<<<<<< HEAD
					outputKey.setMapkey(key.get());
=======
>>>>>>> f9663043fa54918552602d961c1003106d2b8a82
					context.write(outputKey, one);
				}
			}
		}
	}
}







