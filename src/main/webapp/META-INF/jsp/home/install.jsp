<%@page contentType="text/html;charset=gb2312"%>
<%@page import="java.io.*,java.util.*,java.net.*"%>
<%!
	String strOS = System.getProperty("os.name");
	private String getIp() {
		String strTmp = "";
		try {
			strTmp = InetAddress.getLocalHost().getHostAddress();
			return strTmp;
		} catch (Exception e) {
			return strTmp;
		}
	}

	private String getSystemEnv() throws Exception {
		String OS = System.getProperty("os.name").toLowerCase();
		StringBuffer sb = new StringBuffer("");
		Process p = null;
		if (OS.indexOf("windows") > -1) {
			p = Runtime.getRuntime().exec("cmd /c set");
		} else {
			p = Runtime.getRuntime().exec("/bin/sh -c set");
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line + "<br>");
		}
		return sb.toString();
	}

	private String getDrivers() {
		StringBuffer sb = new StringBuffer("");
		File roots[] = File.listRoots();
		for (int i = 0; i < roots.length; i++) {
			sb.append(roots[i] + " ");
		}
		return sb.toString();
	}%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>jsp探针</title>
<style type="text/css">
<!--
body,td,th {
	font-size: 9pt;
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
table {width: 890px}
a:link {
	text-decoration: none;
}

a:visited {
	text-decoration: none;
}

a:hover {
	text-decoration: none;
}

a:active {
	text-decoration: none;
}

.STYLE4 {
	color: #000000
}

.STYLE5 {
	font-weight: bold;
	font-size: 10pt;
}

.STYLE7 {
	font-size: 10
}
-->
</style>
</head>

<body>
	<div align="center">
		<br>
		<table>
			<tr>
				<td colspan="2" height="22" bgcolor="#E0E0E0"><span class="STYLE5">&nbsp;服务器基本信息 </span>
				</td>
			</tr>
			<tr>
				<td height="23" width="20%">&nbsp;服务器名称</td>
				<td>&nbsp;<%=request.getServerName()%></td>
			</tr>
			<tr>
				<td height="23" width="20%">&nbsp;域名/IP</td>
				<td>&nbsp;<%=request.getServerName()%> / <%=getIp()%></td>
			</tr>
			<tr>
				<td height="23">&nbsp;服务器端口</td>
				<td>&nbsp;<%=request.getServerPort()%></td>
			</tr>
			<tr>
				<td height="23">&nbsp;客户端端口</td>
				<td>&nbsp;<%=request.getRemotePort()%></td>
			</tr>
			<tr>
				<td height="23">&nbsp;客户端IP</td>
				<td>&nbsp;<%=request.getRemoteAddr()%></td>
			</tr>
			<tr>
				<td height="23">&nbsp;Web 服务器</td>
				<td>&nbsp;<%=application.getServerInfo()%></td>
			</tr>

			<tr>
				<td height="23">&nbsp;操作系统</td>
				<td>&nbsp;<%=strOS + " " + System.getProperty("sun.os.patch.level") + " Ver:" + System.getProperty("os.version")%></td>
			</tr>
			<tr>
				<td height="23">&nbsp;服务器时间</td>
				<td>&nbsp;<%=new Date()%></td>
			</tr>
			<tr>
				<td height="23" width="20%">&nbsp;CPU 信息</td>
				<td>&nbsp;<%=System.getProperty("os.arch")%></td>
			</tr>
			<tr>
				<td height="23" width="20%">&nbsp;磁盘分区</td>
				<td>&nbsp;<%=getDrivers()%></td>
			</tr>
			<tr>
				<td height="23" width="20%">&nbsp;用户当前工作目录</td>
				<td>&nbsp;<%=System.getProperty("user.dir")%></td>
			</tr>
			<tr>
				<td height="23">&nbsp;本文件路径</td>
				<td>&nbsp;<%=application.getRealPath(request.getRequestURI())%></td>
			</tr>
		</table>
		<%
		Properties p = System.getProperties();
		response.getWriter().append(p.keys().toString());
		%>
		<br>
		<table>
			<tr>
				<td colspan="2" height="22" bgcolor="#E0E0E0"><span class="STYLE4">&nbsp;<span class="STYLE5">Java
							相关信息</span> </span>
				</td>
			</tr>
			<tr>
				<td height="23" width="20%"><span class="STYLE7">&nbsp;JDK 版本</span>
				</td>
				<td>&nbsp;<%=System.getProperty("java.version")%></td>
			</tr>
			<tr>
				<td height="23" width="20%">&nbsp;Servlet 版本</td>
				<td>&nbsp;<%=application.getMajorVersion() + "." + application.getMinorVersion()%></td>
			</tr>
			<tr>
				<td height="23" width="20%">&nbsp;JDK 安装路径</td>
				<td>&nbsp;<%=System.getProperty("java.home")%></td>
			</tr>
			<tr>
				<td height="23" width="20%">&nbsp;编码</td>
				<td>&nbsp;<%=System.getProperty("file.encoding")%></td>
			</tr>
			<tr>
				<td height="23" width="20%">&nbsp;JAVA类路径</td>
				<td>&nbsp;<%=System.getProperty("java.class.path")%></td>
			</tr>
		</table>
		<br>
		<table>
			<tr>
				<td colspan="2" height="22" bgcolor="#E0E0E0">&nbsp;服务器环境变量 &nbsp;</td>
			</tr>
			<tr>
				<td colspan="2"><%=getSystemEnv()%></td>
			</tr>
		</table>
		<br>

	</div>
</body>
</html>