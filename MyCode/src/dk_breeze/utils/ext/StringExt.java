package dk_breeze.utils.ext;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * String拓展类
 */
public class StringExt {

	/**
	 * 判断字符串是否为Null。
	 */
	@Contract(value = "null -> true; !null -> false", pure = true)
	public static boolean isNull(String str) {
		return str == null;
	}

	/**
	 * 判断字符串是否为Null、为空。
	 */
	@Contract(value = "null -> true", pure = true)
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str);
	}

	/**
	 * 判断字符串是否为Null、为空、为空格。
	 */
	@Contract("null -> true")
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

	/**
	 * 将指定的字符串转化为数字（忽略空格）。如果失败，则转化为默认值。
	 * @param str 指定的字符串
	 * @param defaultNum 默认值
	 */
	public static int toInt(String str, int defaultNum) {
		int result;
		try {
			result = Integer.parseInt(str.trim());
		} catch (Exception e) {
			result = defaultNum;
		}
		return result;
	}

	/**
	 * 将指定的字符串转化为数字（忽略空格）。如果失败，则转化为0。
	 * @param str 指定的字符串
	 */
	public static int toInt(String str) {
		return toInt(str, 0);
	}
}
