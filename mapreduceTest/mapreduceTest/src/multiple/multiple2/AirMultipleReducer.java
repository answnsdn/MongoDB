package multiple.multiple2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class AirMultipleReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	// reduce작업의 결과를 저장할 변수
	IntWritable resultVal = new IntWritable();
	MultipleOutputs<Text, IntWritable> multiOut;
	Text resultKey = new Text();

	@Override
	protected void setup(Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		multiOut = new MultipleOutputs<Text, IntWritable>(context);
	}

	@Override
	protected void cleanup(Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		multiOut.close();
	}

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {

		String[] data = key.toString().split(",");
		resultKey.set(data[1]);

		// 2. key에 추가된 구분자별로 output
		if (data[0].equals("departure")) {
			int sum = 0;
			for (IntWritable value : values) {
				sum = sum + value.get();
			}
			resultVal.set(sum);// output value에 추가하기
			multiOut.write("departure", resultKey, resultVal);
		} else if (data[0].equals("arrival")) {
			int sum = 0;
			for (IntWritable value : values) {
				sum = sum + value.get();
			}
			resultVal.set(sum);// output value에 추가하기
			multiOut.write("arrival", resultKey, resultVal);
		} else if(data[0].equals("na14")){
			int sum = 0;
			for (IntWritable value : values) {
				sum = sum + value.get();
			}
			resultVal.set(sum);// output value에 추가하기
			multiOut.write("na14", resultKey, resultVal);
		}
		else if(data[0].equals("na15")){
			int sum = 0;
			for (IntWritable value : values) {
				sum = sum + value.get();
			}
			resultVal.set(sum);// output value에 추가하기
			multiOut.write("na15", resultKey, resultVal);
		}
	}

}
