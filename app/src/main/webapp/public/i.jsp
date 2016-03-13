<%@ page contentType="text/html;charset=utf-8" import="java.util.regex.*,java.io.*,java.net.*,org.ionnic.common.Security"%>
<!doctype html>
<html>
<head>
<title>home web</title>
<link rel="stylesheet" type="text/css" href="css/main.css" />
</head>
<%String baseUrl = request.getContextPath();%>
<body>
    <h2>测试用例</h2>
    <div class="col">
        <ul>
            <li data-task="x-basic"><i>1</i><a class="link normal" href="<%=baseUrl%>/home/basic" target="_blank">basic</a></li>
            <li data-task="x-search"><i>2</i><a class="link normal" href="<%=baseUrl%>/home/search/杭州天气" target="_blank">search</a></li>
            <li data-task="x-exception"><i>3</i><a class="link normal" href="<%=baseUrl%>/home/exception" target="_blank">exception</a></li>
            <li data-task="x-api"><i>4</i><a class="link normal" href="<%=baseUrl%>/home/api" target="_blank">api</a></li>
            <li data-task="x-index"><i>5</i><a class="link normal" href="<%=baseUrl%>/home/index" target="_blank">index</a></li>
            <li data-task="x-db"><i>6</i><a class="link normal" href="<%=baseUrl%>/home/db" target="_blank">db</a></li>
            <li data-task="x-display"><i>7</i><a class="link normal" href="<%=baseUrl%>/home/display" target="_blank">display</a></li>
            <li data-task="x-view"><i>8</i><a class="link normal" href="<%=baseUrl%>/api/view" target="_blank">view</a></li>
            <li data-task="x-404"><i>9</i><a class="link normal" href="<%=baseUrl%>/home/404" target="_blank">404 errro</a></li>
        </ul>
    </div>
    <script src="js/jquery.js"></script>
    <script src="js/main.js"></script>
    <script>
    var csrfToken = '<%=Security.generateToken(request)%>';
    var taskMap = {
        'x-search' : {
            dataType : 'json',
            type : 'post',
            contentType : 'application/json;charset=UTF-8',
            data : JSON.stringify({

            })
        },

        'x-basic' : {
            contentType : 'application/json;charset=UTF-8',
            type : 'post',
            dataType: 'text',
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
        'x-db': {
        	contentType : 'application/json;charset=UTF-8',
            type : 'post',
            dataType: 'json',
            data : JSON.stringify({
                token: csrfToken
            })
        },

        'x-display': {
        	contentType : 'application/json;charset=UTF-8',
            type : 'post',
            dataType: 'html',
            data : JSON.stringify({
                service: 'UserService'
            })
        },

        'x-view': function(callback){
        	var index = 0;
        	var length = 6;

            for(var i = 1; i < length; i++) {
            	var url = "<%=baseUrl%>/home/view/" + i + '?token=' + csrfToken;
            	jQuery.ajax({
            		url: url,
            		dataType: 'json',
            		success: function(data, _, xhr){
            			if(data.status == 0) {
            				index ++;
            			}
            		    if(index == length - 1) {
            		    	callback(0, xhr.responseText);
            		    }
            		},
            		error: function(xhr){
            			callback(1, xhr.responseText);
            		}
            	});
            }
        },

        'x-404': function(callback) {
        	jQuery.ajax({
                url: '<%=baseUrl%>/home/404',
                dataType: 'json',
                success: function(data, _, xhr){
                    if(data.status == 0) {
                        index ++;
                    }
                    if(index == length - 1) {
                        callback(0, xhr.responseText);
                    }
                },
                error: function(xhr){
                	var html = xhr.responseText;
                	if(html.indexOf(' type="hidden" ') > 0) {
                		callback(0, xhr.statusText);
                	} else {
                		callback(1, xhr.responseText);
                	}
                }
            });
        }
    };

    jQuery('a').click(function() {
    	if(!this.getAttribute('data-link')) {
    		this.setAttribute("data-link", this.href);
    	    this.href += "?token=" + csrfToken;
    	};
    });
</script>
<script type="text/javascript" src="<%=baseUrl%>/home/log.js?version=3.0&sid=6565998006"></script>
<script type="text/javascript">

</script>
</body>
</html>