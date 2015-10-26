<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript">
	$(function() {
		$('#addSend').dialog('close');
		ajaxTable();
	});

	function ajaxTable() {
		$('#datagridTable').datagrid({
			url : '${pageContext.request.contextPath}/employee/searchSendOrders.do',
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
				field : 'oid',
				title : '订单号码',
				width : 200,
				formatter : function(value, row, index) {
					return row.id;
				}
			}, {
				field : 'name',
				title : '收件人',
				width : 100
			}, {
				field : 'tel',
				title : '收件人号码',
				width : 100
			}, {
				field : 'address',
				title : '收件人地址',
				width : 100
			}, {
				field : 'status',
				title : '目前状态',
				formatter : function(value, row, index) {
					if (value == "1") {
						return "未发送";
					}
				},
				width : 100,
			}, {
				field : 'opt',
				title : '操作',
				formatter : function(value, row, index) {
					//return "<a href='javascript:deleteEmployee(" + row.id + ")'>删除</a>";
					if (row.status == "1") {
						var method = "changeStatus(" + "'" + row.id + "'" + ");";
						return "<a class='easyui-linkbutton' icon='icon-add' href='javascript:void(0);'" + "onclick=" + method + ">" + "发送" + "</a>";

					}
				},
				width : 100
			} ] ]
		});
	}
	function changeStatus(id) {
		$.ajax({
			url : '${pageContext.request.contextPath}/employee/changeStatus.do',
			data : {
				id : id,
				status:'2'
			},
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
		$('#addSend').dialog('open');
	}
	function submitUpdate() {
		$.ajax({
			url : '${pageContext.request.contextPath}/employee/addSend.do',
			type : 'POST',
			dataType : 'json',
			data : $('#addSendForm').serialize(),
			success : function(obj) {
				if (obj && obj.success) {
					$.messager.show({
						title : '消息提醒',
						msg : obj.msg,
						timeout : 3000,
						showType : 'slide'
					});
					$("input").val("");
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
	<div class="div_grid">
		<table id="datagridTable" title="标题待定" cellspacing="0" cellpadding="0" width="100%" iconCls="icon-edit"></table>
	</div>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" icon="icon-add" onclick="openAddModule();">录入邮件</a>
	</div>
	<div id="addSend" icon="icon-save" class="easyui-dialog" title="邮件录入" style="width: 400px;height: 300px;top:60px;" align="center" closable='true'>
		<form id="addSendForm">
			<div class="div_ul">
				<ul>
					<li><label>订单编号&nbsp;&nbsp;&nbsp;：</label> <input type="text" class="combo" name="id"></input>
					</li>
					<br />
					<li><label>收件人姓名&nbsp;&nbsp;：</label> <input type="text" class="combo" name="name"></input>
					</li>
					<br />
					<li><label>收件人地址&nbsp;&nbsp;：</label> <input type="text" class="combo" name="address"></input>
					</li>
					<br />
					<li><label>收件人联系电话：</label> <input type="text" class="combo" name="tel"></input>
					</li>
					<br />
					<li align="center"><a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="submitUpdate();">提交</a></li>
				</ul>
			</div>
		</form>
	</div>
</body>
</html>
