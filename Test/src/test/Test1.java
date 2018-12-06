package test;

import dk_breeze.utils.ext.StringExt;

public class Test1 {
	public static void main(String[] args) {
//		String str = null;
////		System.out.println(str.trim());

//		System.out.println("".isBlank());
//		System.out.println("   ".isBlank());
//		System.out.println("".isEmpty());
//		System.out.println("   ".isEmpty());
//		String str = null;
////		System.out.println(str.isEmpty());
//		System.out.println(str.isBlank());
		System.out.println(StringExt.toInt("123"));
		System.out.println(StringExt.toInt("123 "));
		System.out.println(StringExt.toInt("aaa"));
		System.out.println(StringExt.toInt("123", 5));
		System.out.println(StringExt.toInt("123 ", 5));
		System.out.println(StringExt.toInt("aaa ", 5));
	}
}
