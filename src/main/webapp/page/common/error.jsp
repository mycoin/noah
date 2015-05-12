<%@ page contentType="text/html;charset=utf-8" isErrorPage="true"%>
<!doctype html>
<html>
<head>
<title>500 - Internal Server Error</title>
<style>
H1,H2,H3 {
	padding: 5px;
}

H1 {
	color: white;
	background-color: #525D76;
	font-size: 22px;
}

H2 {
	color: white;
	background-color: #525D76;
	font-size: 16px;
}

H3 {
	color: white;
	background-color: #525D76;
	font-size: 12px;
	font-weight: normal;
}

TABLE TR TD {
	vertical-align: text-top;
	font-size: 12px;
	line-height: 16px;
	padding-right: 10px;
}

BODY {
	font-family: Tahoma, Arial, sans-serif;
	color: black;
	background-color: white;
}

B {
	color: white;
	background-color: #525D76;
	display: inline-block;
	padding: 2px 5px;
}

HR {
	color: #525D76;
}
</style>
</head>
<body>
	<h1>Internal Server Error</h1>
	<HR size="1" noshade="noshade">
	<table>
		<tr>
			<td align="right"><b>status</b></td>
			<td>500</td>
		</tr>
		<tr>
			<td align="right"><b>request</b></td>
			<td><%=request.getRequestURI()%></td>
		</tr>
		<tr>
			<td align="right"><b>message</b></td>
			<td><%=exception%></td>
		</tr>
		<tr>
			<td align="right"><b>trace</b></td>
			<td>
				<%
					for (StackTraceElement item : exception.getStackTrace()) {
						out.append("at " + item);
						out.append("<br>");
					}
				%>
			</td>
		</tr>
	</table>
	<HR size="1" noshade="noshade">
	<h3>
		Powered by
		<%=application.getServerInfo()%></h3>
</body>
</html>