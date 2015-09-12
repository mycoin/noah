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
    
    <form method="post" action="">
        <input name="token" />
        <input name="token" /> 
        
        <input type="submit" value="submit" />
    </form>
	<table class="c-table" border="1" cellspacing="1" cellpadding="2">
		<tr>
			<td width="80">过滤器:</td>
			<td data-value="^org.ionnic">${data.filter}</td>
		</tr>
		<tr>
			<td>控制器:</td>
			<td data-value="^org.ionnic">${data.controller}</td>
		</tr>
		<tr>
			<td>拦截器:</td>
			<td data-value="^org.ionnic">${data.intercepter}
				<form method="POST" id="form" STYLE="display: inline">
					<select name="method" id="method">
						<option value="GET">GET</option>
						<option value="POST">POST</option>
						<option value="PUT">PUT</option>
						<option value="DELETE">DELETE</option>
					</select> method is "${data.method}"
				</form>
			</td>
		</tr>
		<tr id="security">
			<td>安全配置:</td>
			<td id="security-text"></td>
		</tr>
	</table>
	<script src="<%=baseDir%>/static/sample.js"></script>
	<script type="text/javascript">
		$.ajax({
			url : 'index.json',
			dataType : 'json',
			type : 'DELETE',
			success : function(data) {
				if (data.method !== "DELETE") {
					$('#method').css('background', 'red');
				}
			},
			error : function() {
				$('#method').css('background', 'red');
			}
		});

		$('#method').val('${data.method}');
		$('#method').change(function() {
			$('#form').submit();
		});

		$.ajax({
			url : 'security.json',
			username : 'admin',
			dataType : 'html',
			password : 'admin',
			type : "GET",
			success : function(data) {
				$('#security-text').html(data);
				if (data.indexOf("statusInfo") == -1) {
					$('#security').css('background', 'red');
				}
			}
		});
	</script>
</body>
</html>

