<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String baseDir = request.getContextPath();
%>
<!doctype html>
<html>
<head>
<title>sample/param</title>
<link rel="stylesheet" href="<%=baseDir%>/static/sample.css">
</head>
<body>
	<table class="c-table" border="1" cellspacing="1" cellpadding="2">
		<tr>
			<td colspan="2"><pre class="java">@RequestMapping("/param")
public void param(String name, Model model) {
    model.addAttribute("data", name);
}
</pre></td>
		</tr>
		<tr>
			<td width="80">baseDir:</td>
			<td data-value="/spring-mvc"><%=baseDir%></td>
		</tr>
		<tr>
			<td>[name]:</td>
			<td data-value="2345">${data}</td>
		</tr>
	</table>
	<script src="<%=baseDir%>/static/sample.js"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>

