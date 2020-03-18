package mapred.exam.productsort;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

//input data 하나마다 실행됨
public class PageViewReducer_ver1
	extends Reducer<MyKey, IntWritable,Text,Text>{
	Text resultKey = new Text();
	Text resultVal = new Text();
	@Override
	protected void reduce(MyKey key,
			Iterable<IntWritable> values,
			Reducer<MyKey, IntWritable, Text, Text>.Context context) throws IOException, InterruptedException {
		int sum = 0;//상품클릭수
		int user_count = 0;//상품을 클릭한 사용자 수
		//CustomKey 데이터 중 month데이터를 추출
		String beforeUser = key.getUserId();
		for (IntWritable value:values) {
			System.out.println(key.toString());
			if(beforeUser.equals(key.getUserId())) {
				
			}
			sum = sum+value.get();
			beforeUser = key.getUserId();
		}                              
		resultKey.set(key.getProductId());
		resultVal.set(sum+"");//int형인 sum을 String으로 형변환하기 위해 ""을 추가한다.
		context.write(resultKey, resultVal);		
		
	}
}







