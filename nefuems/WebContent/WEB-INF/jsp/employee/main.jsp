<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>东北林业大学快递联合中心</title>
<style type="text/css">
.list{margin-left: 50px; border-top-width: 1px; border-left-width: 1px; padding-bottom: 1px; margin-top: 10px; border-bottom-width: 10px;}
</style>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body>
	<div id="cc" class="easyui-layout" fit=true>
		<div data-options="region:'north',split:false" style="height:100px;background-image: url('${pageContext.request.contextPath}/pic/nefulog.png');">
			<h2>用户: ${employee.name } 工号：${employee.username} 所属公司：${employee.company.companyName }</h2>
			<span><a href='${pageContext.request.contextPath }/employee/logout.do'>退出</a> </span>
		</div>
		<div data-options="region:'west',title:'邮件管理',split:false" style="width:170px;">
			<div class="easyui-accordion" fit="true" border="false">
				<div title="信息录入" height="200px">
					<div class="list">
						<a href="javascript:addTab('tabid_send','发送邮件','show/add/send.do')">发送邮件</a>
					</div>
					<div class="list">
						<a href="javascript:addTab('tabid_historySend','查询历史发送邮件','show/add/historySend.do')">已发送</a>
					</div>
					<div class="list">
						<a href="javascript:addTab('tabid_receive','接收邮件','show/add/receive.do')">接收邮件</a>
					</div>
					<div class="list">
						<a href="javascript:addTab('tabid_historyReceive','查询历史接收邮件','show/add/historyReceive.do')">已接收</a>
					</div>
					<div class="list">
						<a href="javascript:addTab('tabid_message','短信中心','show/add/messageCenter.do')">
							<%-- <img src="${pageContext.request.contextPath }/pic/msg.jpg"/> --%>短信中心</a>
					</div>
				</div>
				<%--<div title="公司信息" heigth="200px">
					<div style="margin-top: 10px; margin-left: 40px; border-bottom-width: 10px; width: 168px;">
						<a href="javascript:addTab('tabid_notice','发送邮件','show/add/notice.do')">
						<img style="width: 80px; height: 60px;" src="${pageContext.request.contextPath }/pic/notice.png"></img>
						</a>
					</div>
				</div>
			--%></div>
		</div>
		<div data-options="region:'center'" style="padding:0px;background:#eee;">
			<div class="easyui-tabs" id="centerTab" fit="true" border="false">
				<div title="欢迎页" style="padding:20px;overflow:hidden;" data-options="closable:true">
					<div style="margin-top:20px;">
						<h2>操作规章!</h2>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
