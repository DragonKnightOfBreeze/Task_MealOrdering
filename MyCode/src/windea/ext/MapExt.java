/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */
package windea.ext;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * 图表的拓展类
 */
public class MapExt {
	/**
	 * 判断图表是否为空。
	 */
	@Contract(pure = true)
	public static <K, V> boolean isEmpty(@NotNull Map<K, V> map) {
		return map.isEmpty();
	}

	/**
	 * 判断图表是否为null、为空。
	 */
	@Contract("null -> true")
	public static <K, V> boolean orEmpty(@Nullable Map<K, V> map) {
		return map == null || map.size() == 0;
	}

	/**
	 * 判断图表是否小于等于指定长度。
	 */
	@Contract("null, _ -> true")
	public static <K, V> boolean isLessE(@NotNull Map<K, V> map, int length) {
		length = Math.max(length, 0);
		return map.size() <= length;
	}

	/**
	 * 判断图表是否为null、小于等于指定长度。
	 */
	@Contract("null, _ -> true")
	public static <K, V> boolean orLessE(@Nullable Map<K, V> map, int length) {
		length = Math.max(length, 0);
		return map == null || map.size() <= length;
	}
}

