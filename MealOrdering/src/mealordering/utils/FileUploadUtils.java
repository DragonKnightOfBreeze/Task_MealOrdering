package mealordering.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static mealordering.utils.FileUtils.*;

/**
 * 文件上传的工具类
 * @noinspection unused, WeakerAccess
 */
public class FileUploadUtils {
	/** HttpServletRequest对象 */
	private HttpServletRequest request;
	/** 全部上传内容的列表 */
	private List<FileItem> itemList;
	/** 全部参数的图表 */
	private Map<String, List<String>> paramMap = null;
	/** 全部上传文件的图表 */
	private List<FileItem> fileList = null;
	/** 默认的上传文件大小限制（3M） */
	private int maxSize = 3 * 1024 * 1024;


	/**
	 * 构造方法。
	 * @param request HttpServletRequest对象
	 */
	public FileUploadUtils(HttpServletRequest request) throws Exception {
		this(request, -1);
	}

	/**
	 * 构造方法。
	 * @param request HttpServletRequest对象
	 * @param maxSize 上传文件大小限制
	 */
	public FileUploadUtils(HttpServletRequest request, int maxSize) throws Exception {
		this(request, maxSize, request.getServletContext().getRealPath("/mealordering/temp") + "");
	}

	/**
	 * 构造方法。
	 * @param request HttpServletRequest对象
	 * @param maxSize 上传文件大小限制
	 * @param tempDirName 临时文件夹名
	 */
	public FileUploadUtils(HttpServletRequest request, int maxSize, String tempDirName) throws Exception {
		this.request = request;
		//创建磁盘工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//设置临时文件的保存目录
		if(tempDirName != null) {
			File tempDir = new File(tempDirName);
			if(!tempDir.exists()) {
				tempDir.mkdirs();
			}
			factory.setRepository(tempDir);
		}
		//创建处理工具
		ServletFileUpload upload = new ServletFileUpload(factory);

		//设置上传文件大小限制
		if(maxSize > 0) {
			this.maxSize = maxSize;
		}
		upload.setFileSizeMax(this.maxSize);
		upload.setHeaderEncoding("utf-8");
		//接受全部内容
		this.itemList = upload.parseRequest(request);
		//进行初始化操作
		this.init();
	}

	/**
	 * 初始化参数，区分普通参数和上传文件。
	 */
	private void init() throws UnsupportedEncodingException {
		for(FileItem item : itemList) {
			//判断是否是普通的文本参数
			if(item.isFormField()) {
				//取得名称和内容
				String name = item.getFieldName();
				String value = item.getString("UTF-8").trim();
				//保存内容
				List<String> temp;
				//判断内容是否已经存放
				if(paramMap.containsKey(name)) {
					temp = paramMap.get(name);
				} else {
					temp = new ArrayList<>();
				}
				temp.add(value);
				paramMap.put(name, temp);
			} else {
				fileList.add(item);
			}
		}
	}

	/**
	 * 得到一个参数。
	 * @param name 参数名
	 */
	public String getParam(@NotNull String name) {
		String result = null;
		List<String> temp = this.paramMap.get(name);
		if(temp != null) {
			result = temp.get(0);
		}
		return result;
	}

	/**
	 * 得到一组参数。
	 * @param name 参数组名
	 */
	public String[] getParamValues(@NotNull String name) {
		String[] result = null;
		List<String> temp = this.paramMap.get(name);
		if(temp != null) {
			result = temp.toArray(new String[]{});
		}
		return result;
	}

	/**
	 * 得到参数图表。
	 */
	public Map<String, List<String>> getParamMap() {
		return paramMap;
	}

	/**
	 * 得到参数图表，只取参数组中的第一个参数。
	 */
	public Map<String, String> getParamMapByOne() {
		Map<String, String> map = new HashMap<>();
		for(var key : paramMap.keySet()) {
			map.put(key, paramMap.get(key).get(0));
		}
		return map;
	}

	/**
	 * 得到全部的上传文件。
	 */
	public List<FileItem> getFileItem() {
		return fileList;
	}


	/**
	 * 保存全部的上传文件。
	 * @param rootName 得到保存到的根文件夹，例如：/image
	 * @param dirNameGetter 得到保存到的文件夹的Lambda str->str
	 * @param fileNameGetter 得到保存为的文件名的Lambda str->str
	 * @param doDelete 是否删除对应的fileItem
	 * @return 对应的路径名列表
	 */
	public List<String> saveAll(String rootName, Function<String, String> dirNameGetter, Function<String, String> fileNameGetter, boolean doDelete) {
		List<String> pathNameList = new ArrayList<>();
		String realRootName = request.getServletContext().getRealPath(rootName);
		for(FileItem item : fileList) {
			String fileName = fileNameGetter.apply(getFileName(item.getName()));
			String dirName = realRootName + dirNameGetter.apply(fileName);
			File dir = new File(dirName);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			String pathName = dirName + "/" + fileName;
			File file = new File(dirName, fileName);
			pathNameList.add(pathName);
			try {
				IOUtils.copy(item.getInputStream(), new FileOutputStream(file));
			} catch(IOException e) {
				e.printStackTrace();
			}
			if(doDelete) {
				item.delete();
			}
		}
		return pathNameList;
	}

	/**
	 * 保存全部的上传文件。
	 * @param doDelete 是否删除对应的fileItem
	 * @return 对应的路径名列表
	 */
	public List<String> saveAll(String rootName, boolean doDelete) {
		return saveAll(rootName, f -> getRandomDirName(f), f->getRandomFileName(f),doDelete);
	}
}
