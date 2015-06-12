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
			<td width="80">过滤器:</td>
			<td data-value="^org.ionnic"><%=request.getAttribute("filter")%></td>
		</tr>
		<tr>
			<td>控制器:</td>
			<td data-value="^org.ionnic">${data}</td>
		</tr>
		<tr>
			<td>拦截器:</td>
			<td data-value="^org.ionnic">
			    <%=request.getAttribute("intercepter")%>
                <form method="POST" STYLE="display:inline">
                    <select name="method">
                        <option value="DELETE">DELETE</option>
                        <option value="GET">GET</option>
                        <option value="PUT">PUT</option>
                        <option value="POST">POST</option>
                    </select>
                    <input type="submit" value="提交"/>
                    <%=request.getAttribute("hidden")%>
                </form>
			</td>
		</tr>
	</table>
	<script src="<%=baseDir%>/static/sample.js"></script>
</body>
</html>

