<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
body {
	margin: auto;
	text-align: center;
	padding-top: 50px;
	padding-bottom: 50px;
}

.head {
	/* background-image: url("img/head.jpg"); */
	background: gray;
	width: 1800px;
	height: 60px;
	margin: auto;
	text-align: center;
}

.body {
	width: 1800px;
	/* background-image: url("img/bg4.jpg"); */
	margin: auto;
	height: 610px;
	text-align: center;
}

#left {
	background: gray;
	width: 200px;
	height: 600px;
	float: left;
	border-top: 2px solid initial;
	border-top-style: inset;
}

#right {
	background: pale;
	width: 1600px;
	height: 610px;
	float: left;
	margin: auto;
	text-align: center;
}

.bottom {
	background-image: url("img/h1.jpg");
	width: 1800px;
	height: 100px;
	margin: auto;
	text-align: center;
	margin-bottom: 0px;
}

#body_ul {
	list-style: none;
}

.bodyli {
	height: 40px;
	border-top: 2px solid initial;
	border-top-style: inset;
	border-bottom: 2px solid initial;
	border-bottom-style: inset;
	color: white;
	text-align: left;
	font-size: 20px;
}

a {
	text-decoration: none;
}
</style>
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap-table.css">
<link rel="stylesheet" href="css/bootstrap.css">
<title>学生管理系统</title>
</head>
<body>

	<div class="head">
		<nav class="navbar " role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href=""
						style="color: white; font-size: xx-large;">学生管理系统</a>
				</div>
				<div class="navbar-left">
					<ul class="nav">
						<!-- <li class="active"><a
							href="selectServlet?pageNum=0&pageSize=5" target="iframe"><span
								style="color: white; font-size: medium;">首页</span></a></li> -->
						<li class="active"><a
							href="ceshi.jsp" target="iframe"><span
								style="color: white; font-size: medium;">首页</span></a></li>
					</ul>
				</div>
				<div class="navbar-right">
					<ul class="nav nav-tabs">
						<li style="color: white; font-size: small;">欢迎 <em>${username}</em>&nbsp;&nbsp;
						</li>
						<li><a href="exitServlet"><span
								style="color: white; font-size: small;">注销</span></a></li>
					</ul>
				</div>
			</div>
		</nav>
	</div>
	<div class="body">
		<div id="left">
			<ul id="body_ul">
				<li class="bodyli"><a href="add.jsp" target="iframe"> <span
						style="color: white;">添加管理</span></a></li>
			</ul>
		</div>
		<!-- <div id="right">
			<iframe src="selectServlet?pageNum=0&pageSize=5" name="iframe"
				width="1596" height="600"></iframe>
		</div> -->
		<div id="right">
			<iframe src="ceshi.jsp" name="iframe"
				width="1596" height="600"></iframe>
		</div>
	</div>
</body>
</html>