<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>东北林业大学ems后台管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body>
	<div id="cc" class="easyui-layout" fit=true>
		<div data-options="region:'north',split:false" style="height:100px;background-image: url('${pageContext.request.contextPath}/images/admin/top1.png');">
			<h1>欢迎您: ${admin.name },您上次登陆Ip为 : ${admin.lastLoginIp}, 上次登陆时间: ${admin.lastLoginTime }</h1>
			<span><a href='${pageContext.request.contextPath }/admin/logout.do'>退出</a> </span>
		</div>
		<div data-options="region:'west',title:'后台管理',split:false" style="width:170px;">
			<div class="easyui-accordion" fit="true" border="false">
				<div title="个人信息管理" height="200px">
					<ul>
						<li><a href="javascript:addTab('tabid_updatePassword','修改密码','show/personalCenter/updatePassword.do')">修改密码</a></li>
						<li><a href="javascript:addTab('tabid_searchLoginRecords','查询操作记录','show/personalCenter/searchOperaRecords.do')">查询操作记录</a></li>
					</ul>
				</div>
				<div title="企业用户管理" selected='true'>
					<ul>
						<li><a href="javascript:addTab('tabid_companyManager','企业管理','show/companyEmpManger/companyManager.do')">公司信息管理</a></li>
						<li><a href="javascript:addTab('tabid_employeeManager','雇员管理','show/companyEmpManger/employeeManager.do')">雇员信息管理</a></li>
					</ul>
				</div>
				<div title="其他">
					<ul>
						<li><a href="javascript:addTab('tabid_noticeManager','同志公告','show/other/noticeManager.do')">通知公告</a>
						</li>
					</ul>
				</div>
			</div>
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
