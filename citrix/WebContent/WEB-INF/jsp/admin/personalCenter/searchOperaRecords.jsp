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
							url : '${pageContext.request.contextPath}/admin/searchOperaRecords.do',
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
								field : 'operaTime',
								title : '操作时间',
								formatter : function(value, row, index) {						
										var dt=new Date(row.operaTime);
										
										return dt.toLocaleDateString()+" "+ dt.toLocaleTimeString();						
								},
								width : 200
							}, {
								field : 'ip',
								title : '操作ip地址',
								width : 100
							}, {
								field : 'operaAdminName',
								title : '操作人',
								width : 100,

							}, {
								field : 'operaContent',
								title : '操作内容',
								width : 300
							} ] ]
						});
	}
</script>
</head>

<body>
	<div class="div_grid">
		<table id="datagridTable" title="操作记录" cellspacing="0" cellpadding="0"
			width="100%" iconCls="icon-edit"></table>
	</div>
</body>
</html>
