package dk_breeze.test.unsorted;

import java.lang.annotation.Annotation;

public class AnnotationTest {
	public static <A extends Annotation> void getAnnotationInfo(Class<?> clazz, Class<A> annotation) {
		if(!clazz.isAnnotationPresent(annotation))
			return;

		var anno = clazz.getAnnotation(annotation);
		var fields = anno.getClass().getFields();
		for(var field : fields) {
			System.out.println(field.getName() + "\t" + field.toString());
		}
	}
}

@MyAnnotation("value")
class MyClass {

}

/**
 * 自定义注释
 */
@interface MyAnnotation {
	/**
	 * 键
	 */
	String key() default "key";

	/**
	 * 值
	 */
	String value();

}
