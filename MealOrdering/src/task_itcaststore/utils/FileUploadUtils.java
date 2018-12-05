package task_itcaststore.utils;

import java.util.UUID;

/**
 * 文件上传的工具类
 * @noinspection unused, WeakerAccess
 */
public class FileUploadUtils {

	/**
	 * 得到真实的文件名（只有名字，没有路径）。
	 */
	public static String getFileName(String filePath) {
		//查找最后一个 \出现位置
		int index = filePath.lastIndexOf("\\");
		if (index == -1)
			return filePath;
		return filePath.substring(index + 1);
	}

	/**
	 * 生成随机的文件名。
	 */
	public static String generateRandomFileName(String fileName) {
		String uuid = UUID.randomUUID().toString();
		//获得扩展名
		int index = fileName.lastIndexOf(".");
		if (index != -1)
			return uuid + fileName.substring(index);
		return uuid;
	}

	/**
	 * 生成随机的文件目录（一级+二级）。
	 */
	public static String generateRandomDir(String uuidFileName) {
		int hashCode = uuidFileName.hashCode();
		//一级目录
		int d1 = hashCode & 0xf;
		//二级目录
		int d2 = (hashCode >> 4) & 0xf;
		return "/" + d1 + "/" + d2;
	}
}
