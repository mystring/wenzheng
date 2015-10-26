<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.mystring.pojo.Notice"%>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>东北林业大学快递首页</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.3.1/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/common.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/csswebutf.js"></script>
<style type="text/css">
/*弹出层*/
.oplayer1 {
	width: 460px;
	height: 200px;
	position: fixed;
	_position: absolute;
	z-index: 9999;
	overflow-y: auto;
	background: #fff;
	border: 2px solid #DE2604;
}

.oplayer1 .tbar {
	height: 24px;
	padding: 4px 8px;
	background: #de2604;
}

#winmask {
	background: none repeat scroll 0 0 #000000;
	left: 0;
	opacity: 0.5;
	filter: alpha(opacity =          
		                                                         
		            50);
	position: absolute;
	top: 0;
	width: 100%;
	z-index: 999;
}

.fn-clear {
	margin-bottom: 15px;
}

.login label {
	display: inline-block;
	font-size: 14px;
	text-align: right;
	width: 100px;
}

.login .fn-clear1 input {
	width: 141px;
}

tr:HOVER {
	background-color: red;
}

.tr0 {
	background-color: #F1F1F1;
}

.tr1 {
	
}
/*弹出层*/
.zj_middle {
	margin: 15px auto 0;
	width: 1000px;
}

.Forget {
	background: none repeat scroll 0 0 #F1F1F1;
	border-bottom: 1px solid #DDDDDD;
	font-size: 15px;
	font-weight: bold;
	height: 49px;
	line-height: 49px;
	margin-bottom: 20px;
	padding-left: 20px;
}

.Registration_left {
	border-right: 1px dashed #DDDDDD;
	display: inline;
	float: left;
	margin: 25px 0 70px 70px;
	width: 500px;
}

.fn-clear {
	margin-bottom: 10px;
}

.fn-clearq label {
	display: inline-block;
	float: left;
	line-height: 30px;
	width: 85px;
}

.Registration_left label {
	display: inline-block;
	font-size: 14px;
	text-align: right;
	width: 85px;
}

.select_bordera {
	display: inline-block;
	margin-left: 10px;
	width: 246px;
}

.containera {
	
}

.selecta {
	border: 1px solid #CCCCCC;
	color: #666666;
	font-size: 13px;
	line-height: 40px;
	margin: -1px;
	padding: 9px;
	width: 246px;
}

.Registration_left input {
	border: 1px solid #DDDDDD;
	color: #AAAAAA;
	font-size: 14px;
	height: 35px;
	line-height: 35px;
	margin: 0 10px;
	padding-left: 10px;
	vertical-align: middle;
	width: 236px;
}

.Registration_left .pc1 {
	margin-top: 20px;
}

.Registration_left p {
	color: #666666;
	padding-bottom: 25px;
	padding-left: 95px;
	width: 370px;
}

body,p {
	font-size: 12px;
}

element.style {
	cursor: pointer;
}

a {
	color: #333333;
	text-decoration: none;
}

* {
	margin: 0;
	padding: 0;
}

.Registration_left img {
	vertical-align: middle;
}

img {
	border: 0 none;
}

.pc1 input {
	margin-right: 0;
	width: 50px;
}

.clear {
	clear: both;
}

.Registration_right {
	display: inline;
	float: right;
	margin-top: 20px;
	width: 388px;
}

.zj_footer {
	clear: both;
	color: #666666;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 30px;
	margin: 0 auto;
	padding: 20px 0;
	text-align: center;
	width: 1000px;
}

.zj_foot {
	margin-top: 300px;
	background: url("pic/box5.png") repeat-x scroll 0 0 rgba(0, 0, 0, 0);
	height: 72px;
	width: 100%;
}
</style>
<script type="text/javascript">
	/**
	 * 登陆请求
	 */
	function login() {
		$("#loginBtn").css("display", "none");
		$("#loginBtn1").css("display", "block");
		var type = document.getElementById("userLoginType").value;
		//alert(type);
		if (type == 1) {
			submit1();
		} else {
			submit();
		}
	}
	function submit() {
		$
				.ajax({
					type : "POST",
					url : "${pageContext.request.contextPath}/employee/login.do",
					data : $("#form1").serialize(),
					dataType : 'json',
					success : function(r) {
						if (r && r.success) {
							alert(r.msg);
							window.location.href = "${pageContext.request.contextPath}/employee/main.do";
						} else if (r && !r.success) {
							$("#loginBtn").css("display", "block");
							$("#loginBtn1").css("display", "none");
							alert(r.msg);
						}
					}
				});
	}
	function submit1() {
		$
				.ajax({
					type : "POST",
					url : "${pageContext.request.contextPath}/admin/login.do",
					data : $("#form1").serialize(),
					dataType : 'json',
					success : function(r) {
						if (r && r.success) {
							window.location.href = "${pageContext.request.contextPath}/admin/main.do";
						} else if (r && !r.success) {
							$("#loginBtn").css("display", "block");
							$("#loginBtn1").css("display", "none");
							alert(r.msg);
						}
					}
				});
	}
	$(function() {
		var newDate = new Date();
		var day = newDate.getDay();
		var st = newDate.getFullYear() + "年" + newDate.getMonth() + "月"
				+ newDate.getDate() + "日  ";
		if (day == 1) {
			st = st + "星期一";
		}
		if (day == 2) {
			st = st + "星期二";
		}
		if (day == 3) {
			st = st + "星期三";
		}
		if (day == 4) {
			st = st + "星期四";
		}
		if (day == 5) {
			st = st + "星期五";
		}
		if (day == 6) {
			st = st + "星期六";
		}
		if (day == 7) {
			st = st + "星期日";
		}
		$("#topDate").text(st);
	});
