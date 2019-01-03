package windea.test.unsorted;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class SortTest {

	private List<Person> people = new ArrayList<>();

	public void sortTest1() {
		Collections.sort(people, new Comparator<Person>() {
			public int compare(Person x, Person y) {
				return x.getName().compareTo(y.getName());
			}
		});
	}

	public void sortTest2() {
		Collections.sort(people, (Person x, Person y) -> x.getName().compareTo(y.getName()));
	}

	public void sortTest3() {
		Collections.sort(people, Comparator.comparing((Person p) -> p.getName()));
	}

	public void sortTest4() {
		Collections.sort(people, Comparator.comparing(p -> p.getName()));
	}

	public void sortTest5() {
		Collections.sort(people, Comparator.comparing(Person::getName));
	}

	public void sortTest6() {
		people.sort(Comparator.comparing(Person::getName));
	}


	public void sortTest_Reverse() {
		people.sort(Comparator.comparing(Person::getName).reversed());
	}
}


class Person {
	private String name;

	String getName() {
		return name;
	}
}
