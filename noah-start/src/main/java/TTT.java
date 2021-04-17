import java.util.LinkedHashMap;
import java.util.Map;

public class TTT {
	public static void main(String[] args) {
		View.Printable view = new View.MyView();
		
		Map<String, Object> dataMap = new LinkedHashMap<>();
		
		dataMap.put("id", 1);
		dataMap.put("name", "111Name");
		
		view.doPrint((View)view, "ss", dataMap);
	}
}


