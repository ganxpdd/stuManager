<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.error{
	color:red;
}
</style>

<meta charset="UTF-8">
<title>管理员注册</title>
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript">
		$.validator.setDefaults({
			submitHandler: function(form){
				alert("正在提交....."),
				form.submit()
			}
		});
		$().ready(function() {
			$(".cmxform").validate({
				//输入信息成功显示“OK!”
				success: function(label) {
					label.addClass("valid").text("Ok!");
				},
				rules:{
					userName: {
						required: true,
						minlength: 3 
					},
					password: {
						required: true,
						minlength: 5
					},
					confirm_password: {
						required: true,
						minlength: 5,
						equalTo: "#password"
					}
				},
				messages:{
					username: {
						required: "请输入用户名",
						minlength: "用户名必需由三个字母或汉字组成"
					},
					password: {
						required: "请输入密码",
						minlength: "密码长度不能小于 5 个字母"
					},
					confirm_password: {
						required: "请输入密码",
						minlength: "密码长度不能小于5 个字母",
						equalTo: "两次密码输入不一致"
					}
				}
			})
		});
</script>
</head>
<body>
	<form id="register" class="cmxform" action="RegisterServlet" method="post" >
		<table border="1" >
			<tr>
				<td colspan="2"><h2>注册</h2></td>
			</tr>
			<tr>
				<td>用&nbsp&nbsp户&nbsp&nbsp名:</td>
				<td><input type="text" name="userName" id="userName"></td>
			</tr>
			<tr>
				<td>密&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp码:</td>
				<td><input type="password" name="password" id="password"></td>
			</tr>
			<tr>
				<td>确认密码:</td>
				<td><input type="password" name="confirm_password" id="cmxform_password"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="提交" /> <input
					type="reset" value="清空" /></td>
			</tr>
		</table>
	</form>
</body>
</html>