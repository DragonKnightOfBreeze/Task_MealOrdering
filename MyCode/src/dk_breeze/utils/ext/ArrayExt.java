/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package dk_breeze.utils.ext;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 数组的拓展类
 */
public class ArrayExt {

	/**
	 * 根据指定的开始点、结束点和间隔，生成一个int数组。左闭右开。
	 * @param begin 开始点，默认为0
	 * @param end 结束点
	 * @param sep 间隔，默认为1，只能为正数
	 * @return 生成的int数组
	 */
	@NotNull
	public static Integer[] range(int begin, int end, int sep) {

		boolean isAsc = true;    //是否升序
		if(begin == end || sep <= 0)
			throw new IllegalArgumentException();
		if(begin > end)
			isAsc = false;

		List<Integer> list = new LinkedList<>();
		int index = isAsc ? begin : end;
		int target = isAsc ? end : begin;
		while(index < target) {
			list.add(index);
			index += sep;
		}
		if(!isAsc) {
			list.sort(Comparator.reverseOrder());
		}
		return list.toArray(new Integer[0]);
	}

	@NotNull
	public static Integer[] range(int begin, int end) {
		return range(begin, end, 1);
	}

	@NotNull
	public static Integer[] range(int end) {
		return range(0, end, 1);

	}

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
	@Contract(value = "null, _ -> true", pure = true)
	public static <T> boolean orLessE(T[] array, int length) {
		length = Math.max(length, 1);
		return array == null || array.length <= length;
	}

}

