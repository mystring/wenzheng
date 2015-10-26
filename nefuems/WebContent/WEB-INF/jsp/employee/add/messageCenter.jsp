<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript">
	$(function() {
		// 加载表格数据
		ajaxTable();
	});
	// 加载表格
	function ajaxTable() {
		$('#datagridTable')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/employee/getMsg.do',
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
								field : 'time',
								title : '发送时间',
								formatter : function(value, row, index) {						
										var dt=new Date(row.time);
										
										return dt.toLocaleDateString()+" "+ dt.toLocaleTimeString();						
								},
								width : 150
							}, {
								field : 'name',
								title : '联系人',
								width : 50
							}, {
								field : 'tel',
								title : '联系电话',
								width : 100,

							}, {
								field : 'msg',
								title : '发送内容',
								width : 400

							} ] ]
						});
	}
</script>
</head>

<body>
	<div class="div_grid">
		<table id="datagridTable" title="短信中心" cellspacing="0" cellpadding="0"
			width="100%" iconCls="icon-edit"></table>
	</div>
</body>
</html>
