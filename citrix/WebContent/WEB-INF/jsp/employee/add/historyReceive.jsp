<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript">
	$(function() {
		ajaxTable();
	});

	function ajaxTable() {
		$('#datagridTable').datagrid({
			url : '${pageContext.request.contextPath}/employee/searchHistoryReceiveOrders.do',
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
				field : 'oid',
				title : '订单号码',
				width : 100,
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
					if (value == "2") {
						return "已发送";
					}
					if (value == "4") {
						return "已签收";
					}
				},
				width : 100
			}, {
				field : 'receiveTime',
				title : '签收时间',
				formatter : function(value, row, index) {
					var dt = new Date(row.receiveTime);
					return dt.toLocaleDateString() + " " + dt.toLocaleTimeString();
				},
				width : 200
			} ] ]
		});
	}
</script>
</head>
<body>
	<div class="div_grid">
		<table id="datagridTable" title="标题待定" cellspacing="0" cellpadding="0" width="100%" iconCls="icon-edit"></table>
	</div>
</body>
</html>
