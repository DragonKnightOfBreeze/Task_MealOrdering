/*
 * Copyright (c) $today.year.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of Wind Fairy.
 */

package dkbreeze.utils.ext;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

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
	public static boolean isOrEmpty(String str) {
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
	public static boolean isOrBlank(String str) {
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
	@Contract("null, _ -> false")
	public static boolean equalsE(String str, @NotNull Enum<?> e) {
		return e.toString().equals(str);
	}

	/**
	 * 将指定的字符串转化为整型数值（忽略空格）。如果失败，则转化为默认值。
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
	 * 将指定的字符串转化为整型数值（忽略空格）。如果失败，则转化为0。
	 * @param str 指定的字符串
	 */
	public static int toInt(String str) {
		return toInt(str, 0);
	}

	/**
	 * 将指定的字符串转化为单精度数值（忽略空格）。如果失败，则转化为默认值。
	 * @param str 指定的字符串
	 * @param defaultNum 默认值
	 */
	public static float toFloat(String str, float defaultNum) {
		float result;
		try {
			result = Float.parseFloat(str.trim());
		} catch(Exception e) {
			result = defaultNum;
		}
		return result;
	}

	/**
	 * 将指定的字符串转化为单精度数值（忽略空格）。如果失败，则转化为默认值。
	 * @param str 指定的字符串
	 */
	public static float toFloat(String str) {
		return toFloat(str, 0.0f);
	}

	/**
	 * 将指定的字符串转化为双精度数值（忽略空格）。如果失败，则转化为默认值。
	 * @param str 指定的字符串
	 * @param defaultNum 默认值
	 */
	public static double toDouble(String str, double defaultNum) {
		double result;
		try {
			result = Double.parseDouble(str.trim());
		} catch(Exception e) {
			result = defaultNum;
		}
		return result;
	}

	/**
	 * 将指定的字符串转化为双精度数值（忽略空格）。如果失败，则转化为0。
	 * @param str 指定的字符串
	 */
	public static double toDouble(String str) {
		return toDouble(str, 0.0);
	}

	/**
	 * 如果指定的字符串为null、为空、为空格，则转化为空字符串。
	 * @param str 指定的字符串
	 */
	public static String toStr(String str) {
		return toStr(str, "");
	}

	/**
	 * 如果指定的字符串为null、为空、为空格，则转化为默认值。
	 * @param str 指定的字符串
	 * @param defaultStr 默认值
	 */
	@Contract("null, _ -> param2")
	public static String toStr(String str, String defaultStr) {
		if(str == null || str.isBlank())
			return defaultStr;
		return str;
	}

	/**
	 * 将指定的字符串转化为枚举值。如果不匹配，则返回默认值。
	 * @param str 指定的字符串
	 * @param clazz 枚举类
	 * @param defaultValue 默认值
	 */
	public static Enum toEnum(String str, Class<? extends Enum> clazz, Enum<?> defaultValue) {
		var result = toEnum(str, clazz);
		return result == null ? defaultValue : result;
	}

	/**
	 * 将指定的字符串转化为枚举值。如果不匹配，则返回null。
	 * @param str 指定的字符串
	 * @param clazz 枚举类
	 */
	public static Enum toEnum(String str, Class<? extends Enum> clazz) {
		var consts = clazz.getEnumConstants();
		for(var c : consts) {
			if(c.toString().equals(str)) {
				return c;
			}
		}
		return null;
	}

	/**
	 * 将指定的字符串转化为枚举值的序数。如果不匹配，则返回-1。
	 * @param str 指定的字符串
	 * @param clazz 枚举类
	 */
	public static int toEnumOrd(String str, Class<? extends Enum> clazz) {
		var e = toEnum(str, clazz);
		return e == null ? -1 : e.ordinal();
	}
}
