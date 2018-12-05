package task_itcaststore.utils.ext;

import org.jetbrains.annotations.NotNull;

/**
 * String拓展类
 * @noinspection unused, WeakerAccess
 */
public class StringExt {

	/**
	 * 判断字符串是否为Null。
	 */
	public static boolean isNull(String str) {
		return str == null;
	}

	/**
	 * 判断字符串是否为Null、为空。
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str);
	}

	/**
	 * 判断字符串是否为Null、为空、为空格。
	 */
	public static boolean isSpace(String str) {
		return str == null || "".equals(str.trim());
	}


	/**
	 * 判断两个字符串是否相等。<br/>
	 * 可设置是否忽略空格、是否忽略大小写。
	 */
	public static boolean equals(String str, @NotNull String secStr, boolean ignoreSpace, boolean ignoreCase) {
		String str1 = str;
		String secStr1 = secStr;
		if (ignoreSpace) {
			str1 = str1.trim();
			secStr1 = secStr1.trim();
		}
		if (ignoreCase) {
			str1 = str1.toLowerCase();
			secStr1 = str1.toLowerCase();
		}
		return secStr1.equals(str1);
	}

	/**
	 * 判断两个字符串是否相等。
	 */
	public static boolean equals(String str, @NotNull String secStr) {
		return secStr.equals(str);
	}

	/**
	 * 判断两个字符串是否相等，忽略空格。
	 */
	public static boolean equalsIS(String str, @NotNull String secStr) {
		return equals(str, secStr, true, false);
	}

	/**
	 * 判断两个字符串是否相等，忽略大小写。
	 */
	public static boolean equalsIC(String str, @NotNull String secStr) {
		return equals(str, secStr, false, true);
	}

	/**
	 * 判断指定的字符串和指定的枚举值是否相等。
	 */
	public static boolean equalsE(String str, Enum e) {
		return e.toString().equals(str);
	}
}
