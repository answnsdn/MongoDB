package pattern.exam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sound.midi.Soundbank;

public class PatternTest05 {

	public static void main(String[] args) {
		//String str = "ja1111aCva--@@-@@@@- 한글 --@@@@-- progra44568EmgFmiJng";
		String str = "tomato jaava tomato prog potato";
		//String patternStr = ".*";//한글만 추출
		//String patternStr = "-@?-";
		//String patternStr = "[^ ]";//공백이 아닌 문자
		//String patternStr = ".{5}";
		//String patternStr = "[amv] {1,3}";//a,m,v가 1회, 2회, 3회인 문자
		//String patternStr = "[a-z]{3,}";
		//String patternStr = "\\W";//대문자, 소문자, 숫자  뺀 모두
		//String patternStr = "\\w";//대문자, 소문자, 숫자 모두
		//String patternStr = "\\d";//숫자만 추출
		//String patternStr = "(tom|pot)ato";
		String patternStr = "(a|i){3}bc";
		
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
