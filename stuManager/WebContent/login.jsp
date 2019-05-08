<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bootstrap-table.css">
<style type="text/css">
body {
	background-image: url("img/bg2.jpg");
	margin: auto;
	padding-top: 150px; 
}

.div1 {
	align-content: center;
	border: 2px;
	height: 200px;
	width: 380px;
	margin: auto;
	padding-top: 20px;
	opacity: 0.7;
	box-shadow: darkgrey 0px 0px 20px 10px;
}

</style>
</head>
<body>
	<%if("login_error".equals(request.getParameter("error"))){ %>
	<script type="text/javascript">
		 alert("非法入侵！");
	</script>		
	<%}else{%>
	<% } %>
	<%
		if("login_error".equals(request.getParameter("message"))){ %>
		<script type="text/javascript">
   				 alert("用户名或者密码错误！");
		</script>		
		<%}else{%>
		<% }%>
	<div class="div1">
		<form action="loginServlet" method="post" class="form-horizontal">
			<div class="form-group">
				<label for="name" class="col-sm-4 control-label">账号</label>
				<div class="col-sm-6"><input type="text" id="name" name="username" class="form-control" value="${username}"></div>
			</div>
			<div class="form-group">
				<label for="pwd" class="col-sm-4 control-label">账号</label>
				<div class="col-sm-6"><input type="password" id="pwd" name="password" class="form-control" value="${password}"></div>
			</div>
			<span style="color: red;">
			</span>
			<div class="form-group">
				<div class=" col-sm-offset-1 col-sm-5 "><a href="register.jsp"><img src="img/register.jpg"></a></div>
				<div class=" col-sm-offset-1 col-sm-5"><input type="submit" value=""
					style="background-image: url(img/login2.jpg); width: 100px; height: 34px; border: 0px;"></div>
			</div>


		</form>
	</div>
</body>
</html>