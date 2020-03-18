package mapred.basic;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;//intwritable보다 큰 값을 받기 위함
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//Mapper 역할을 하는 클래스<Mapper에서 들어가는 데이터 타입 정의>
//1. Mapper클래스를 상속한다.
//	=> mapper에 전달될 input데이터의 key, value타입과 mapper의 실행결과로 출력되는 output 데이터의 key, value 타입을 정의

//2. map메소드를 오버라이딩해서 map작업을 수행하면서 처리할 내용을 구현
//	=> 입력된 값을 분석하기 위한 메소드 : 입력된 데이터에 조건을 적용하여 원하는 데이터를 추출하기 위한 반복작업 수행
// map메소드의 매개 변수 - 입력데이터 키, 입력값, Context
//		- Context : 맵리듀스 작업을 수행하며 맵메소드의 실행결과 - 즉, 출력데이터를 기록, shuffle하고 리듀스로
//					내보내는 작업을 수행하는 객체
//					프레임워크 내부에서 기본작업을 처리하는 객체
//					내부에서 머신들끼리 통신할 때 필요한 여러 정보를 갖고 있다.
public class WordCountMapper extends Mapper<LongWritable,Text, Text, IntWritable>{
	//output데이터를 mapper의 실행결과로 내보낼 수 있도록 키와 value를 저장하는 변수
	static final IntWritable outputVal = new IntWritable(1);//static final : 상수로 변환해줌
	Text outputKey = new Text();
	
	@Override
	protected void map(LongWritable key, Text value,
					Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		//key는 linenumber, value는 입력데이터 한 라인에 해당하는 문장 ex) 1
		//StringTokenizer : value를 공백을 기준으로 나누어주는 객체 ex) read a book
		StringTokenizer st = new StringTokenizer(value.toString());
		//공백을 기준으로 나누면 value(=Token)가 여러 개 생성되므로 while문을 돌려준다.
		while(st.hasMoreTokens()) {//Token이 없을 때까지 반복해서 꺼낸다.(True/False)
			String token = st.nextToken();//공백을 기준으로 나눈 value(=Token)을 저장하는 객체
			outputKey.set(token);//Token을 맵의 output 데이터에 set한다.
			context.write(outputKey, outputVal);//Context객체의 write메소드를 통해 output으로 내보낼 데이터 정의
		}
	}
	
}
