package mapred.exam.air.multiple;

import java.io.IOException; 

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class AirMultipleReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	IntWritable resultVal = new IntWritable();
	Text resultKey = new Text();
	MultipleOutputs<Text, IntWritable> multiout;
	
	@Override
	protected void cleanup(Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		multiout.close();
	}

	@Override
	protected void setup(Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		multiout = new MultipleOutputs<Text, IntWritable>(context);
	}

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		String[] data = key.toString().split(",");
		resultKey.set(data[1]);
		int sum = 0;
		
		if(data[0].equals("departure")) {
			for(IntWritable value:values) {
				sum = sum+value.get();
			}
			resultVal.set(sum);
			multiout.write("departure", resultKey, resultVal);
		}else if(data[0].equals("departnull")) {
			for(IntWritable value:values) {
				sum = sum+value.get();
			}
			resultVal.set(sum);
			multiout.write("departnull", resultKey, resultVal);
		}else if(data[0].equals("arrival")) {
			for(IntWritable value:values) {
				sum = sum+value.get();
			}
			resultVal.set(sum);
			multiout.write("arrival", resultKey, resultVal);
		}else {
			for(IntWritable value:values) {
				sum = sum+value.get();
			}
			resultVal.set(sum);
			multiout.write("arrinull", resultKey, resultVal);
		}
	}
	
}
