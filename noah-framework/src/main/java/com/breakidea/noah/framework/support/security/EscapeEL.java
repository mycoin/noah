package com.breakidea.noah.framework.support.security;

/**
 * EscapeEL is a wrapper class the provides alternative names for the escaping
 * methods in com.coverity.security.Escape. These alternative names are useful
 * primarily as EL functions in JSP files.
 * <p>
 * To use these functions in EL, use mvn package and then drop
 * <code>coverity-escapers-X.X.jar</code> into <code>WEB-INF/lib</code>. Then you can use the
 * following incantation to incorporate the tag library into EL to invoke these
 * functions:
 * <pre>
 * &lt;%@ taglib uri="http://coverity.com/security" prefix="cov" %&gt;
 * 
 * &lt;!-- Example of usage within a JSP --&gt; 
 * &lt;script type="text/javascript"&gt;
 *     var x = '${cov:jsStringEscape(param.foobar)}';
 * &lt;/script&gt;
 * </pre>
 * @author Romain Gaucher
 * @author Andy Chou
 * @author Jon Passki
 * 
 */
public abstract class EscapeEL {

	/**
	 * EL wrapper for {@link Escape#html(String)}
	 */
	public static String htmlEscape(String input) {
		return Escape.html(input);
	}

	/**
	 * EL wrapper for {@link Escape#htmlText(String)}, equivalent to <code>fn:escapeXml</code>.
	 */
	public static String htmlText(String input) {
		return Escape.htmlText(input);
	}

	/**
	 * EL wrapper for {@link Escape#uriParam(String)}
	 */
	public static String uriParamEncode(String input) {
		return Escape.uriParam(input);
	}

	/**
	 * EL wrapper for {@link Escape#uri(String)}
	 */
	public static String uriEncode(String input) {
		return Escape.uri(input);
	}

	/**
	 * EL wrapper for {@link Escape#jsString(String)}
	 */
	public static String jsStringEscape(String input) {
		return Escape.jsString(input);
	}

	/**
	 * EL wrapper for {@link Escape#jsRegex(String)}
	 */
	public static String jsRegexEscape(String input) {
		return Escape.jsRegex(input);
	}

	/**
	 * EL wrapper for {@link Escape#cssString(String)}
	 */
	public static String cssStringEscape(String input) {
		return Escape.cssString(input);
	}
}
