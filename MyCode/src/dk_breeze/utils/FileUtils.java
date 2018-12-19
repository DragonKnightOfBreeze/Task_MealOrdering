/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package dk_breeze.utils;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.UUID;

/**
 * 文件的工具类
 * @noinspection unused, WeakerAccess
 */
public class FileUtils {

	public static String join(@NotNull String... path) {
		return (String.join(File.separator, path));
	}

	/**
	 * 得到文件名（只有名字，没有路径）。
	 */
	public static String getFileName(@NotNull String filePath) {
		int index = filePath.lastIndexOf("\\");
		if(index == -1)
			return filePath;
		return filePath.substring(index + 1);
	}

	/**
	 * 切去文件的拓展名。
	 */
	public static String splitFileExt(@NotNull String fileName) {
		int index = fileName.lastIndexOf(".");
		if(index == -1)
			return fileName;
		return fileName.substring(0, index - 1);
	}

	/**
	 * 得到文件的扩展名。如果没有，则返回空字符串。
	 */
	public static String getFileExt(@NotNull String fileName) {
		int index = fileName.lastIndexOf(".");
		if(index == -1)
			return "";
		return fileName.substring(index + 1);
	}

	/**
	 * 生成随机的文件名。
	 */
	public static String getRandomFileName(@NotNull String fileName) {
		String uuid = UUID.randomUUID().toString();
		//尝试得到扩展名
		int index = fileName.lastIndexOf(".");
		if(index != -1)
			return uuid + fileName.substring(index);
		return uuid;
	}

	/**
	 * 生成随机的文件目录名（一级+二级）。
	 */
	public static String getRandomDirName(@NotNull String fileName) {
		int hashCode = fileName.hashCode();
		//一级目录
		int d1 = hashCode & 0xf;
		//二级目录
		int d2 = (hashCode >> 4) & 0xf;
		return "/" + d1 + "/" + d2;
	}
}
