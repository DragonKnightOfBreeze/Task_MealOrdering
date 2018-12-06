package mealordering.utils;

import java.util.UUID;

/**
 * 生成注册激活码的工具类
 * @noinspection unused, WeakerAccess
 */
public class ActiveCodeUtils {
	public static String generateActiveCode() {
		return UUID.randomUUID().toString();
	}
}
