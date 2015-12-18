<%@ page contentType="text/html;charset=utf-8" import="java.util.regex.*,java.io.*,java.net.*,org.ionnic.common.Security"%>
<!doctype html>
<html>
<head>
<title>test web</title>
<link rel="stylesheet" type="text/css" href="css/lib.css" />
</head>
<%
String baseUrl = request.getContextPath();
%>
<body>
    <h2>测试用例</h2>
    <div class="col">
        <ul>
            <li data-task="x-basic"><i>2</i><a class="link normal" href="<%=baseUrl%>/test/basic" target="_blank">basic</a></li>
            <li data-task="x-search"><i>1</i><a class="link normal" href="<%=baseUrl%>/test/search/杭州天气" target="_blank">search</a></li>
            <li data-task="x-exception"><i>3</i><a class="link normal" href="<%=baseUrl%>/test/exception" target="_blank">exception</a></li>
            <li data-task="x-api"><i>4</i><a class="link normal" href="<%=baseUrl%>/api" target="_blank">api</a></li>

            <li data-task="x-index"><i>5</i><a class="link normal" href="<%=baseUrl%>/test/index" target="_blank">index</a></li>
            <li data-task="x-json"><i>6</i><a class="link normal" href="<%=baseUrl%>/test/json" target="_blank">json</a></li>
        </ul>
    </div>
    <script src="//macbook-air.local/jquery.js"></script>
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

</script>
</body>
</html>