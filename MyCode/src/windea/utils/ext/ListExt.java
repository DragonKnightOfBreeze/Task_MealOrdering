/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package windea.utils.ext;

import org.jetbrains.annotations.Contract;

import java.util.List;

public class ListExt {
	/**
	 * 判断列表是否为null、为空。
	 */
	@Contract(value = "null -> true;", pure = true)
	public static <T> boolean orEmpty(List<T> list) {
		return list == null || list.size() == 0;
	}

	/**
	 * 判断列表是否为null、小于等于指定长度。
	 */
	@Contract(value = "null, _ -> true", pure = true)
	public static <T> boolean orLessE(List<T> list, int length) {
		length = Math.max(length, 1);
		return list == null || list.size() <= length;
	}
}
