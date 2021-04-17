package demo;

import java.util.Map;

public interface Viewable {
	String render(String viewName, Map<String, Object> object);
}
