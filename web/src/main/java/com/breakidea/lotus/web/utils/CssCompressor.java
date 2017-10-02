package com.breakidea.lotus.web.utils;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CssCompressor {

	private StringBuffer srcsb = new StringBuffer();

	public CssCompressor(Reader in) throws IOException {
		// Read the stream...
		int c;
		while ((c = in.read()) != -1) {
			srcsb.append((char) c);
		}
	}

	protected String mergeRules(String inputCss) {

		Map<String, Object> ruleMap = new HashMap<String, Object>();
		StringBuffer mergedCss = new StringBuffer();

		Pattern p = Pattern.compile("([^\\{]*)\\{(.*?)\\}");
		Matcher m = p.matcher(inputCss);

		while (m.find()) {
			String selectors = m.group(1);
			String rules = m.group(2);

			rules = compressDimensions(rules);

			if (ruleMap.containsKey(rules)) {
				ruleMap.put(rules, ruleMap.get(rules) + "," + selectors);
			} else {
				ruleMap.put(rules, selectors);
			}
		}

		String rule;
		for (Iterator<String> i = ruleMap.keySet().iterator(); i.hasNext();) {
			rule = (String) i.next();
			mergedCss.append(ruleMap.get(rule) + "{" + rule + "}");
		}

		return mergedCss.toString();
	}

	protected String removeDuplicateProperties(String inputCssRule) {

		StringBuffer cssRule = new StringBuffer();

		Set<String> ruleSet = new HashSet<String>(Arrays.asList(inputCssRule.split(";")));

		for (Iterator<String> i = ruleSet.iterator(); i.hasNext();) {
			cssRule.append((String) i.next() + ";");
		}

		return cssRule.toString();
	}

	protected String compressDimensions(String inputCssRule) {
		Pattern p = Pattern.compile("(border|margin):(\\d+(?:\\p{Alpha}*))(\\2){3}");
		Matcher m;

		StringBuffer cssRule = new StringBuffer();

		for (String rule : inputCssRule.split(";")) {

			String condensedRule = rule.replaceAll(" +", "");
			m = p.matcher(condensedRule);
			if (m.find()) {
				cssRule.append(condensedRule.substring(0, m.start()));
				cssRule.append(m.group(1) + ':' + m.group(2) + ';');
			} else {
				cssRule.append(rule + ';');
			}
		}

		return cssRule.toString();
	}

	public void compress(Writer out, int linebreakpos) throws IOException {

		Pattern p;
		Matcher m;
		String css;
		StringBuffer sb;
		int startIndex, endIndex;

		// Remove all comment blocks...
		startIndex = 0;
		boolean iemac = false;
		boolean preserve = false;
		sb = new StringBuffer(srcsb.toString());
		while ((startIndex = sb.indexOf("/*", startIndex)) >= 0) {
			preserve = sb.length() > startIndex + 2 && sb.charAt(startIndex + 2) == '!';
			endIndex = sb.indexOf("*/", startIndex + 2);
			if (endIndex < 0) {
				if (!preserve) {
					sb.delete(startIndex, sb.length());
				}
			} else if (endIndex >= startIndex + 2) {
				if (sb.charAt(endIndex - 1) == '\\') {
					// Looks like a comment to hide rules from IE Mac.
					// Leave this comment, and the following one, alone...
					startIndex = endIndex + 2;
					iemac = true;
				} else if (iemac) {
					startIndex = endIndex + 2;
					iemac = false;
				} else if (!preserve) {
					sb.delete(startIndex, endIndex + 2);
				} else {
					startIndex = endIndex + 2;
				}
			}
		}

		css = sb.toString();

		// Normalize all whitespace strings to single spaces. Easier to work
		// with that way.
		css = css.replaceAll("\\s+", " ");

		// Make a pseudo class for the Box Model Hack
		css = css.replaceAll("\"\\\\\"}\\\\\"\"", "___PSEUDOCLASSBMH___");

		// Remove the spaces before the things that should not have spaces
		// before them.
		// But, be careful not to turn "p :link {...}" into "p:link{...}"
		// Swap out any pseudo-class colons with the token, and then swap back.
		sb = new StringBuffer();
		p = Pattern.compile("(^|\\})(([^\\{:])+:)+([^\\{]*\\{)");
		m = p.matcher(css);
		while (m.find()) {
			String s = m.group();
			s = s.replaceAll(":", "___PSEUDOCLASSCOLON___");
			m.appendReplacement(sb, s);
		}
		m.appendTail(sb);
		css = sb.toString();
		css = css.replaceAll("\\s+([!{};:>+\\(\\)\\],])", "$1");
		css = css.replaceAll("___PSEUDOCLASSCOLON___", ":");

		// Remove the spaces after the things that should not have spaces after
		// them.
		css = css.replaceAll("([!{}:;>+\\(\\[,])\\s+", "$1");

		// Add the semicolon where it's missing.
		css = css.replaceAll("([^;\\}])}", "$1;}");

		// Replace 0(px,em,%) with 0.
		css = css.replaceAll("([\\s:])(0)(px|em|%|in|cm|mm|pc|pt|ex)", "$1$2");

		// Replace 0 0 0 0; with 0.
		css = css.replaceAll(":0 0 0 0;", ":0;");
		css = css.replaceAll(":0 0 0;", ":0;");
		css = css.replaceAll(":0 0;", ":0;");
		// Replace background-position:0; with background-position:0 0;
		css = css.replaceAll("background-position:0;", "background-position:0 0;");

		// Replace 0.6 to .6, but only when preceded by : or a white-space
		css = css.replaceAll("(:|\\s)0+\\.(\\d+)", "$1.$2");

		// Shorten colors from rgb(51,102,153) to #336699
		// This makes it more likely that it'll get further compressed in the
		// next step.
		p = Pattern.compile("rgb\\s*\\(\\s*([0-9,\\s]+)\\s*\\)");
		m = p.matcher(css);
		sb = new StringBuffer();
		while (m.find()) {
			String[] rgbcolors = m.group(1).split(",");
			StringBuffer hexcolor = new StringBuffer("#");
			for (int i = 0; i < rgbcolors.length; i++) {
				int val = Integer.parseInt(rgbcolors[i]);
				if (val < 16) {
					hexcolor.append("0");
				}
				hexcolor.append(Integer.toHexString(val));
			}
			m.appendReplacement(sb, hexcolor.toString());
		}
		m.appendTail(sb);
		css = sb.toString();

		// Shorten colors from #AABBCC to #ABC. Note that we want to make sure
		// the color is not preceded by either ", " or =. Indeed, the property
		// filter: chroma(color="#FFFFFF");
		// would become
		// filter: chroma(color="#FFF");
		// which makes the filter break in IE.
		p = Pattern.compile(
				"([^\"'=\\s])(\\s*)#([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])");
		m = p.matcher(css);
		sb = new StringBuffer();

		Map<String, String> colorMap = new HashMap<String, String>();
		colorMap.put("C0C0C0", "silver");
		colorMap.put("800000", "maroon");
		colorMap.put("800080", "purple");
		colorMap.put("008000", "green");
		colorMap.put("808000", "olive");
		colorMap.put("000080", "navy");
		colorMap.put("008080", "teal");

		while (m.find()) {
			// Test for AABBCC pattern
			if (m.group(3).equalsIgnoreCase(m.group(4)) && m.group(5).equalsIgnoreCase(m.group(6))
					&& m.group(7).equalsIgnoreCase(m.group(8))) {
				m.appendReplacement(sb, m.group(1) + m.group(2) + "#" + m.group(3) + m.group(5) + m.group(7));
			} else {
				// Test if hex code can be smaller as a named color
				String hex = m.group(3) + m.group(4) + m.group(5) + m.group(6) + m.group(7) + m.group(8);
				if (colorMap.containsKey(hex)) {
					m.appendReplacement(sb, m.group(1) + m.group(2) + "#" + colorMap.get(hex));
				} else {
					m.appendReplacement(sb, m.group());
				}
			}
		}
		m.appendTail(sb);
		css = sb.toString();

		// Replace named colors where they are shorter hex values
		css.replaceAll(":black", ":#000");
		css.replaceAll(":white", ":#FFF");
		css.replaceAll(":yellow", ":#FF0");

		// Remove empty rules.
		css = css.replaceAll("[^\\}]+\\{;\\}", "");

		if (linebreakpos >= 0) {
			// Some source control tools don't like it when files containing
			// lines longer
			// than, say 8000 characters, are checked in. The linebreak option
			// is used in
			// that case to split long lines after a specific column.
			int i = 0;
			int linestartpos = 0;
			sb = new StringBuffer(css);
			while (i < sb.length()) {
				char c = sb.charAt(i++);
				if (c == '}' && i - linestartpos > linebreakpos) {
					sb.insert(i, '\n');
					linestartpos = i;
				}
			}

			css = sb.toString();
		}

		// Replace the pseudo class for the Box Model Hack
		css = css.replaceAll("___PSEUDOCLASSBMH___", "\"\\\\\"}\\\\\"\"");

		// Replace multiple semi-colons in a row by a single one
		// See SF bug #1980989
		css = css.replaceAll(";;+", ";");

		// Trim the final string (for any leading or trailing white spaces)
		css = css.trim();

		// Merge the classes
		css = mergeRules(css);

		// Remove the last semi-colon in blocks
		css = css.replaceAll(";\\}", "}");

		// Write the output...
		out.write(css);
	}
}