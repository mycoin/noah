package demo;

import java.util.Map;

public abstract class AbstractView extends ApplicationSupport implements Viewable {

	@Override
	public String render(String viewName, Map<String, Object> dataMap) {
		dataMap.put("className", AbstractView.class.getName());
		dataMap.put("domainName", "g.alicdn.com");

		return renderInternal(viewName + ".vm", dataMap);
	}

	protected abstract String renderInternal(String string, Map<String, Object> dataMap);
}
