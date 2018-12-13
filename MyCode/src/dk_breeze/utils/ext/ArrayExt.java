package dk_breeze.utils.ext;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 数组的拓展类
 */
public class ArrayExt {
//	public static void main(String[] args) {
//		System.out.println(Arrays.toString(range(10)));
//		System.out.println(Arrays.toString(range(10,0)));
//		System.out.println(Arrays.toString(range(0,10,2)));
//		System.out.println(Arrays.toString(range(50,-20,8)));
//	}

	/**
	 * 根据指定的开始点、结束点和间隔，生成一个int数组。左闭右开。
	 * @param begin 开始点，默认为0
	 * @param end 结束点
	 * @param sep 间隔，默认为1，只能为正数
	 * @return 生成的int数组
	 */
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

	public static Integer[] range(int begin, int end) {
		return range(begin, end, 1);
	}

	public static Integer[] range(int end) {
		return range(0, end, 1);
	}

}

