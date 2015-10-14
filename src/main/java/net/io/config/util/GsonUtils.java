package net.io.config.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * @author apple
 *
 */
public abstract class GsonUtils {

	private static Gson gson;

	/**
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> clazz) {
		if (json == null) {
			return null;
		}
		try {
			return getGson().fromJson(json, clazz);
		} catch (JsonSyntaxException e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * @param json
	 * @param typeOfT
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String json, Type typeOfT) {
		if (json == null) {
			return null;
		}
		try {
			return (T) getGson().fromJson(json, typeOfT);
		} catch (JsonSyntaxException e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * @return
	 */
	public static Gson getGson() {
		if (gson == null) {
			gson = new GsonBuilder().create();
		}
		return gson;
	}

	/**
	 * @param src
	 * @return
	 */
	public static String toJson(Object src) {
		try {
			return getGson().toJson(src);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
