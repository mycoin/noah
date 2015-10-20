<%@ page contentType="text/html;charset=utf-8" import="java.util.regex.*,java.io.*,java.net.*,org.ionnic.config.Security"%>
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
            <li data-task="x-basic"><i>2</i><a class="link normal" href="/service/test/basic" target="_blank">basic</a></li>
            <li data-task="x-search"><i>1</i><a class="link normal" href="/service/test/search/杭州天气" target="_blank">search</a></li>
            <li data-task="x-exception"><i>3</i><a class="link normal" href="/service/test/exception" target="_blank">exception</a></li>
            <li data-task="x-api"><i>4</i><a class="link normal" href="/service/api" target="_blank">api</a></li>
            <li data-task="x-index"><i>4</i><a class="link normal" href="/service/test/index" target="_blank">index</a></li>
        </ul>
    </div>
    <script src="http://macbook-air.local/jquery.js"></script>
    <script src="http://macbook-air.local/require.js"></script>
    <script src="js/lib.js"></script>
    <script>
    var csrfToken = '<%=Security.generateToken(request)%>';
	var taskMap = {
		'x-search' : {
			dataType : 'json',
			type : 'post',
			contentType : 'application/json;charset=UTF-8',
			data : JSON.stringify({
				data: 1,
				vale: '阿里巴巴'
			})
		},

		'x-basic' : {
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			dataType: 'text',
			data : JSON.stringify({
                data: {
                    app: "百度中国",
                    time: new Date()
                },
                status: 0,
                statusInfo: "OK",
            })
		},
		'x-exception': {
			dataType : 'json'
		},

		'x-api': {
			contentType : 'application/json;charset=UTF-8',
            type : 'post',
            dataType: 'json',
            data : JSON.stringify({
            	token: csrfToken,
            	service: 'UserService',
                params: {
                    page: 1,
                    offset: 32,
                    keyword: '百度中国'
                }
            })
		},
		'x-index': {

		}
	};
</script>
<script type="text/javascript">
document.write(unescape("%3Cscript%20src%3D%27"+(location.protocol=="https:"?"https://www.ionnic.org":"http://macbook-air.local:8080")+"/service/click/log.js%3Fversion%3D0.01%26sid%3D24354214%27%3E%3C/script%3E"));
</script>
</body>
</html>