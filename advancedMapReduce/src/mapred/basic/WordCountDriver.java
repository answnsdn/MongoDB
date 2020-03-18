package mapred.basic;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

//맵리듀스를 실행하기 위한 일련의 작업을 처리(=환경을 구축)하는 클래스
public class WordCountDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// 1. Hadoop 내부 jar파일과 통신하기 위한 Configuration객체를 정의하고
		//	    맵리듀스를 처리하는 Job객체와 conf를 연결한다.
		Configuration conf = new Configuration();//Hadoop 내부 jar파일을 불러오기 위한 객체
		Job job = new Job(conf, "basic");
		// 2. 실제 job을 처리하기 위한 클래스가 어떤 클래스인지 등록
		//      => 구현한 Mapper, Reducer, Driver를 등록
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);
		job.setJarByClass(WordCountDriver.class);//jar 내에 있는 여러 개의 .class 파일 중  
		// 3. HDFS에서 읽고 쓸 Input데이터와 Output데이터의 포맷(텍스트/이미지)을 정의
		// 		=> HDFS에 텍스트파일의 형태로 input/output을 처리
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		// 4. 리듀스의 출력데이터에 대한 키와 value의 타입을 정의
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		// 5. HDFS에 저장된 파일을 읽고 쓸 수 있는 Path객체를 정의(경로)
		FileInputFormat.addInputPath(job, new Path(args[0]));//Input에 대한 경로 추가
		FileOutputFormat.setOutputPath(job, new Path(args[1]));//Output에 대한 경로 세팅
		// 6. 1번-5번까지 설정한 내용을 기반으로 job이 실행되도록 명령
		job.waitForCompletion(true);
	}

}
