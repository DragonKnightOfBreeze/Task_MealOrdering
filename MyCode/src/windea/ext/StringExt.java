/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package windea.ext;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import windea.annotation.Outlook;

import java.text.MessageFormat;

/**
 * String类的拓展类
 */
public class StringExt {
	/**
	 * 判断字符串是否为空。
	 */
	@Contract(pure = true)
	public static boolean isEmpty(@NotNull String str) {
		return str.isEmpty();
	}

	/**
	 * 判断字符串是否为Null、为空。
	 */
	@Contract(value = "null -> true; !null -> false", pure = true)
	public static boolean orEmpty(@Nullable String str) {
		return str == null || str.isEmpty();
	}

	/**
	 * 判断字符串是否为空、为空白。
	 */
	public static boolean isBlank(@NotNull String str) {
		return str.isBlank();
	}

	/**
	 * 判断字符串是否为Null、为空、为空白。
	 */
	@Contract(value = "null -> true; !null -> false", pure = true)
	public static boolean orBlank(@Nullable String str) {
		return str == null || str.isBlank();
	}

	/**
	 * 判断两个字符串是否相等。<br>
	 * 要比较的两个字符串可为null。
	 */
	@Contract(value = "null, null -> true; null, !null -> false; !null, null -> false", pure = true)
	public static boolean equals(@Nullable String str, @Nullable String secStr) {
		if(str == null && secStr == null) {
			return true;
		} else if(str == null || secStr == null) {
			return false;
		} else {
			return str.equals(secStr);
		}
	}

	/**
	 * 判断两个字符串是否相等，忽略空白。<br>
	 * 要比较的两个字符串可为null。
	 */
	@Contract("null, null -> true; null, !null -> false; !null, null -> false")
	public static boolean equalsIB(@Nullable String str, @Nullable String secStr) {
		if(str == null && secStr == null) {
			return true;
		} else if(str == null || secStr == null) {
			return false;
		} else {
			return str.trim().equals(secStr.trim());
		}
	}

	/**
	 * 判断两个字符串是否相等，忽略大小写。<br>
	 * 要比较的两个字符串可为null。
	 */
	@Contract(value = "null, null -> true; null, !null -> false; !null, null -> false", pure = true)
	public static boolean equalsIC(@Nullable String str, @Nullable String secStr) {
		if(str == null && secStr == null) {
			return true;
		} else if(str == null || secStr == null) {
			return false;
		} else {
			return str.equalsIgnoreCase(secStr);
		}
	}

	/**
	 * 判断两个字符串是否相等，忽略空白和大小写。<br>
	 * 要比较的两个字符串可为null。
	 */
	@Contract("null, null -> true; null, !null -> false; !null, null -> false")
	public static boolean equalsIBC(@Nullable String str, @Nullable String secStr) {
		if(str == null && secStr == null) {
			return true;
		} else if(str == null || secStr == null) {
			return false;
		} else {
			return str.trim().equalsIgnoreCase(secStr.trim());
		}
	}

	/**
	 * 判断指定的字符串和指定的枚举值是否相等。
	 */
	@Contract("null, _ -> false")
	public static <E extends Enum> boolean equalsE(@Nullable String str, @NotNull E e) {
		return e.toString().equals(str);
	}


	/**
	 * 将指定的字符串转化为整型数值（忽略空白）。如果失败，则转化为默认值。
	 * @param str 指定的字符串
	 * @param defaultNum 默认值
	 */
	public static int toInt(@Nullable String str, int defaultNum) {
		str = str == null ? "" : str.trim();
		int result;
		try {
			result = Integer.parseInt(str);
		} catch(Exception e) {
			result = defaultNum;
		}
		return result;
	}

	/**
	 * 将指定的字符串转化为整型数值（忽略空白）。如果失败，则转化为0。
	 * @param str 指定的字符串
	 */
	public static int toInt(@Nullable String str) {
		return toInt(str, 0);
	}

	/**
	 * 将指定的字符串转化为单精度数值（忽略空白）。如果失败，则转化为默认值。
	 * @param str 指定的字符串
	 * @param defaultNum 默认值
	 */
	public static float toFloat(@Nullable String str, float defaultNum) {
		str = str == null ? "" : str.trim();
		float result;
		try {
			result = Float.parseFloat(str);
		} catch(Exception e) {
			result = defaultNum;
		}
		return result;
	}

	/**
	 * 将指定的字符串转化为单精度数值（忽略空白）。如果失败，则转化为默认值。
	 * @param str 指定的字符串
	 */
	public static float toFloat(@Nullable String str) {
		return toFloat(str, 0.0f);
	}

	/**
	 * 将指定的字符串转化为双精度数值（忽略空白）。如果失败，则转化为默认值。
	 * @param str 指定的字符串
	 * @param defaultNum 默认值
	 */
	public static double toDouble(@Nullable String str, double defaultNum) {
		str = str == null ? "" : str.trim();
		double result;
		try {
			result = Double.parseDouble(str);
		} catch(Exception e) {
			result = defaultNum;
		}
		return result;
	}

	/**
	 * 将指定的字符串转化为双精度数值（忽略空白）。如果失败，则转化为0。
	 * @param str 指定的字符串
	 */
	public static double toDouble(@Nullable String str) {
		return toDouble(str, 0.0);
	}

	/**
	 * 如果指定的字符串为null、为空、为空格，则转化为空字符串。
	 * @param str 指定的字符串
	 */
	@NotNull
	public static String toStr(@Nullable String str) {
		return toStr(str, "");
	}

	/**
	 * 如果指定的字符串为null、为空、为空格，则转化为默认值。
	 * @param str 指定的字符串
	 * @param defaultStr 默认值
	 */
	@Contract("null, _ -> param2")
	@NotNull
	public static String toStr(@Nullable String str, @NotNull String defaultStr) {
		if(str == null || str.isBlank())
			return defaultStr;
		return str;
	}

	/**
	 * 将指定的字符串转化为枚举值。如果不匹配，则返回默认值。
	 * @param str 指定的字符串
	 * @param clazz 枚举类
	 * @param defaultVal 默认值
	 */
	@NotNull
	public static <E extends Enum> E toEnum(@Nullable String str, @NotNull Class<E> clazz, @NotNull E defaultVal) {
		E result = toEnum(str, clazz);
		return result == null ? defaultVal : result;
	}

	/**
	 * 将指定的字符串转化为枚举值。如果不匹配，则返回null。
	 * @param str 指定的字符串
	 * @param clazz 枚举类
	 */
	@Nullable
	public static <E extends Enum> E toEnum(@Nullable String str, @NotNull Class<E> clazz) {
		E[] consts = clazz.getEnumConstants();
		for(E c : consts) {
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
	public static <E extends Enum> int toEnumOrd(@Nullable String str, @NotNull Class<E> clazz) {
		Enum e = toEnum(str, clazz);
		return e == null ? -1 : e.ordinal();
	}


	/**
	 * 模拟模版字符串。<br>
	 * 自动转义单引号。
	 * @param pattern 模版字符串
	 * @param args 参数
	 * @see MessageFormat
	 */
	@Outlook
	@NotNull
	public static String f(@NotNull String pattern, @NotNull Object... args) {
		return MessageFormat.format(pattern.replace("'", "''"), args);
	}

	/**
	 * 模拟多行字符串。
	 * @param strArray 每行的字符串
	 */
	@Outlook
	@NotNull
	public static String m(@NotNull String... strArray) {
		return String.join("\n", strArray);
	}
}
