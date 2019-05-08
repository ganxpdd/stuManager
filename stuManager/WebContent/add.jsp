<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style type="text/css">
.invalid { /*错误信息颜色 */
	color: red;
}

.valid {
	color: green;
}

body {
	/* margin: auto;
	text-align: center; */
	margin-top: 100px;
}

span {
	font-size: small;
}
</style>
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap-table.css">
<link rel="stylesheet" href="css/bootstrap.css">
<script type="text/javascript">
	$.validator.setDefaults({
		submitHandler : function(form) {
			alert("正在添加....."), form.submit()
		}
	});
	$().ready(function() {
		$(".addform").validate({
			//输入信息成功显示“OK!”
			success : function(label) {
				label.addClass("valid").text("Ok!");
			},
			errorPlacement : function(error, element) {
				if (element.is(':radio')) {
					var eid = element.attr('name');
					error.appendTo(element.parent().next());
				} else {
					error.appendTo(element.parent().next());
				}
			},
			errorClass : "invalid",
			errorElement : "em",
			rules : {
				stu_name : {
					required : true,
					minlength : 2
				},
				stu_age : {
					required : true,
					max : 200,
					digits : true
				},
				stu_sex : {
					required : true
				},
				stu_numb : {
					required : true,
					minlength : 5,
					digits : true,
					remote : {
						type:"POST",
						url:"numbVerifyAction",             //servlet
					    data:{
						      stu_numb:function(){return $("#numb").val();}
						      } 
						    } 
				},
			},
			messages : {
				stu_name : {
					required : "请输入用户名",
					minlength : "用户名最少由2个字母或汉字组成"
				},
				stu_age : {
					required : "请输入年龄",
					max : "年龄不超过200岁",
					digits : "年龄必须是数字"
				},
				stu_sex : {
					required : "请选择性别"
				},
				stu_numb : {
					required : "请输入学号",
					minlength : "学号不能少于5位数",
					digits : "学号必须是数字",
					remote : "学号已经存在"
				}
			}
		})
	});
</script>

<title>添加</title>
</head>
<body>
	<%
		if ("addstu_error".equals(request.getParameter("error"))) {
	%>
	<script type="text/javascript">
		alert("该学生已存在，添加失败！");
	</script>
	<%
		} else {
	%>
	<%
		}
	%>

	<form action="addServlet" method="post" class="addform form-horizontal">

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<h1>添加</h1>
			</div>
		</div>

		<div class="form-group ">
			<label for="name" class="col-sm-2 control-label">名字:</label>
			<div class="col-sm-8">
				<input type="text" id="name" name="stu_name" class="form-control"
					placeholder="请输入名字" value="${stu_name}" />
			</div>
			<span></span>
		</div>
		<div class="form-group ">
			<label for="age" class="col-sm-2 control-label">年龄:</label>
			<div class="col-sm-8">
				<input type="text" name="stu_age" id="age" class="form-control"
					placeholder="请输入年龄" value="${stu_age}" />
			</div>
			<span></span>
		</div>
		<div class="form-group ">
			<label for="sex" class="col-sm-2 control-label">性别:</label>
			<div class="col-sm-8">
				<label><input type="radio" name="stu_sex" value="男" id="sex"
					${stu_sex=='男' ? 'checked="checked"':''}>男</label> <label>
					<input type="radio" id="sex" name="stu_sex" value="女"
					${stu_sex=='女' ? 'checked="checked"':''}>女
				</label>
			</div>
			<span></span>
		</div>
		<div class="form-group ">
			<label for="numb" class="col-sm-2 control-label">学号:</label>
			<div class="col-sm-8">
				<input type="text" name="stu_numb" id="numb" class="form-control"
					placeholder="请输入学号" value="${stu_numb}">
			</div>
			<c:if test="${numbError!=''}">
				<span style="color: red">${numbError}</span>
			</c:if>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="submit" value="提交"> <input type="reset"
					value="清空" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-5 col-sm-1">
				<a href="#" onClick="javascript :history.back(-1);">返回上一页</a>
			</div>
			<div class="col-sm-1">
				<a href="selectServlet?pageNum=0&pageSize=5">返回主页</a>
			</div>
		</div>
	</form>
</body>
</html>