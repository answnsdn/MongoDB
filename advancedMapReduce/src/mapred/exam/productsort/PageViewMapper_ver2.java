package mapred.exam.productsort;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PageViewMapper_ver2
	extends Mapper<LongWritable, Text,MyKey,Text>{
	MyKey outputKey = new MyKey();//output key
	//output Value
	Text outputVal = new Text();
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, MyKey, Text>.Context context)
			throws IOException, InterruptedException {
		//if(key.get()>0)//데이터 제일 윗줄을 표현 => 첫번째 줄에 데이터의 종류를 나타내고 있다면 필요 없다. {
			String[] line = value.toString().split("\\t");
			if(line!=null & line.length>0) {
					/*outputKey.set(line[0]+"년 "
										+line[1]+"월"); 기존방식*/
					//커스터마이징을 위한 개별 방식
					outputKey.setProductId(line[2]);
					outputKey.setUserId(line[9]);
					outputVal.set(line[9]);
					context.write(outputKey, outputVal);
				//}
			
		}
	}
}







