<%@ page contentType="text/html;charset=utf-8" isErrorPage="true"%>
<html>
<head>
<title>404 - Page not found</title>
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
	font-size: 14px;
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
	<h1>Page Not Found</h1>
	<HR size="1" noshade="noshade">
	<table>
		<tr>
			<td align="right"><b>status</b></td>
			<td>404</td>
		</tr>
		<tr>
			<td align="right"><b>servlet</b></td>
			<td><%=request.getAttribute("javax.servlet.error.servlet_name")%></td>
		</tr>
		<tr>
			<td align="right"><b>URL</b></td>
			<td><%=request.getAttribute("javax.servlet.forward.request_uri")%></td>
		</tr>
	</table>
	<HR size="1" noshade="noshade">
	<h3>home.ionnic.org</h3>
</body>
</html>