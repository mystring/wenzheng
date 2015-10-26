<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript">
	function submitPassword() {
		var prePwd = $('#prePwd')[0].value;
		var newPwd = $('#newPwd')[0].value;
		var newPwd1 = $('#newPwd1')[0].value;
		if (prePwd == "") {
			$.messager.alert("消息提示", "旧密码不能为空！");
			return;
		}
		if (newPwd == "" || newPwd1 == "") {
			$.messager.alert("消息提示", "请输入新密码！");
			return;
		}
		if (newPwd != newPwd1) {
			$.messager.alert("消息提示", "两次输入密码不一致!");
			return;
		}
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/updatePwd.do',
			type : "POST",
			data : $("#updatePwdForm").serialize(),
			dataType : 'json',
			success : function(obj) {
				if (obj && obj.success) {
					$.messager.show({
						title : '消息提醒',
						msg : '密码修改成功',
						timeout : 3000,
						showType : 'slide'
					});
					$("#prePwd").val("");
					$("#newPwd").val("");
					$("#newPwd1").val("");
				}
				if (obj && !obj.success) {
					$.messager.alert('消息提示:', obj.msg);
				}
			}

		});
	}
	$(function() {
		$("#prePwd").val("");
		$("#newPwd").val("");
		$("#newPwd1").value("");
	});
</script>
</head>
<body>
	<div style="padding: 200px" align="center">
		<div id="addDoc" icon="icon-save" class="easyui-dialog" title="修改密码" style="width: 400px;height: 400px;" align="center" closable='false'>
			<form id="updatePwdForm">
				<div class="div_ul">
					<ul>
						<li><label>旧密码&nbsp;&nbsp;：</label> <input type="password" class="combo" id="prePwd" name="prePwd"></input></li>
						<br />
						<li><label>新密码&nbsp;&nbsp;：</label> <input type="password" class="combo" id="newPwd" name="newPwd"></input></li>
						<br />
						<li><label>确认新密码：</label> <input type="password" class="combo" id="newPwd1" name="newPwd1"></input>
						</li>
						<br />
						<li align="center"><a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="submitPassword();">提交</a>
						</li>
					</ul>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
