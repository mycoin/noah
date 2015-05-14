package org.ionnic.app.home.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ionnic.core.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Home {

	Logger log = LoggerFactory.getLogger(Home.class);

	@RequestMapping("/home/about")
	public ModelAndView about() {
		ModelAndView view = new ModelAndView("home/a");
		view.addObject("about", "about");
		return view;
	}

	@RequestMapping("/home")
	public ModelAndView index(HttpServletRequest req, WebRequest request) {

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", 0);
		data.put("name", "ronghan.lrh");
		data.put("website", "http://home.ionnic.org/");
		data.put("age", 25);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 0);
		map.put("statusInfo", "OK");
		map.put("data", data);

		log.debug("23456789098765432");
		System.out.println(request);
		// System.out.println(req.getAttribute("Param"));

		return new ModelAndView("home/index", map);
	}

	@RequestMapping("/home/install")
	public ModelAndView install() {
		return new ModelAndView("/home/install");
	}

	public static <T> T requestToObject(HttpServletRequest request, Class<T> clazz) {

		if (null == request) {
			return null;
		}

		Field[] fields = clazz.getDeclaredFields(); // 取到所有类下的属性，也就是变量名
		Field field;
		T o = null;
		try {
			o = clazz.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		for (int i = 0; i < fields.length; i++) {
			field = fields[i];
			String fieldName = field.getName();
			// 把属性的第一个字母处理成大写
			String stringLetter = fieldName.substring(0, 1).toUpperCase();
			// 取得set方法名，比如setBbzt
			String setName = "set" + stringLetter + fieldName.substring(1);
			// 真正取得get方法。
			Method setMethod = null;
			Class<?> fieldClass = field.getType();
			try {
				Object value = request.getParameter(fieldName);
				Object valueArray = request.getParameterValues(fieldName);
				if (value != null && isHaveSuchMethod(clazz, setName)) {
					if (String.valueOf(value).trim().length() > 0) {
						if (fieldClass == String.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(o, String.valueOf(value));// 为其赋值
						} else if (fieldClass == Integer.class || fieldClass == int.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(o, Integer.parseInt(String.valueOf(value)));// 为其赋值
						} else if (fieldClass == Boolean.class || fieldClass == boolean.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(o, Boolean.getBoolean(String.valueOf(value)));// 为其赋值
						} else if (fieldClass == Short.class || fieldClass == short.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(o, Short.parseShort(String.valueOf(value)));// 为其赋值
						} else if (fieldClass == Long.class || fieldClass == long.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(o, Long.parseLong(String.valueOf(value)));// 为其赋值
						} else if (fieldClass == Double.class || fieldClass == double.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(o, Double.parseDouble(String.valueOf(value)));// 为其赋值
						} else if (fieldClass == Float.class || fieldClass == float.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(o, Float.parseFloat(String.valueOf(value)));// 为其赋值
						} else if (fieldClass == BigInteger.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(o, BigInteger.valueOf(Long.parseLong(String.valueOf(value))));// 为其赋值
						} else if (fieldClass == BigDecimal.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(o, BigDecimal.valueOf(Double.parseDouble(String.valueOf(value))));// 为其赋值
						} else if (fieldClass == Date.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							Date tempDate = null;
							tempDate = DateUtil.parse(value.toString());
							if (null != tempDate) {
								setMethod.invoke(o, tempDate);
							}
						}
					} else {
						Object oo = null;
						setMethod = clazz.getMethod(setName, fieldClass);
						setMethod.invoke(o, oo);
					}

				}
				if (valueArray != null && isHaveSuchMethod(clazz, setName)) {
					if (fieldClass == String[].class) {
						setMethod = clazz.getMethod(setName, fieldClass);
						setMethod.invoke(o, value == null ? null : valueArray);
					}
				}
			} catch (Exception e) {
			}

		}
		return o;
	}

	public static boolean isHaveSuchMethod(Class<?> clazz, String methodName) {
		Method[] methodArray = clazz.getMethods();
		boolean result = false;
		if (null != methodArray) {
			for (int i = 0; i < methodArray.length; i++) {
				if (methodArray[i].getName().equals(methodName)) {
					result = true;
					break;
				}
			}
		}
		return result;
	}
}