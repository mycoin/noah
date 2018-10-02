package com.breakidea.noah.framework.support.logger;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class LoggerUtil {

	public static String getMessage(Logger logger, Object key, Object[] params) {
		ResourceBundle bundle = logger.getResourceBundle();
		String message = String.valueOf(key);

		if (bundle == null || !bundle.containsKey(message)) {
			return format(message, params);
		} else {
			return format(bundle.getString(message), params);
		}
	}

	public static String format(String message, Object[] params) {
		if (message == null || params == null) {
			return message;
		}
		return MessageFormat.format(message, params);
	}

}
