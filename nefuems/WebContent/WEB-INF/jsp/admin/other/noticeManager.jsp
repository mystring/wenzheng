<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript">
	function submit1() {
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/addNotice.do',
			type : 'POST',
			dataType : 'json',
			data : $('#addNoticeForm').serialize(),
			success : function(obj) {
				if (obj && obj.success) {
					$.messager.show({
						title : '消息提醒',
						msg : obj.msg,
						timeout : 3000,
						showType : 'slide'
					});
					//ajaxTable();
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
	活动通知添加:
	<br />
	<div>
		<form id="addNoticeForm">
			公告标题:&nbsp;&nbsp;<input type="text" name="title" /> <br /> 公告内容:
			<textarea name="content" style="width: 351px; height: 254px;"></textarea>
			<a href="javascript:submit1();">提交</a>
		</form>
	</div>
</body>
</html>
