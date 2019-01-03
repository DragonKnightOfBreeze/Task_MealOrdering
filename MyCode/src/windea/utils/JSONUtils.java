/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */


package windea.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * JSON的工具类<br>
 * 使用官方的json插件。
 * @noinspection unused, WeakerAccess
 */
public class JSONUtils {

	/**
	 * 快速生成JSON对象。
	 */
	public static JSONObject of(@NotNull String k1, Object v1) {
		return new JSONObject().put(k1, v1);
	}

	/**
	 * 快速生成JSON对象。
	 */
	public static JSONObject of(@NotNull String k1, Object v1, @NotNull String k2, Object v2) {
		return new JSONObject().put(k1, v1).put(k2, v2);
	}

	/**
	 * 快速生成JSON对象。
	 */
	public static JSONObject of(@NotNull String k1, Object v1, @NotNull String k2, Object v2, @NotNull String k3,
			Object v3) {
		return new JSONObject().put(k1, v1).put(k2, v2).put(k3, v3);
	}

	/**
	 * 快速生成JSON对象。
	 */
	public static JSONObject of(@NotNull String k1, Object v1, @NotNull String k2, Object v2, @NotNull String k3,
			Object v3, @NotNull String k4,
			Object v4) {
		return new JSONObject().put(k1, v1).put(k2, v2).put(k3, v3).put(k4, v4);
	}


	/**
	 * 快速生成JSON数组。
	 */
	public static JSONArray of2(Object v1) {
		return new JSONArray().put(v1);
	}

	/**
	 * 快速生成JSON数组。
	 */
	public static JSONArray of2(Object v1, @NotNull Object... other) {
		JSONArray json = new JSONArray().put(v1);
		Arrays.stream(other).forEach(json::put);
		return json;
	}


	/**
	 * 根据指定的路径，读取JSON文件，得到JSON对象。<br>
	 * 路径可以是绝对地址，也可以相对于项目地址并不以/或\\开头。
	 */
	@NotNull
	@Contract("_ -> new")
	public static JSONObject from(@NotNull String filePath) throws IOException {
		if(!filePath.endsWith(".json"))
			throw new IllegalArgumentException("*" + filePath + "* is not a json file.");

		return new JSONObject(new JSONTokener(new FileReader("filePath")));
	}

	/**
	 * 根据指定的路径，读取JSON文件，得到JSON数组。<br>
	 * 路径可以是绝对地址，也可以相对于项目地址并不以/或\\开头。
	 */
	@NotNull
	@Contract("_ -> new")
	public static JSONArray from2(@NotNull String filePath) throws IOException {
		if(!filePath.endsWith(".json"))
			throw new IllegalArgumentException("*" + filePath + "* is not a json file.");

		return new JSONArray(new JSONTokener(new FileReader("filePath")));
	}


	/**
	 * 根据指定的JSON对象和路径，写入JSON文件。<br>
	 * 路径可以是绝对地址，也可以相对于项目地址并不以/或\\开头。
	 */
	public static void to(@NotNull JSONObject json, @NotNull String filePath) throws IOException {
		to(json, filePath, 4);
	}

	/**
	 * 根据指定的JSON对象和路径，写入JSON文件。<br>
	 * 路径可以是绝对地址，也可以相对于项目地址并不以/或\\开头。
	 */
	public static void to(@NotNull JSONObject json, @NotNull String filePath, int indent) throws IOException {
		if(!filePath.endsWith(".json"))
			throw new IllegalArgumentException("*" + filePath + "* is not a json fileName.");

		FileWriter writer = new FileWriter(filePath);
		writer.write(json.toString(indent));
		writer.close();
	}

	/**
	 * 根据指定的JSON对象和路径，写入JSON文件。<br>
	 * 路径可以是绝对地址，也可以相对于项目地址并不以/或\\开头。
	 */
	public static void to2(@NotNull JSONArray json, @NotNull String filePath) throws IOException {
		to2(json, filePath, 4);
	}

	/**
	 * 根据指定的JSON对象和路径，写入JSON文件。<br>
	 * 路径可以是绝对地址，也可以相对于项目地址并不以/或\\开头。
	 */
	public static void to2(@NotNull JSONArray json, @NotNull String filePath, int indent) throws IOException {
		if(!filePath.endsWith(".json"))
			throw new IllegalArgumentException("*" + filePath + "* is not a json fileName.");

		FileWriter writer = new FileWriter(filePath);
		writer.write(json.toString(indent));
		writer.close();
	}
}
