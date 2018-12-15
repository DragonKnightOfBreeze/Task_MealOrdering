package mealordering.utils;

import java.util.UUID;

/**
 * uuid的工具类
 * @noinspection unused, WeakerAccess
 */
@Deprecated
public class IdUtils {
	/**
	 * 得到随机的uuid。
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
}