</script>

</head>
<body>
	<div align="center">
		<div>
			<h1 align="center">
				<font size="26px;">校园联合快递中心信息系统</font>
			</h1>
			<hr />
		</div>
		<img src="pic/top2.jpg" width="100%;">
	</div>
	<div class="zj_middle">
		<div class="Forget">
			<div>
				今天是：<span id="topDate"></span>欢迎您的到来
			</div>
		</div>
		<div class="Registration_left">
			<form id="form1">
				<div class="fn-clear fn-clearq">
					<label>用户类型：</label>
					<div class="select_bordera">
						<div class="containera">
							<select class="selecta" name="userLoginType" id="userLoginType">
								<option value="1">管理员</option>
								<option value="2" selected="selected">普通员工</option>
							</select>
						</div>
					</div>
				</div>
				<div class="fn-clear">
					<label>用户名：</label><span id="accountSpan"><input type="text" onblur="if(this.value=='') {this.value='请输入用户名';this.style.color='#ccc';}" onfocus="if(this.value=='请输入用户名') {this.value='';}this.style.color='#333';" value="请输入用户名" nextid="servicepwd" id="account" name="username"> </span>
				</div>
				<div class="fn-clear">
					<label>密码：</label><input type="password" value="" id="servicepwd" name="password">
				</div>
				<p class="pc1">
					<a onclick="login()" style="cursor:pointer" id="subimg" target="_blank"> <img src="pic/Login3.jpg" id="loginBtn"> </a> <img id="loginBtn1" style="display:none" src="pic/Login4.jpg">
				</p>
			</form>
		</div>
		<div class="Registration_right">
			<table>
				<tr>
					<td align="left" style="border: thin solid;background-color: #FF6347;">通知公告</td>
				<tr>
					<c:forEach items="${noticeList }" var="notice" begin="0" step="1" varStatus="status">
						<tr class="tr${status.index%2 }">
							<td style="height: 20px; width: 300px;"><a href="javascript:Effect.openwin('notice${notice.id }','top','20');">${notice.title }</a>
							<td>
								<%
									Notice notice = (Notice) pageContext.getAttribute("notice");
										String time = new SimpleDateFormat("yyyy-MM-dd").format(notice
												.getTime());
								%><%=time%></td>
						</tr>
					</c:forEach>
			</table>
			<form action="getRecords.do">
				<table>
					<tr>
						<td align="left" style="border: thin solid;background-color: #FF6347;">快递查询</td>
					<tr>
					<tr>
						<td>订单号码</td>
						<td><input type="text" name="orderid"></td>
						<td><input type="submit" value="查询">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
	<div>
		友情链接:
		<ul>
			<c:forEach items="${companyList }" var="company">
				<li style="float: left;font-size: 12px; text-align: center; width: 110px;"><a style="border-right: 1px solid #999999;display: block; margin-top: 20px;width: 110px;" href="${company.address }">${company.companyName }</a></li>
			</c:forEach>
		</ul>
	</div>

	<div class="clear"></div>









	<div style="background-color:#093001 ">
		<img src="pic/box5.png">
	</div>
	<c:forEach items="${noticeList }" var="notice">
		<div class="oplayer1" id="notice${notice.id }" style="display:none; width: 400px;">
			<div class="tbar">
				${notice.title } <a style="margin-left: 350px;" href="javascript:Effect.openwin('notice${notice.id }');">关闭</a>
			</div>
			<div>${notice.content }</div>
		</div>
	</c:forEach>
	<div id="winmask" style="display:none;"></div>

</body>
</html>