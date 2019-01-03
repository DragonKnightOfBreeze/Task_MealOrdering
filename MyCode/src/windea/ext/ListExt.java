/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package windea.ext;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 列表的拓展类
 */
public class ListExt {
	/**
	 * 判断列表是否为空。
	 */
	public static <T> boolean isEmpty(@NotNull List<T> list) {
		return list.isEmpty();
	}

	/**
	 * 判断列表是否为null、为空。
	 */
	@Contract("null -> true")
	public static <T> boolean orEmpty(@Nullable List<T> list) {
		return list == null || list.size() == 0;
	}

	/**
	 * 判断列表是否小于等于指定长度。
	 */
	@Contract("null, _ -> true")
	public static <T> boolean isLessE(@NotNull List<T> list, int length) {
		length = Math.max(length, 0);
		return list.size() <= length;
	}

	/**
	 * 判断列表是否为null、小于等于指定长度。
	 */
	@Contract("null, _ -> true")
	public static <T> boolean orLessE(@Nullable List<T> list, int length) {
		length = Math.max(length, 0);
		return list == null || list.size() <= length;
	}
}
