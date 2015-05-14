package org.ionnic.test.reflect;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class Main {

	static {

		// 在封装之前 注册转换器
		ConvertUtils.register(new DateTimeConverter(), java.util.Date.class);
	}

	public static void main(String[] args) throws Exception {
		Product p = new Product();
		Map<String, Object> data = getData();

		BeanUtils.populate(p, data);
		System.out.println(p);
		data = null;
	}

	public static Map<String, Object> getData() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "a10213428");
		map.put("produectName", "雪纺衫");
		map.put("price", "100.32");
		map.put("isOnline", "true");

		return map;
	}

}

@SuppressWarnings("rawtypes")
class DateTimeConverter implements Converter {

	private static final String DATE = "yyyy-MM-dd";
	private static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
	private static final String TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";

	@Override
	public Object convert(Class type, Object value) {
		if (value == null || "".equals(value))
			return null;
		if (value instanceof String) {
			String dateValue = value.toString().trim();
			int length = dateValue.length();
			if (type.equals(java.util.Date.class)) {
				try {
					DateFormat formatter = null;
					if (length <= 10) {
						formatter = new SimpleDateFormat(DATE, new DateFormatSymbols(Locale.CHINA));
						return formatter.parse(dateValue);
					}
					if (length <= 19) {
						formatter = new SimpleDateFormat(DATETIME, new DateFormatSymbols(Locale.CHINA));
						return formatter.parse(dateValue);
					}
					if (length <= 23) {
						formatter = new SimpleDateFormat(TIMESTAMP, new DateFormatSymbols(Locale.CHINA));
						return formatter.parse(dateValue);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}
}