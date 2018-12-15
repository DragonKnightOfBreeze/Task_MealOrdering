package dk_breeze.test.unsorted;

public class LambdaTest {
	public static void MyMethod(MyInterface mi) {
		System.out.println(mi.LambdaTest(1, 3));
	}

	public static void main(String[] args) {
		MyMethod((a, b) -> a + b);
	}
}

interface MyInterface {
	int LambdaTest(int a, int b);
}
