package pattern.exam;

import java.util.regex.Pattern;

public class EmailCheck {
	public static void main(String[] args) {
		
		String[] user = {"heaves@hanmail,net","heaves@hanmail.net",
					"테스트heaves@hanmail.net","sc.com@hanmail.net",
					",heaves@hanmail.net","@hanmail.net"
					,"heaves@hanmail.co.kr"};
		//user에 입력된 email의 형식이 맞는지 true|false로 출력할 수 있도록 작업	
		for(int i=0;i<user.length;i++) {
			String emailReg=user[i];
			//System.out.println(Pattern.matches("[\\.{}0-9A-z]+@[0-9A-z\\.]+", emailReg));
			System.out.println(Pattern.matches("([0-9A-z\\.]+@)[0-9A-z\\.]+", emailReg));
			System.out.println(Pattern.matches("^[A-z]+\\.?[0-9A-z]+@[A-z]+(\\.[a-zA-Z]+){1,2}$", emailReg));
		}
	}

}
