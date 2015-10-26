<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户登录</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin/css/Default.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin/css/User_Login.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin/css/xtree.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.3.1/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/common.js" charset="utf-8"></script>
<script type="text/javascript">
	/**
	 * 登陆请求
	 */
	function login() {
	var type=document.getElementById("loginType").value;
	//alert(type);
		if( type== 1) {
		
			submit1();
		} else {
			submit();
		}
	}
	function submit() {
		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/employee/login.do",
			data : $("#form1").serialize(),
			dataType : 'json',
			success : function(r) {
				if (r && r.success) {
					alert(r.msg);
					window.location.href = "${pageContext.request.contextPath}/employee/main.do";
				} else if (r && !r.success) {
					alert(r.msg);
				}
			}
		});
	}
	function submit1() {
		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/admin/login.do",
			data : $("#form1").serialize(),
			dataType : 'json',
			success : function(r) {
				if (r && r.success) {
					window.location.href = "${pageContext.request.contextPath}/admin/main.do";
				} else if (r && !r.success) {
					alert(r.msg);
				}
			}
		});
	}
</script>

</head>
<body id=userlogin_body>
	<div id=user_login>
		<dl>
			<dd id=user_top>
				<ul>
					<li class=user_top_l></li>
					<li class=user_top_c></li>
					<li class=user_top_r></li>
				</ul>
			<dd id=user_main>
				<ul>
					<li class=user_main_l></li>
					<li class=user_main_c>
						<div>
							用户类型：<select id="loginType">
								<option value="1">管理员</option>
								<option value="2">员工</option>
							</select>
						</div>
						<div class=user_main_box>
							<form id="form1" method="post">
								<ul>
									<li class=user_main_text>工号：</li>
									<li class=user_main_input><input class=txtusernamecssclass id=txtusername maxlength=20 name=username>
									</li>
								</ul>
								<ul>
									<li class=user_main_text>密 码：</li>
									<li class=user_main_input><input class=txtpasswordcssclass id=txtpassword type=password name=password>
									</li>
								</ul>
							</form>
						</div></li>
					<li class=user_main_r><input class=ibtnentercssclass style="border-top-width: 0px; border-left-width: 0px; border-bottom-width: 0px; border-right-width: 0px" onclick="login();" type=image src="${pageContext.request.contextPath }/images/admin/user_botton.gif">
					</li>
				</ul>
			<dd id=user_bottom>
				<ul>
					<li class=user_bottom_r></li>
				</ul>
			</dd>
		</dl>
	</div>
	<span id=valrusername style="display: none; color: red"></span>
	<span id=valrpassword style="display: none; color: red"></span>
	<span id=valrvalidatecode style="display: none; color: red"></span>
	<div id=validationsummary1 style="display: none; color: red"></div>
	<div></div>
</body>
</body>
</html>