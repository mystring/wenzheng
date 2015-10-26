<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript">
	$(function() {
		$('#updateDialog').dialog('close');
		ajaxTable();
	});
	function ajaxTable() {
		$('#datagridTable').datagrid({
			url : '${pageContext.request.contextPath}/admin/searchCompany.do',
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
				field : 'companyName',
				title : '公司名称',
				width : 200
			}, {
				field : 'address',
				title : '官网首页',
				width : 100
			}, {
				field : 'createTime',
				title : '添加日期',
				formatter : function(value, row, index) {
					var dt = new Date(row.createTime);

					return dt.toLocaleDateString() + ":" + dt.toLocaleTimeString();
				},
				width : 100,
			}, {
				field : 'opt',
				title : '操作',
				formatter : function(value, row, index) {
					return "<a href='javascript:deleteCompany(" + row.id + ")'>删除</a>&nbsp;" + "<a href='javascript:detailCompany(" + row.id + ")'>查看详细</a>&nbsp;";
				},
				width : 100
			} ] ]
		});
	}
	function deleteCompany(id) {
		$.messager.confirm('删除提示', '你确定删除该数据吗?', function(r) {
			if (r) {
				$.ajax({
					url : '${pageContext.request.contextPath}/admin/delCompany.do?id=' + id,
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
		});
	}
	function detailCompany(id) {
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/detailCompany.do?id=' + id,
			type : 'POST',
			dataType : 'json',
			success : function(obj) {
				$('#cid').val(obj.obj.id);
				$("#companyName").val(obj.obj.companyName);
				$("#address").val(obj.obj.address);
				$("#createTime").val(timeFormat(obj));
				$('#updateDialog').dialog('open');
			}
		});
	}
	function timeFormat(obj) {
		var dt = new Date(obj.obj.createTime);
		return dt.toLocaleDateString() + ":" + dt.toLocaleTimeString();
	}
	function addCompany() {
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/addCompany.do',
			type : 'POST',
			dataType : 'json',
			data : $('#addCompanyForm').serialize(),
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
	function submitUpdate() {
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/updateCompany.do',
			type : 'POST',
			dataType : 'json',
			data : $('#updateCompanyForm').serialize(),
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
	添加公司
	<br />
	<form id="addCompanyForm">
		公司名称:<input type="text" name="companyName" /> 公司官网<input type="text" name="address"> <input type="button" value="提交" onclick="addCompany()">
	</form>
	<div class="div_grid">
		<table id="datagridTable" title="公司列表" cellspacing="0" cellpadding="0" width="100%" iconCls="icon-edit"></table>
	</div>
		<div id="updateDialog" icon="icon-save" class="easyui-dialog" title="查看详细" style="width: 400px;height: 200px;top: 60px;" align="center" closable='true'>
			<form id="updateCompanyForm">
				<input type="hidden" id="cid" name="id" />
				<div class="div_ul">
					<ul>
						<li><label>公司名称&nbsp;&nbsp;：</label> <input type="text" class="combo" id="companyName" name="companyName"></input>
						</li>
						<br />
						<li><label>公司官网&nbsp;&nbsp;：</label> <input type="text" class="combo" id="address" name="address"></input>
						</li>
						<li><label>创立时间&nbsp;&nbsp;：</label> <input type="text" readonly="readonly" class="combo" id="createTime" name="createTime"></input>
						</li>
						<br />
						<li align="center"><a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="submitUpdate();">修改</a>
						</li>
					</ul>
				</div>
			</form>
		</div>
</body>
</html>
