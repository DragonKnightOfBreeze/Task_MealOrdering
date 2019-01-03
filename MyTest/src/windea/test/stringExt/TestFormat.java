/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package windea.test.stringExt;

import java.text.MessageFormat;

public class TestFormat {
	public static void main(String[] args) {
		var str = "Idea";
		//已知的问题：如果模版字符串中有单引号，需要重复写两次以转义 ' -> ''
		// { ArgumentIndex , FormatType , FormatStyle }

		System.out.println(MessageFormat.format("She''s name is ''{0}''", str));
//		System.out.println(MessageFormat.format("She's name is {0}",str));
//		System.out.println(MessageFormat.format("She's name is \"{0}\"",str));
//		System.out.println(MessageFormat.format("She's name is \'{0}\'",str));
//		System.out.println(MessageFormat.format("She\'s name is \'{0}\'",str));

//		System.out.println(StringExt.f("She's name is '{0}'", str));
//		var name = "Windea";
//		String sql = StringExt.f("select * from Meal where name like '%{0}%' limit ?,?", name);
//		System.out.println(sql);
	}
}
