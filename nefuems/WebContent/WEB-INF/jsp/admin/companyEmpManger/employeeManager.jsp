<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript">
	$(function() {
		$('#addEmployee').dialog('close');
		ajaxTable();
		$("#username").val("");
		$("#password").val("");
		$("name").val("");
	});

	function ajaxTable() {
		$('#datagridTable').datagrid({
			url : '${pageContext.request.contextPath}/admin/searchEmployee.do',
			toolbar : '#toolbar',
			checkOnSelect : false,
			pagination : true,// 是否分页
			fitColumns : true,// 列自适应表格宽度
			striped : true,// 当true时，单元格显示条纹
			loadMsg : '数据加载中,请稍后...',
			onLoadError : function() {
				alert('数据加载失败!');
			},
			onLoadSuccess : function(data) {
			},
			columns : [ [ {
				field : 'id',
				checkbox : 'true',
				width : 20
			}, {
				field : 'username',
				title : '工号',
				width : 200
			}, {
				field : 'name',
				title : '姓名',
				width : 100
			}, {
				field : 'companyname',
				title : '所在公司',
				formatter : function(value, row, index) {
					return row.company.companyName;
				},
				width : 100,
			}, {
				field : 'opt',
				title : '操作',
				formatter : function(value, row, index) {
					return "<a href='javascript:deleteEmployee(" + row.id + ")'>删除</a>";
				},
				width : 100
			} ] ]
		});
	}
	function deleteEmployee(id) {
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/delEmployee.do?id=' + id,
			type : 'POST',
			dataType : 'json',
			success : function(obj) {
				if (obj && obj.success) {
					$.messager.show({
						title : '消息提醒',
						msg : obj.msg,
						timeout : 3000,
						showType : 'slide'
					});
					ajaxTable();
				}
				if (obj && !obj.success) {
					$.messager.alert('消息提示:', obj.msg);
				}
			}
		});
	}
	function openAddModule() {
		$('#addEmployee').dialog('open');
	}
	function submitUpdate() {
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/addEmployee.do',
			type : 'POST',
			dataType : 'json',
			data : $('#addEmployeeForm').serialize(),
			success : function(obj) {
				if (obj && obj.success) {
					$.messager.show({
						title : '消息提醒',
						msg : obj.msg,
						timeout : 3000,
						showType : 'slide'
					});
					// $('#addEmployeeForm').onreset();
					ajaxTable();
				}
				if (obj && !obj.success) {
					$.messager.alert('消息提示:', obj.msg);
				}
			}
		});
	}
</script>
</head>
<body>
	<br />
	<div class="div_grid">
		<table id="datagridTable" title="员工列表" cellspacing="0" cellpadding="0" width="100%" iconCls="icon-edit"></table>
	</div>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" icon="icon-add" onclick="openAddModule();">添加员工</a>
	</div>
	<div id="addEmployee" icon="icon-save" class="easyui-dialog" title="添加员工" style="width: 400px;height: 300px;top:60px;" align="center" closable='true'>
		<form id="addEmployeeForm">
			<div class="div_ul">
				<ul>
					<li><label>员工工号&nbsp;&nbsp;：</label> <input type="text" class="combo" name="username"></input>
					</li>
					<br />
					<li><label>员工姓名&nbsp;&nbsp;：</label> <input type="text" class="combo" name="name"></input>
					</li>
					<br />
					<li><label>登录密码&nbsp;&nbsp;：</label> <input type="password" class="combo" name="password"></input>
					</li>
					<br />
					<li><label>公司名称&nbsp;&nbsp;：</label>
					 <select name="company">
							<c:forEach items="${requestScope.companys }" var="company">
								<option value="${company.id }">${company.companyName }</option>
							</c:forEach>
					</select>
					</li>
					<li><label>角色&nbsp;&nbsp;：</label> 管理者：<input type="radio" class="combo" name="role" value="manager" />普通员工：<input type="radio" class="combo" name="role" value="clerk" />
					</li>
					<br />
					<li align="center"><a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="submitUpdate();">提交</a> <input type="reset" value="重置">
					</li>
				</ul>
			</div>
		</form>
	</div>
</body>
</html>
