package mapred.basic;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

//Reducer - 데이터를 집계하는 역할
/* 1. Reducer클래스를 상속
 * 2. Reduce메소드를 오버라이딩
 * */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	//reduce작업의 결과를 저장할 변수가 필요하므로
	IntWritable resultVal = new IntWritable();
	String data = "";
	Text appenddata = new Text();
	Text resultKey = new Text();
	
	@Override//reduce 메소드의 매개변수 
			 //key => 입력데이터의 키 타입
			 //values => 입력데이터의 값이 Iterable 형태로 전달 (ex){1,1,1,1} => 여러개 전달이므로 반복작업
			 //context => Mapper와 동일
			 //		      맵리듀스 프레임워크 안에서 기본작업을 할 수 있도록 도와주는 역할
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) 
					throws IOException, InterruptedException {
		int sum = 0;
		//Reduce에 전달된 입력데이터 값을 꺼내서 모두 더하기
		data = data + "reduce호출";
		appenddata.set(data);
		for(IntWritable value:values) {//values의 데이터를 하나씩 꺼내서 value에 저장
			sum = sum+value.get();//value 데이터의 합계를 sum에 저장
		}
		resultVal.set(sum);//계산결과를 IntWritable에 세팅
		resultKey.set(key+":"+appenddata);
		context.write(resultKey, resultVal);//Context객체 write메소드로 output으로 내보낼 데이터 정의
	}
}
