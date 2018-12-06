package mealordering.utils;

import java.util.UUID;

/**
 * 得到随机的uuid的工具类
 * @noinspection unused, WeakerAccess
 */
public class IdUtils {
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
}
