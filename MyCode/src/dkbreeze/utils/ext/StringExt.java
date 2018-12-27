/*
 * Copyright (c) $today.year.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of Wind Fairy.
 */

package dkbreeze.utils.ext;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

/**
 * String类的拓展类
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
	 * 判断字符串是否为空。
	 */
	@Contract(value = "null -> true; !null -> false", pure = true)
	public static boolean isEmpty(String str) {
		return str.isEmpty();
	}

	/**
	 * 判断字符串是否为Null、为空。
	 */
	@Contract(value = "null -> true; !null -> false", pure = true)
	public static boolean orEmpty(String str) {
		return str == null || str.isEmpty();
	}

	/**
	 * 判断字符串是否为空、为空格（制表符、换行符）。
	 */
	@Contract(value = "null -> true; !null -> false", pure = true)
	public static boolean isBlank(String str) {
		return str.isBlank();
	}

	/**
	 * 判断字符串是否为Null、为空、为空格（制表符、换行符）。
	 */
	@Contract(value = "null -> true; !null -> false", pure = true)
	public static boolean orBlank(String str) {
		return str == null || str.isBlank();
	}


	/**
	 * 判断两个字符串是否相等。<br>
	 * 可设置是否忽略空格、是否忽略大小写。
	 */
	public static boolean equals(String str, @NotNull String secStr, boolean ignoreSpace, boolean ignoreCase) {
		String str1 = str;
		String secStr1 = secStr;
		if(ignoreSpace) {
			str1 = str1.trim();
			secStr1 = secStr1.trim();
		}
		if(ignoreCase) {
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
		} catch(Exception e) {
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

	public static double toDouble(String str) {
		return toDouble(str, 0.0);
	}

	public static double toDouble(String str, double defaultNum) {
		double result;
		try {
			result = Integer.parseInt(str.trim());
		} catch(Exception e) {
			result = defaultNum;
		}
		return result;
	}

	/**
	 * 格式化字符串（自动转义单引号）。
	 * <ul>
	 * <li>占位符格式：{index,type,style}</li>
	 * <li>index：参数的索引，从0开始</li>
	 * <li>type：格式化类型，可选值：number，date，time，choice</li>
	 * <li>style：格式化风格，可选值：short，medium，long，full，integer，currency，percent，subformatPattern</li>
	 * </ul>
	 * @param pattern 模版字符串
	 * @param args 对应数量的参数
	 */
	public static String f(@NotNull String pattern, @NotNull Object... args) {
		return MessageFormat.format(pattern.replace("'", "''"), args);
	}
}
