package pattern.exam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PatternAPITest {

	public static void main(String[] args) {
		String value = "배송이 너무 느려요 상품이 좋아서 예뻐요 좋아 좋아";
		Pattern p = Pattern.compile("은|는|이|가|요|서");
		Matcher m = p.matcher(value);
		//패턴에 일치하지 않는 문자들만 추출해서 저장
		StringBuffer sb = new StringBuffer();
		while(m.find()) {
			String data = m.group();
			System.out.println(data);
			//패턴에 만족하는 문자열을 ""로 치환한 후 전체 문자열을 StringBuffer에 저장
			m.appendReplacement(sb, "");
		}
		System.out.println(sb);
		//조건에 만족하지 않는 문자 추가할 수 있도록 구현
		m.appendTail(sb);
		System.out.println(sb);
		String[] result = sb.toString().split(" ");
		for(int i=0;i<result.length;i++) {
			System.out.println(result[i]);
		}

	}

}
