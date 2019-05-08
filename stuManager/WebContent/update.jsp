<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap-table.css">
<link rel="stylesheet" href="css/bootstrap.css">
<script type="text/javascript">
	$.validator.setDefaults({
		submitHandler : function(form) {
			alert("正在提交....."), form.submit()
		}
	});
	$().ready(function() {
		$(".updateform").validate({
			//输入信息成功显示“OK!”
			success : function(label) {
				label.addClass("valid").text("Ok!");
			},
			errorPlacement : function(error, element) {
				error.appendTo(element.parent().next());
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
				stu_numb : {
					required : true,
					minlength : 5,
					digits : true,
					remote : {
						type:"POST",
						url:"numbVerifyAction",             //servlet
					    data:{
						      stu_numb:function(){return $("#numb").val();} ,
							  id:function(){return $("#ID").val();}
						  } 
					}
				}
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
				stu_numb : {
					required : "请输入学号",
					minlength : "学号不能少于5位数",
					digits : "学号必须是数字",
					remote:"学号已存在"
				}
			}
		})
	});
</script>


<style type="text/css">
.invalid { /*错误信息颜色 */
	color: red;
}

.valid {
	color: green;
}
span{
	font-size: small;
}
</style>
<title>修改信息</title>
</head>
<body>
	<%
		if ("update_error".equals(request.getParameter("error"))) {
	%>
	<script type="text/javascript">
		alert("该学生已存在，修改失败！");
	</script>
	<%
		} else {
	%>
	<%
		}
	%>
	<form action="UpdateServlet" method="post" class="updateform form-horizontal">

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<h1>修改信息</h1>
				<input type="hidden" name="id" id ="ID" value="${id}">
			</div>
		</div>
		<div class="form-group ">
			<label for="name" class="col-sm-2 control-label">名字:</label>
			<div class="col-sm-8">
				<input type="text" id="name" name="stu_name" class="form-control" value="${stu_name}" />
			</div>
			<span></span>
		</div>
		<div class="form-group ">
			<label for="age" class="col-sm-2 control-label">年龄:</label>
			<div class="col-sm-8">
				<input type="text" name="stu_age" id="age" class="form-control" value="${stu_age}" />
			</div>
			<span></span>
		</div>
		<div class="form-group ">
			<label for="sex" class="col-sm-2 control-label">性别:</label>
			<div class="col-sm-8">
				<label><input type="radio" name="stu_sex" value="男" id="sex"
					${stu_sex=='男' ? 'checked="checked"':''}>男</label> 
				<label>
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
					value="${stu_numb}">
			</div>
			<span></span>
			<c:if test="${numbError!=''}">
					<span style="color: red ;font-size: small;" >${numbError}</span>
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