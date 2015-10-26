<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript">
	$(function() {
		ajaxComboTree();
		ajaxTable();
	});
	function ajaxComboTree() {
		$('#position').combotree({
			required : true
		});
		$('#position').combotree('loadData', [ {
			'id' : 1,
			text : '1号仓位'
		}, {
			'id' : 2,
			text : '2号仓位'
		}, {
			'id' : 3,
			text : '3号仓位'
		}, {
			'id' : 4,
			text : '4号仓位'
		} ]);
	}
	function ajaxTable() {
		$('#datagridTable')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/employee/searchReceiveOrders.do',
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
							columns : [ [
									{
										field : 'id',
										checkbox : 'true',
										width : 20
									},
									{
										field : 'oid',
										title : '订单号码',
										width : 100,
										formatter : function(value, row, index) {
											return row.id;
										}
									},
									{
										field : 'name',
										title : '收件人',
										width : 100
									},
									{
										field : 'tel',
										title : '收件人号码',
										width : 100
									},
									{
										field : 'address',
										title : '收件人地址',
										width : 150
									},
									{
										field : 'position',
										title : '仓位',
										width : 50,
										formatter : function(value, row, index) {
											return row.position + "号仓位";
										}
									},
									{
										field : 'status',
										title : '目前状态',
										formatter : function(value, row, index) {
											if (value == "3") {
												return '待接收';
											}
										},
										width : 50,
									},
									{
										field : 'opt',
										title : '操作',
										formatter : function(value, row, index) {
											//return "<a href='javascript:deleteEmployee(" + row.id + ")'>删除</a>";
											if (row.status == "3") {
												var method = "changeStatus("
														+ "'" + row.id + "'"
														+ ");";
												var method2 = "sendMsg(" + "'"
														+ row.id + "'" + ");";
												return "<a class='easyui-linkbutton' icon='icon-add' href='javascript:void(0);'"
														+ "onclick="
														+ method
														+ ">"
														+ "领取"
														+ "</a>"
														+ "  "
														+ "<a  href='javascript:void(0);'"
														+ "onclick="
														+ method2
														+ ">" + "发送短信" + "</a>";

											}
										},
										width : 100
									} ] ]
						});
	}

	function batchSendMsg() {
		var ids = [];
		var selectedRow = $('#datagridTable').datagrid('getSelections');
		for ( var i = 0; i < selectedRow.length; i++) {
			ids.push(selectedRow[i].id);
		}
		ids = ids.join(',');
		console.info(ids);
		$
				.ajax({
					url : '${pageContext.request.contextPath}/employee/batchSendMsg.do',
					data : {
						ids : ids
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
	function sendMsg(id) {
		$.ajax({
			url : '${pageContext.request.contextPath}/employee/sendMsg.do',
			data : {
				id : id
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
	function changeStatus(id) {
		$
				.ajax({
					url : '${pageContext.request.contextPath}/employee/changeStatus.do',
					data : {
						id : id,
						status : '4'
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
	
	function searchAjax(){
		$('#datagridTable')
		.datagrid(
				{
					url : '${pageContext.request.contextPath}/employee/searchReceiveOrders.do',
					queryParams:{
						name:$("#name").val(),
						tel:$("#tel").val()
					},
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
					columns : [ [
							{
								field : 'id',
								checkbox : 'true',
								width : 20
							},
							{
								field : 'oid',
								title : '订单号码',
								width : 100,
								formatter : function(value, row, index) {
									return row.id;
								}
							},
							{
								field : 'name',
								title : '收件人',
								width : 100
							},
							{
								field : 'tel',
								title : '收件人号码',
								width : 100
							},
							{
								field : 'address',
								title : '收件人地址',
								width : 150
							},
							{
								field : 'position',
								title : '仓位',
								width : 50,
								formatter : function(value, row, index) {
									return row.position + "号仓位";
								}
							},
							{
								field : 'status',
								title : '目前状态',
								formatter : function(value, row, index) {
									if (value == "3") {
										return '待接收';
									}
								},
								width : 50,
							},
							{
								field : 'opt',
								title : '操作',
								formatter : function(value, row, index) {
									//return "<a href='javascript:deleteEmployee(" + row.id + ")'>删除</a>";
									if (row.status == "3") {
										var method = "changeStatus("
												+ "'" + row.id + "'"
												+ ");";
										var method2 = "sendMsg(" + "'"
												+ row.id + "'" + ");";
										return "<a class='easyui-linkbutton' icon='icon-add' href='javascript:void(0);'"
												+ "onclick="
												+ method
												+ ">"
												+ "领取"
												+ "</a>"
												+ "  "
												+ "<a  href='javascript:void(0);'"
												+ "onclick="
												+ method2
												+ ">" + "发送短信" + "</a>";

									}
								},
								width : 100
							} ] ]
				});
		
	}
	function receive() {
		$.ajax({
			url : '${pageContext.request.contextPath}/employee/receive.do',
			type : 'POST',
			dataType : 'json',
			data : {
				position:$('#position').combotree('getValue'),
				oid:$("#oid").val()
			},
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
</script>
</head>
<body>
	<div class="div_grid">
		<table id="datagridTable" title="接收邮件" cellspacing="0" cellpadding="0" width="100%" iconCls="icon-edit"></table>
	</div>
	<div id="toolbar">
		<div id="toolbar"><%--
			<form id="form1">	
			--%><label>仓位： </label><input name="position" id="position" class="easyui-combotree" style="width:200px;"></input>
			<label>订单号码: </label> <input class="combo"  type="text" name="oid" id="oid"/> 
			<a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-add" onclick="receive();">录入邮件</a>
				<%--</form>	
			--%><label>姓名: </label><input class="Wdate"  id="name" name="name" type="text"  size="12" />
			<label>电话: </label><input class="Wdate"  id="tel" name="tel" type="text"  size="12"  />
			<a href="#" class="easyui-linkbutton" icon="icon-add" onclick="searchAjax();">查询</a>
			<a href="javascript:void(0);" style="border: medium outset" onclick="batchSendMsg();">批量发送短信</a>
		</div>
	</div>
</body>
</html>
