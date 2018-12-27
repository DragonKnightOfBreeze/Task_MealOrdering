package dkbreeze.test.unsorted;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListTest {
	private static List<Integer> list = new ArrayList<>();

	private static void reverse(List<Integer> list) {
		list.sort(Comparator.reverseOrder());
	}

	public static void main(String[] args) {
		list.add(1);
		list.add(2);
		list.add(3);
		reverse(list);
		for(var l : list) {
			System.out.println(l);
		}
	}
}

class Student {

}
