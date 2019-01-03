/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package windea.utils.ext;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * 数组的拓展类
 */
public class ArrayExt {
	/**
	 * 判断数组是否为null、为空。
	 */
	@Contract(value = "null -> true;", pure = true)
	public static <T> boolean orEmpty(T[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * 判断数组是否为null、小于等于指定长度。
	 */
	public static <T> boolean orLessE(T[] array, int length) {
		length = Math.max(length, 1);
		return array == null || array.length <= length;
	}

	public static <T> String[] toStringArray(@NotNull T[] array) {
		String[] result = new String[array.length];
		for(int i = 0; i < array.length; i++) {
			result[i] = array[i].toString();
		}
		return result;
	}

	public static String[] toStringArray(@NotNull int[] array) {
		String[] result = new String[array.length];
		for(int i = 0; i < array.length; i++) {
			result[i] = String.valueOf(array[i]);
		}
		return result;
	}

}

