package com.breakidea.noah.web.support;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.util.NestedServletException;

import com.breakidea.noah.web.support.util.RequestUtils;

public class AbstractExtendedRequest extends AbstractController implements InitializingBean {

	private static final String DEFAULT_MAIN = "defaultMain";

	private static final String MODULE_NAME = "module";

	private static final String ERROR_NAME = "errorMsg";

	private Map<String, Method> methodMap = new HashMap<String, Method>();

	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpServletResponse response;

	@Autowired
	protected HttpSession session;

	@Override
	public final void afterPropertiesSet() throws Exception {
		Method[] methods = this.getClass().getMethods();
		for (Method method : methods) {
			String methodName = StringUtils.capitalize(method.getName());

			if (shouldRegistered(method)) {
				methodMap.put(methodName, method);
			}
		}
	}

	private Method getExecutor(String methodName) {
		return methodMap.get(StringUtils.capitalize(methodName));
	}

	/**
	 * 根据HttpServletRequest解析并路由出方法名
	 * 
	 * @param request
	 * @return
	 */
	private String getMethodName(HttpServletRequest request) {
		return RequestUtils.getParameter(request, MODULE_NAME, DEFAULT_MAIN);
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView result = new ModelAndView();
		ModelMap contextMap = new ModelMap();

		try {
			String methodName = getMethodName(request);
			Method executorMethod = getExecutor(methodName);

			if (executorMethod == null) {
				throw new NestedServletException("NotRegistered");
			} else if (!(Boolean) executorMethod.invoke(this, contextMap)) {
				throw new NestedServletException("Failed");
			} else {
				result.addObject("data", contextMap);
				result.addObject("modules", methodMap.keySet());
			}
		} catch (NestedServletException e) {
			result.addObject(ERROR_NAME, e.getMessage());
		} catch (Exception e) {
			result.addObject(ERROR_NAME, "Unknown");
		}

		return result;
	}

	/**
	 * 检测一个方法是否值得作为可执行方法，有三个条件：
	 * 
	 * 1.可以访问 2.返回Boolean类型的方法，其它的不支持 3.仅仅支持一个类型ModelMap作为参数
	 * 
	 * @param method
	 * @return
	 */
	private boolean shouldRegistered(Method method) {
		try {
			String returnType = method.getReturnType().getSimpleName();
			Class<?>[] parameterTypes = method.getParameterTypes();

			if ("boolean".equalsIgnoreCase(returnType) && parameterTypes.length == 1) {
				Class<?> firstParameter = parameterTypes[0];
				Class<?> targetClazz = ModelMap.class;

				return targetClazz.equals(firstParameter);
			}
		} catch (Exception e) {
			logger.error("error execute shouldRegistered: " + method, e);
		}
		return false;
	}
}
