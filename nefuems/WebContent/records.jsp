<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>订单查询</title>
</head>

<body>
	<br />
	<table style="border:medium solid;">
		<tr>
			<td>订单号码</td>
			<td>时间:</td>
			<td>目前所在地</td>
			<td>经手人</td>
			<td>目前状态</td>
		</tr>
		<c:forEach items="${listRecords }" var="record">
			<tr>
				<td>${record.oid }</td>
				<td>${record.nowTime }</td>
				<td>${record.nowAddress }</td>
				<td>${record.ename}</td>
				<td>${record.ostatus==1?"在途中":"已签收"}</td>
			</tr>
		</c:forEach>

	</table>
	<br>
</body>
</html>
