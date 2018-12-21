package mealordering.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 权限的注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Permission {
	enum P {
		Client, Admin, All, System, View
	}

	P value() default P.Client;
}
