package pattern.exam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sound.midi.Soundbank;

public class PatternTest04 {

	public static void main(String[] args) {
		System.out.println(Pattern.matches("[0-9]+", "1234java"));
		System.out.println(Pattern.matches("[0-9]+", "1234"));
	}

}
