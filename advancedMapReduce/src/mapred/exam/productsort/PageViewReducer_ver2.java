package mapred.exam.productsort;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

//input data 하나마다 실행됨
public class PageViewReducer_ver2
	extends Reducer<MyKey,Text,Text,Text>{
	Text resultKey = new Text();
	Text resultVal = new Text();
	@Override
	protected void reduce(MyKey key,
			Iterable<Text> values,
			Reducer<MyKey, Text, Text, Text>.Context context) throws IOException, InterruptedException {
		int sum = 0;//상품클릭수
		int user_count = 0;//상품을 클릭한 사용자 수
		//CustomKey 데이터 중 month데이터를 추출
		String beforeUser = "";
		for (Text value:values) {
			String currentUser = value.toString();
			if(!beforeUser.equals(currentUser)) {
				user_count++; //사용자가 다른 경우
			}
			sum++;//하나의 상품에 접속한 모든 횟수
			beforeUser = currentUser;
		}
		//상품코드가 바뀔 때마다 출력
		resultKey.set(key.getProductId());
		StringBuffer data = new StringBuffer();
		data.append(user_count).append("\t").append(sum);
		resultVal.set(data.toString());
		context.write(resultKey, resultVal);		
		
	}
}







