import java.util.Map;

public interface View {

	String VER = "1";

	String render(String tplName, Map<String, Object> dataMap);

	public abstract class GlobalContext {
		String toJSON(Object data) {
			return data.toString();
		}
	}

	public interface Printable {
		void doPrint(View view, String tplName, Map<String, Object> dataMap);
	}

	public abstract class AbsView extends GlobalContext implements View {

		@Override
		public final String render(String tplName, Map<String, Object> dataMap) {
			dataMap.put("domainName", "g.alicdn.com");
			String result = renderInternal(tplName, dataMap);
			return result;
		}

		public abstract String renderInternal(String tplName, Map<String, Object> dataMap);
	}

	public class MyView extends AbsView implements Printable {

		@Override
		public String renderInternal(String tplName, Map<String, Object> dataMap) {
			return tplName + toJSON(dataMap);
		}

		@Override
		public void doPrint(View view, String tplName, Map<String, Object> dataMap) {
			System.err.println(view.render(tplName, dataMap));
		}

	}
}
