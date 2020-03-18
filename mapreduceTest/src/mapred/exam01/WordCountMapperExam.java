package mapred.exam01;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.fs.shell.Count;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapperExam extends Mapper<LongWritable, Text, Text, IntWritable> {
	//output 데이터를 담을 변수 선언
	static final IntWritable outputVal = new IntWritable(1);
	Text outputKey = new Text();
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		StringTokenizer st = new StringTokenizer(value.toString());
		System.out.println(key+":"+value);
		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			if(token.length()>=5) {
			outputKey.set(token);
			context.write(outputKey, outputVal);
			}
		}
	}
	
	
}
