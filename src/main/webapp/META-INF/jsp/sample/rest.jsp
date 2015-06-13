<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String baseDir = request.getContextPath();
%>
<!doctype html>
<html>
<head>
<title>sample/rest</title>
<link rel="stylesheet" href="<%=baseDir%>/static/sample.css">
</head>
<body>
	<table class="c-table" border="1" cellspacing="1" cellpadding="2">
		<tr>
			<td colspan="2"><pre><code class="java">@RequestMapping("/rest")
public void rest(Model model) {
    Map<String , Object> data = new HashMap<String , Object>();
    data.put("id", 83961);
    ...
    model.addAttribute("status", 0);
    model.addAttribute("statusInfo", "OK");
    model.addAttribute("data", data);
}</code></pre></td>
		</tr>
		<tr id="format">
			<td width="80">favorParam:</td>
			<td><a href="rest.json">rest.json</a>, <a href="rest.xml">rest.xml</a>, <a href="?format=json">json</a>, <a href="?format=xml">xml</a></td>
		</tr>
	</table>
	<script src="<%=baseDir%>/static/sample.js"></script>
	<script>
    function notify() {
    	jQuery("#format").addClass("error");
    }
	jQuery.ajax({
		url : "rest",
		data : {
			format : "json"
		},
		dataType : "text",
		success : function(text) {
			jQuery.ajax({
				url : "rest.json",
				dataType : "text",
				success : function(inner) {
					if (inner == text && text.indexOf("home.ionnic.org") > -1) {
						jQuery.ajax({
							url : "rest.xml",
							dataType : "text",
							success : function(text) {
								if (text.indexOf("</entry>") == -1) {
									notify();
								}
							},
							error: function(){
								notify();
							}
						});
					} else {
						notify();
					}
				},
				error: function(){
					notify();
				}
			});
		},
		
		error: function(){
			notify();
		}
	});

		jQuery('[href]').mousedown(function(e) {
			jQuery.ajax({
				url : this.href,
				dataType : "html",
				success : function(data) {
					alert(data);
				}
			});
		});
	</script>
</body>
</html>

