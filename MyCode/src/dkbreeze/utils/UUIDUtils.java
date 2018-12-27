package dkbreeze.utils;

import java.util.UUID;

/**
 * uuid的工具类
 */
public class UUIDUtils {
	/**
	 * 得到随机的uuid。
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
}
