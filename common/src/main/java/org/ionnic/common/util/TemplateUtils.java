package org.ionnic.common.util;

import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.node.Node;
import org.springframework.util.StringUtils;

/**
 * @author apple
 *
 */
public abstract class TemplateUtils {

	private static final Pattern SPACE_HOLDER_PATTERN = Pattern.compile("\\{[^{}]*\\}");

	private static final Object DEFAULT_LAYOUT = "layout/blank.vm";

	/**
	 * @param pattern
	 * @param parameterMap
	 * @return
	 */
	public static String formatString(String pattern, Map<String, Object> parameterMap) {

		if (parameterMap == null) {
			parameterMap = Collections.emptyMap();
		}

		Matcher matcher = SPACE_HOLDER_PATTERN.matcher(pattern);
		if (!matcher.find()) {
			return pattern;
		}
		int start = 0;
		StringBuilder buffer = new StringBuilder();
		do {
			buffer.append(pattern, start, matcher.start());
			String group = matcher.group();
			String key = group.substring(1, group.length() - 1);
			Object value = parameterMap.get(key);
			if (value != null) {
				buffer.append(value);
			}
			start = matcher.end();
		} while (matcher.find());

		buffer.append(pattern, start, pattern.length());
		return buffer.toString();
	}

	/**
	 * @param node
	 * @return
	 */
	public static String getFirstArg(Node node) {
		String[] args = getArgArray(node);
		if (args.length > 0) {
			return args[0];
		} else {
			return null;
		}
	}

	/**
	 * Creates an array containing the literal text from the macro
	 * arguments(s) (including the macro's name as the first arg).
	 *
	 * @param node The parse node from which to grow the argument
	 * list.  It's expected to include the block node tree (for the
	 * macro body).
	 * @return array of arguments
	 */
	public static String[] getArgArray(Node node) {
		/*
		 * Get the number of arguments for the macro, excluding the
		 * last child node which is the block tree containing the
		 * macro body.
		 */
		int numArgs = node.jjtGetNumChildren();

		String argArray[] = new String[numArgs];

		// avoid the block tree...
		numArgs--;

		int i = 0;

		String string;

		while (i <= numArgs) {
			string = node.jjtGetChild(i).getFirstToken().image;
			if (i >= 0) {
				if (string.startsWith("$")) {
					string = string.substring(1, string.length());
				} else {
					string = string.substring(1, string.length() - 1);
				}
			}

			argArray[i] = string.intern();
			i++;
		}
		return argArray;
	}

	/**
	 * @param context
	 * @return
	 */
	public static boolean isRenderingLayout(InternalContextAdapter context) {
		String layout = (String) context.get("layout");
		if (StringUtils.hasLength(layout)) {
			return true;
		} else if (layout == null && context.getCurrentTemplateName().equals(DEFAULT_LAYOUT)) {
			return true;
		}
		return false;
	}

}
