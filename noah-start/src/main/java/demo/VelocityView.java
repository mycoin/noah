package demo;

import java.util.LinkedHashMap;
import java.util.Map;

public class VelocityView extends AbstractView implements Printable {

	protected String renderInternal(String string, Map<String, Object> dataMap) {
		return string + "::" + toJSON(dataMap);
	}

	@Override
	public void print(String viewName, Map<String, Object> dataMap) {
		System.out.println(this.render(viewName, dataMap));
	}

	public static void main(String[] args) {
		VelocityView vv = new VelocityView();
		Map<String, Object> dataMap = new LinkedHashMap<String, Object>();

		dataMap.put("name", "Nane");
		dataMap.put("ok", true);

		vv.print("XX", dataMap);
		String html = vv.render("XX", dataMap);
		
		System.out.println(html);
	}
}
