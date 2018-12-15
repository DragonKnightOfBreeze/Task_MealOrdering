//测试函数式编程

package dk_breeze.test.unsorted;

import java.util.Arrays;

public class StreamTest {
	public static void main(String[] args) {

		int[] nums = {1, 2, 3};

		//根据条件过滤集合，返回一个相同类型的集合
		//Lambda表达式：接受一个同类型的参数，返回布尔值
		int[] nums1 = Arrays.stream(nums).filter(x -> x % 2 == 0).toArray();
		for(var n : nums1)
			System.out.println(n);

		//根据条件改变这个集合中的所有元素，返回一个相同类型的集合
		//Lambda表达式：接受一个同类型的参数，返回一个同类型的值
		int[] nums2 = Arrays.stream(nums).map(x -> x + 2).toArray();
		for(var n : nums2)
			System.out.println(n);

		//根据条件对一个集合中的数值做累积运算（前两个运算，所得结果在和第三个运算...）
		//Lambda表达式：接受两个同类型的参数，返回一个同类型的值
		int num3 = Arrays.stream(nums).reduce((x, y) -> x + y).getAsInt();
		System.out.print(num3 + "\t");


	}
}
