package mapred.exam.stock.option;

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

public class StockOptionDriver extends Configured implements Tool{

	@Override
	public int run(String[] optionlist) throws Exception {
		GenericOptionsParser parser = new GenericOptionsParser(getConf(), optionlist);
		String[] OtherArgs = parser.getRemainingArgs();
		Job job = new Job(getConf(), "nasdaqMultiple");
		//맵퍼, 리듀스, 드라이버 클래스 등록
		job.setMapperClass(StockOptionMapper.class);
		job.setReducerClass(StockOptionReducer.class);
		job.setJarByClass(StockOptionDriver.class);
		//Input데이터와 Output데이터의 포맷을 정의
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		//출력할 Key와 Value 타입을 정의
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		//읽고 쓰는 Path 객체 정의
		FileInputFormat.addInputPath(job, new Path(OtherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(OtherArgs[1]));
		
		//job 실행
		job.waitForCompletion(true);
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new StockOptionDriver(), args);	
	}

}
