/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */
package windea.ext;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * 集合的拓展类
 */
public class SetExt {
	/**
	 * 判断集合是否为空。
	 */
	public static <T> boolean isEmpty(@NotNull Set<T> set) {
		return set.isEmpty();
	}

	/**
	 * 判断集合是否为null、为空。
	 */
	@Contract("null -> true")
	public static <T> boolean orEmpty(@Nullable Set<T> set) {
		return set == null || set.size() == 0;
	}

	/**
	 * 判断集合是否小于等于指定长度。
	 */
	@Contract("null, _ -> true")
	public static <T> boolean isLessE(@NotNull Set<T> set, int length) {
		length = Math.max(length, 0);
		return set.size() <= length;
	}

	/**
	 * 判断集合是否为null、小于等于指定长度。
	 */
	@Contract("null, _ -> true")
	public static <T> boolean orLessE(@Nullable Set<T> set, int length) {
		length = Math.max(length, 0);
		return set == null || set.size() <= length;
	}
}

