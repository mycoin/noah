<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String baseDir = request.getContextPath();
%>
<!doctype html>
<html>
<head>
<title>sample/index</title>
<link rel="stylesheet" href="<%=baseDir%>/static/sample.css">
</head>
<body>
	<table class="c-table" border="1" cellspacing="1" cellpadding="2">
		<tr>
			<td colspan="2"><pre class="java">@RequestMapping("/sample/index")
public void index(Model model) {
    model.addAttribute("data", this);
}</pre></td>
		</tr>
		<tr>
			<td width="80">baseDir:</td>
			<td data-value="^/"><%=baseDir%></td>
		</tr>
		<tr>
			<td>控制器:</td>
			<td data-value="^org.ionnic">${data}</td>
		</tr>
		<tr>
			<td>拦截器:</td>
			<td data-value="^[a-z0-9-]{36}$"></td>
		</tr>
	</table>
	<script src="<%=baseDir%>/static/sample.js"></script>
</body>
</html>

