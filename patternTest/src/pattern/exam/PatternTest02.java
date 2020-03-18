package pattern.exam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sound.midi.Soundbank;

public class PatternTest02 {

	public static void main(String[] args) {
		String str = "ja1111aCva--@@-@@@@- 한글 --@@@@-- progra44568EmgFmiJng";
		//String patternStr = "a|m|g"; // |는 or을 의미 => a 또는 m 또는 g 문자열 추출
		//String patternStr = "[amg]"; //위와 동일
		//String patternStr = "[amg][ma]";//첫글자가 a /m /g이거나 두번째 글자가 m/ a
		//String patternStr = "[c-j]";// 알파벳 c와 j사이에 해당하는 문자
		//String patternStr = "[A-Bc-j]";// 대문자 A,B 소문자 c-j까지 문자 추출
		//String patternStr = "[4-8]";//숫자 4에서 8사이 숫자를 추출
		//String patternStr = "[^4-8]";// ^가 []안에 있으면 부정의 의미 =. 숫자 4-8이 아닌 문자
		//String patternStr = "[^c-j]";//c-j사이의 영문자가 아닌 것
		//String patternStr = "[A-Za-z0-9]";//영문자와 숫자만 추출
		String patternStr = "[가-힣]";//한글만 추출
		equalsPattern(str, patternStr);
	}
	public static void equalsPattern(String str, String patternStr) {
		//1. 패턴을 인식
		Pattern pattern = Pattern.compile(patternStr);
		//2. 패턴을 적용하여 문자열을 관리
		Matcher m = pattern.matcher(str);
		
		while(m.find()) {
			System.out.println(m.group());
			System.out.println(m.start()+":"+(m.end()-1));
			}
	}

}
