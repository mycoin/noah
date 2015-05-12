package org.ionnic.core.util;import java.lang.reflect.Field;import java.lang.reflect.Method;import java.util.ArrayList;import java.util.HashMap;import java.util.List;import java.util.Map;/** * @author apple *  */public class ReflectUtil {	/**	 * 取Bean的属性和值对应关系的MAP	 * 	 * @param bean	 * @return Map	 */	public static Map<String, Object> extract(Object bean) {		Map<String, Object> valueMap = new HashMap<String, Object>();		Class<?> clazz = bean.getClass();		Field[] fields = clazz.getDeclaredFields();		List<Method> methods = null;		for (Field field : fields) {			String name = field.getName();			Object value = null;			try {				String prefix = StringUtil.upperCaseFirst(name);				methods = ReflectUtil.getMethod(clazz, "^(get|is)" + prefix + "$", true);				loop: for (Method method : methods) {					value = method.invoke(bean);					break loop;				}			} catch (Exception e) {				e.printStackTrace();				continue;			}			valueMap.put(name, value);		}		return valueMap;	}	/**	 * @param clazz	 * @param field	 * @return	 */	private static Method getMethod(Class<?> clazz, String method) {		List<Method> methods = getMethod(clazz, method, false);		try {			return methods.get(0);		} catch (Exception e) {			return null;		}	}	/**	 * @param clazz	 * @param pattern	 * @param usingPattern	 * @return	 */	private static List<Method> getMethod(Class<?> clazz, String pattern, boolean usingPattern) {		List<Method> methods = new ArrayList<Method>();		Method[] list = clazz.getDeclaredMethods();		for (Method item : list) {			if (usingPattern) {				if (usingPattern && StringUtil.isMatchPattern(item.getName(), pattern)) {					methods.add(item);				}			} else {				if (item.getName().equals(pattern)) {					methods.add(item);				}			}		}		return methods.size() == 0 ? null : methods;	}	/**	 * 	 * @param bean	 * @param clazz	 * @param value	 * @return	 * @return	 */	private static <T> Object getValue(Class<?> clazz, Object value) {		String fieldType = clazz.getSimpleName();		if ("Date".equals(fieldType)) {			return DateUtil.parse(value + "");		} else if ("Integer".equals(fieldType) || "int".equals(fieldType)) {			return Integer.parseInt(value + "");		} else if ("Long".equalsIgnoreCase(fieldType)) {			return Long.parseLong(value + "");		} else if ("Double".equalsIgnoreCase(fieldType)) {			return Double.parseDouble(value + "");		} else if ("Boolean".equalsIgnoreCase(fieldType)) {			return Boolean.parseBoolean(value + "");		} else if ("String".equalsIgnoreCase(fieldType)) {			return value;		}		return null;	}	/**	 * set属性的值到Bean	 * 	 * @param bean	 * @param valMap	 */	public static boolean injectBean(Object bean, Map<String, Object> valueMap) {		Class<?> clazz = bean.getClass();		Field[] fields = clazz.getDeclaredFields();		boolean success = true;		for (Field field : fields) {			String name = field.getName();			Class<?> type = field.getType();			try {				String function = "set" + StringUtil.upperCaseFirst(name);				Object value = ReflectUtil.getValue(type, valueMap.get(name));				Method method = ReflectUtil.getMethod(clazz, function);				method.invoke(bean, value);			} catch (Exception e) {				e.printStackTrace();				success = false;			}		}		return success;	}}