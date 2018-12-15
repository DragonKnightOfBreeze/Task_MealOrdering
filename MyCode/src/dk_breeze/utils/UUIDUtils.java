package dk_breeze.utils;

import java.util.UUID;

/**
 * uuid的工具类
 * @noinspection unused, WeakerAccess
 */
public class UUIDUtils {
	/**
	 * 得到随机的uuid。
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
}
