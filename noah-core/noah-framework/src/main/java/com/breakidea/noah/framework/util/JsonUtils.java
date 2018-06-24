package com.breakidea.noah.framework.util;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.google.gson.stream.MalformedJsonException;

public abstract class JsonUtils {

	public static final String DEFAULT_JSON = "{}";

	public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	private static Gson gson = create();

	/**
	 * @return
	 */
	public static Gson create() {
		GsonBuilder gb = new GsonBuilder();

		gb.disableHtmlEscaping();
		gb.serializeNulls();
		gb.setDateFormat(DATE_FORMAT);
		gb.registerTypeAdapter(Date.class, new DateTypeAdapter());

		return gb.create();
	}

	/**
	 * @param src
	 * @param type
	 * @return
	 */
	public static Object parse(Object src, Type type) {
		try {
			return gson.toJson(src, type);
		} catch (Throwable e) {
			throw new UnsupportedOperationException(e);
		}
	}

	/**
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T parse(Reader reader, Class<T> clazz) {
		if (reader == null) {
			return null;
		}
		return gson.fromJson(reader, clazz);
	}

	/**
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> parse(String json) {
		return parse(json, Map.class);
	}

	/**
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T parse(String json, Class<T> clazz) {
		if (json == null) {
			return null;
		}
		return gson.fromJson(json, clazz);
	}

	/**
	 * @param src
	 * @return
	 * @throws MalformedJsonException
	 */
	public static String stringify(Object src) throws UnsupportedOperationException {
		if (src == null) {
			return DEFAULT_JSON;
		}
		return stringify(src, src.getClass());
	}

	/**
	 * @param src
	 * @param writer
	 * @return
	 * @throws JsonIOException
	 */
	public static void stringify(Object src, Appendable writer) throws JsonIOException {
		gson.toJson(src, writer);
	}

	/**
	 * @param src
	 * @param typeOfSrc
	 * @return
	 * @throws UnsupportedOperationException
	 */
	public static String stringify(Object src, Type type) throws UnsupportedOperationException {
		try {
			return gson.toJson(src, type);
		} catch (Throwable e) {
			throw new UnsupportedOperationException(e);
		}
	}
}
