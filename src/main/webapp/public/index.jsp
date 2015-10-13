<%@ page contentType="text/html;charset=utf-8" import="java.util.regex.*,java.io.*,java.net.*,org.ionnic.config.support.TokenRepository"%>
<!doctype html>
<html>
<head>
<title>test web</title>
<link rel="stylesheet" type="text/css" href="css/lib.css" />
</head>
<body>
    <h2>测试用例</h2>
    <div class="col">
        <ul>
            <li data-task="x-search"><i>1</i><a class="link normal" href="/service/test/search/杭州天气" target="_blank">search</a></li>
            <li data-task="x-basic"><i>3</i><a class="link normal" href="/service/test/basic" target="_blank">basic</a></li>
            <li data-task="x-exception"><i>5</i><a class="link normal" href="/service/test/exception" target="_blank">exception</a></li>
        </ul>
    </div>
    <script src="//macbook-air.local/jQuery.js"></script>
    <script src="js/lib.js"></script>
    <script>
    var csrfToken = '<%=TokenRepository.generateToken(request)%>';
	var taskMap = {
		'x-search' : {
			dataType : 'json',
			type : 'post',
			contentType : 'javascript/json'
		},

		'x-header' : {
			type : 'post'
		},

		'x-basic' : {
			contentType : 'javascript/json',
			type : 'post',
			data : {
				token : 2
			}
		},
		'x-security' : {
			username : 'admin',
			password : 'admin'
		},
		'x-exception': {
			dataType : 'json'
		}
	};
</script>
</body>
</html>