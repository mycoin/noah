<%@ page contentType="text/html;charset=utf-8" import="java.util.regex.*,java.io.*,java.net.*"%>
<%@ page pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String sCurrentLine = "";
	String sTotalString = "";
	URL url = new URL("http://www.baidu.com/duty/right.html");
	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	connection.connect();
	InputStream urlStream = connection.getInputStream();
	BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(urlStream, "utf-8"));
	while ((sCurrentLine = reader.readLine()) != null) {
		sTotalString += sCurrentLine;
	}
	out.append(sTotalString);
%>