package org.ionnic.core.support.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.tools.generic.RenderTool;

public class pageTool extends RenderTool {
	private Map<String, List<Object>> cacheMap = new HashMap<String, List<Object>>();

	/**
	 * @param type
	 * @param value
	 */
	private void add(String type, Object value) {
		List<Object> list = cacheMap.get(type);
		if (list == null) {
			list = new ArrayList<Object>();
			cacheMap.put(type, list);
		}
		list.add(value);
	}

	public void addScript(String filename) {
		add("SCRIPT", filename);
	}

	public void addScript(String[] filename) {
		for (String item : filename) {
			addScript(item);
		}
	}

	public void addStyleSheet(String filename) {
		add("CSS", filename);
	}

	public String getValue(String keyName) {
		return "";
	}

	public void load(String path) {
	}

	public String loadTemplate(String url) {
		return "";
	}

	public void setValue() {

	}

	public String showScript() {
		String output = "";
		List<Object> scriptList = cacheMap.get("SCRIPT");
		if (scriptList == null) {
			return output;
		} else {
			for (Object item : scriptList) {
				output += "<script type=\"text/javascript\" src=\"" + item + "\"></script>";
			}
		}
		return output;
	}

	public String showStylesheet() {
		String output = "";
		List<Object> styleList = cacheMap.get("CSS");
		if (styleList == null) {
			return output;
		} else {
			for (Object item : styleList) {
				output += "<link rel=\"stylesheet\" type=\"text/css\" href=\"" + item + "\"></script>";
			}
		}
		return output;
	}
}
