package mapred.product.sort;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PageViewReducer extends Reducer<Mykey, IntWritable, Mykey, IntWritable>{
	IntWritable resultVal = new IntWritable();
	Mykey resultkey = new Mykey();
	@Override
	protected void reduce(Mykey key, Iterable<IntWritable> values,
			Reducer<Mykey, IntWritable, Mykey, IntWritable>.Context context) throws IOException, InterruptedException {
		int sum = 0;
		int usersum = 1;
		String beforeUserId = key.getUserId();
		for(IntWritable value : values) {
			if(!beforeUserId.equals(key.getUserId())) {
				usersum = usersum + 1;
			}
			sum = sum + value.get();
			beforeUserId = key.getUserId();
		}
		resultkey.setProductId(key.getProductId());
		resultkey.setUserId(usersum+"");
		resultVal.set(sum);
		context.write(resultkey, resultVal);
	}
	
	
}
