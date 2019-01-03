package windea.test.unsorted;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
	public static void main(String[] args) {
		String pattern = ".*(直剑)";
		String str = "环印骑士直剑";

		Pattern pat = Pattern.compile(pattern);
		Matcher matcher = pat.matcher(str);
		while(matcher.find()) {        //必须要开始查找
			System.out.println(matcher.group(1));
		}

		String str2 = str.replaceAll("(.*)(直剑)", "$1的$2");
		System.out.println(str2);
	}
}
