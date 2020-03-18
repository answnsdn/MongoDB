package mapred.exam.air.multiple;

import java.io.IOException; 

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class AirMultipleDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		Job job = new Job(conf, "airmulti");
		//맵퍼, 리듀스, 드라이버 클래스 등록
		job.setMapperClass(AirMultipleMapper.class);
		job.setReducerClass(AirMultipleReducer.class);
		job.setJarByClass(AirMultipleDriver.class);
		//Input데이터와 Output데이터의 포맷을 정의
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		//출력할 Key와 Value 타입을 정의
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		//읽고 쓰는 Path 객체 정의
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		MultipleOutputs.addNamedOutput(job, "departure", TextOutputFormat.class, Text.class, IntWritable.class);
		MultipleOutputs.addNamedOutput(job, "departnull", TextOutputFormat.class, Text.class, IntWritable.class);
		MultipleOutputs.addNamedOutput(job, "arrival", TextOutputFormat.class, Text.class, IntWritable.class);
		MultipleOutputs.addNamedOutput(job, "arrinull", TextOutputFormat.class, Text.class, IntWritable.class);
		//job 실행
		job.waitForCompletion(true);
	}

}
