<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.mystring.pojo.Notice"%>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>东北林业大学快递首页</title>
</head>
<body>

	<form action="login.do">
		用户名：<input type="text" name="username"><br/>
		密码：<input type="text" name="password"><br/>
		域名：<input type="text" name="domain"><br/>	
		ip：<input type="text" name="ip"><br/>
		端口：<input type="text" name="port"><br/>
		应用id：<input type="text" name="appId"><br/>
		url：<input type="text" name="url"><br/>
		<input type="submit" value="submit"><a href="logout.do">注销</a>
	</form>
</body>

</body>
</html>