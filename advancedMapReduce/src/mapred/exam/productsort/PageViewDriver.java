package mapred.exam.productsort;

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

public class PageViewDriver extends Configured implements Tool{
	
	@Override
	public int run(String[] Combinerlist) throws Exception {
		
		GenericOptionsParser parser = new GenericOptionsParser(getConf(), Combinerlist);
		String [] otherArgs = parser.getRemainingArgs();
		
		Job job = new Job(getConf(), "product_ver2"); // mapreduce!!! job 구분 명

		job.setMapperClass(PageViewMapper_ver2.class);
		job.setReducerClass(PageViewReducer_ver2.class);
		job.setJarByClass(PageViewDriver.class);
		
		//Shuffle할때 사용할 클래스를 사용자정의 클래스가 실행되도록 등록
		job.setPartitionerClass(MyKeyPartitioner_ver2.class);
		job.setSortComparatorClass(MyKeyComparator.class);
		job.setGroupingComparatorClass(GroupKeyComparator.class);
		job.setMapOutputKeyClass(MyKey.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

		job.waitForCompletion(true);
		
		return 0;
	}
	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new PageViewDriver(), args);
	}
}
