package mapred.exam.stock.multiple;

import java.io.IOException; 

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import mapred.exam.stock.option.StockOptionDriver;

public class StockMultipleDriver{


	public static void main(String[] args) throws Exception {
		Configuration conf =new Configuration(); 
		Job job = new Job(conf, "nasdaqMulitple");
		//맵퍼, 리듀스, 드라이버 클래스 등록
		job.setMapperClass(StockMultipleMapper.class);
		job.setReducerClass(StockMultipleReducer.class);
		job.setJarByClass(StockMultipleDriver.class);
		//Input데이터와 Output데이터의 포맷을 정의
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		//출력할 Key와 Value 타입을 정의
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		//읽고 쓰는 Path 객체 정의
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		MultipleOutputs.addNamedOutput(job, "up", TextOutputFormat.class, Text.class, IntWritable.class);
		MultipleOutputs.addNamedOutput(job, "down", TextOutputFormat.class, Text.class, IntWritable.class);
		MultipleOutputs.addNamedOutput(job, "equal", TextOutputFormat.class, Text.class, IntWritable.class);
		//job 실행
		job.waitForCompletion(true);
	}

}
