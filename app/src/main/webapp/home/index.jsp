<%@ page contentType="text/html;charset=utf-8" import="java.io.*,java.net.*"%>
<!doctype html>
<html>
<head>
<title>home web</title>
<link rel="stylesheet" type="text/css" href="css/main.css" />
</head>
<%
String baseUrl = request.getContextPath() + "/";
%>
<body>
    <script>
    var baseUrl = "<%=baseUrl%>";
    </script>
</body>
</html>
