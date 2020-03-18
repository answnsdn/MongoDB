package mapred.exam.air.option;

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
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
/*사용자가 -D 옵션을 이용해서 입력한 옵션값을 프로그램 안에서 사용하기 위한 과정
	=>Mapper가 사용할 수 있도록 전달한다는 의미*/
/*	1. Configured와 Tool을 상속해야 한다.
		=> Configured는 환경설정 정보를 활용
		=> Tool은 사용자정의 옵션을 사용하기 위함
	2. Run메소드 오버라이딩
		=> run메소드 안에 Driver에서 구현했던 모든 코드를 구현
	3. Run메소드를 Main메소드에서 실행되도록 호출해야 한다.
		=> 내부 구조 상 Run메소드는 직접 호출이 불가하다
		=> ToolRunner 헬퍼 클래스를 이용해서 호출*/
public class AirOptionDriver extends Configured implements Tool{
	
	@Override
	public int run(String[] optionlist) throws Exception {
		//Run메소드는 사용자가 입력한 모든 옵션에 대한 정보를 String[]으로 전달받는다.
		//-D를 입력하고 설정한 옵션과 사용자가 입력한 명령행매개변수를 분리하여 관리해야 한다.
		//getRemainingArgs()을 이용하여 공통옵션(-D/-conf/-fs 등등)
		//과 사용자가 입력한 옵션을 따로 분리한다. -> 
		GenericOptionsParser parser = new GenericOptionsParser(getConf(), optionlist);
		String[] otherArgs = parser.getRemainingArgs();
		Job job = new Job(getConf(), "airoption");
		//맵퍼, 리듀스, 드라이버 클래스 등록
		job.setMapperClass(AirOptionMapper.class);
		job.setReducerClass(AirOptionReducer.class);
		job.setJarByClass(AirOptionDriver.class);
		//Input데이터와 Output데이터의 포맷을 정의
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		//출력할 Key와 Value 타입을 정의
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		//읽고 쓰는 Path 객체 정의
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		//job 실행
		job.waitForCompletion(true);
		return 0;
	}

	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new AirOptionDriver(), args);
	}

}
