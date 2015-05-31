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
			<td colspan="2">
			<pre><code class="java">@RequestMapping("/rest")
public void rest(Model model) {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put("id", 83961);
    ...
    model.addAttribute("status", 0);
    model.addAttribute("statusInfo", "OK");
    model.addAttribute("data", data);
}
</code></pre></td>
		</tr>
		<tr class="error" id="format">
			<td width="80">favorParam:</td>
			<td><a href="rest.json">json</a>, <a href="rest.xml">xml</a>, <a href="?format=json">json</a>, <a href="?format=xml">xml</a></td>
		</tr>
	</table>
	<script src="<%=baseDir%>/static/sample.js"></script>
	<script>
	jQuery.ajax({
		url: "rest",
		data: {
			format: "json"
		},
		dataType: "text",
		success: function(text){
			jQuery.ajax({
		        url: "rest.json",
		        dataType: "text",
		        success: function(inner){
		            if(inner == text && text.indexOf("home.ionnic.org") > -1) {
		            	jQuery.ajax({
		                    url: "rest.xml",
		                    dataType: "text",
		                    success: function(text){
		                        if(text.indexOf("</entry>") > -1) {
		                        	jQuery("#format").removeClass("error");
		                        }
		                    	
		                    }
		                });
		            }
		        }
		    });
		}
	});
	
	1||jQuery.get("rest", {format: "json"}, function(data){
		 if(data.data.id == 83961) {
			 jQuery.get("rest.xml", function(data){
				  if(jQuery(data).children().get(0).tagName == "map") {
					  jQuery("#format").removeClass("error");
				  }
			 });
		 }
	});
	</script>
</body>
</html>

