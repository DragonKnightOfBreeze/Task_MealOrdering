package dk_breeze.test.resourceBundle;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

class Test {
	public void test() throws Exception {
		Properties prop = new Properties();
		prop.load(getClass().getClassLoader().getResourceAsStream("dk_breeze/test/resourceBundle/text2.properties"));
		System.out.println(prop.getProperty("name"));
	}
}


public class TestRB {

	public static void main(String[] args) throws Exception {
		new Test().test();

//		System.out.println(ResourceBundle.getBundle("nihao").getString("name"));
		System.out.println(ResourceBundle.getBundle("dk_breeze/test/resourceBundle/text2").getString("name"));
		System.out.println(ResourceBundle.getBundle("dk_breeze/test/resourceBundle/text").getString("name"));
		System.out.println(ResourceBundle.getBundle("dk_breeze/test/resourceBundle/text", new Locale("en", "US")).getString("name"));
		System.out.println(ResourceBundle.getBundle("dk_breeze/test/resourceBundle/text").getString("aaa"));
	}
}
