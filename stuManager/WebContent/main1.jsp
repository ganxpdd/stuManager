<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap-table.css">
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/jquery-1.11.1.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-table.js"></script>
<script src="js/bootstrap-table-locale-all.js"></script>
<meta charset="UTF-8">
<title>学生列表</title>
<style type="text/css">
.d1 {
	margin: auto;
	text-align: center;
	height: 50px;
	width: 100%;
}

.d1 div {
	padding: 2px 0 0 0;
}

.form-horizontal .form-group {
	margin-right: 0px;
	margin-left: 0px;
	margin-bottom: 0px;
	border-left: 1px solid black;
	border-right: 1px solid black;
}

.bootstrap-table {
	margin: auto;
	text-align: center;
}

.add {
	border: 1px solid gray;
	height: 50px;
}

#NB {
	float: right;
	margin-right: 50px;
	margin-bottom: 0px;
}

th {
	margin: auto;
	text-align: center;
}

#tr1 {
	color: #fff;
	background-color: #212529;
	border-color: #32383e;
}
</style>

<script type="text/javascript">
	//单个删除
	var id;
	function dl(id) {
		var an = confirm("是否删除?");
		if (an == true) {
			location.href = 'DeleteServlet?id=' + id;
		} else {
			return false;
		}
	};
	//全选
	$(function(){
		$("#checkAll").click(function(){
		$("input").prop("checked",this.checked);
			
		});
	});
	
	//批量删除
	/* var id[];
	function dlAll(id[]){
		var an = confirm("是否删除?");
		if (an == true) {
			location.href = 'DeleteServlet?ID[]=' + id[];
		} else {
			return false;
		}
	} */
	
	function test(){
		location.href="selectServlet?pageNum=0&pageSize=5";
	}
	//上下页
	function next(pageNum, pageSize){
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
	  	$("#searchForm").submit();
	}
	
</script>
</head>
<body>
	<c:if test="${deleteMassage==true}">
		<script type="text/javascript">
		alert("删除成功！");
	</script>
	</c:if>
	<div class="d1">
		<div class="row">
			<form id="searchForm" action="selectServlet" method="post"
				class="selectform form-horizontal">
				<input type="hidden" id="pageNum" name="pageNum" value="0">
				<input type="hidden" id="pageSize" name="pageSize" value="5">
				<div class="form-group col-md-2">
					<label class="col-md-5">学号：</label>
					<div class="col-md-5">
						<input type="text" id="numb" name="Snumb" class="form-control"
							value="${Numb}" placeholder="请输入查询学号" size="9"> <span></span>
					</div>
				</div>
				<div class="form-group col-md-2">
					<label class="col-md-4">姓名：</label>
					<div class="col-md-5">
						<input type="text" name="Sname" class="form-control"
							value="${Sname}" placeholder="请输入查询姓名" size="9">
					</div>
				</div>
				<div class="form-group col-md-3">
					<label class="col-md-3">年龄：</label>
					<div class="col-md-4">
						<input type="text" name="MinAge" size="4" class="form-control"
							value="${MinAge}" placeholder="最小年龄">
					</div>
					<div class="col-md-1">—</div>
					<div class="col-md-4">
						<input type="text" name="MaxAge" class="form-control" size="4"
							value="${MaxAge}" placeholder="最大年龄">
					</div>
				</div>
				<div class="form-group col-md-3 ">
					<label class="col-md-4 ">性别：</label>
					<div class="col-md-5 ">
						<label><input type="radio" name="Ssex" value="男"
							${Ssex=='男' ? 'checked="checked"':''}>男 </label> <label>
							<input type="radio" name="Ssex" value="女"
							${Ssex=='女' ? 'checked="checked"':''}>女
						</label>
					</div>
				</div>
				<div class="form-group col-md-2">
					<input type="submit" value="查找" class="form-control">
				</div>
			</form>
		</div>
	</div>
	<div class="add col-md-12">
		<ul class="nav nav-tabs" style="float: left;">
			<li><a href="#" target="iframe_a" onclick="dlAll('${id }')"> <span
					style="font-size: x-small;">删除</span></a></li>
			<li><a href="add.jsp" target="iframe"> <span
					style="font-size: x-small;">添加</span></a></li>
		</ul>
		<ul id="NB">
			<li>总学生数：${pageCount}</li>
			<li>当前页数：${pageNum/5+1}</li>

		</ul>
	</div>
	<div class="bootstrap-table">
		<table border="1" width="80%"
			class="table table-striped table-bordered table-hover table-condensed thead-dark">
			<tr id="tr1">
				<td><input type="checkbox" id="checkAll"></td>
				<th>序号</th>
				<th>学号</th>
				<th>名字</th>
				<th>年龄</th>
				<th>性别</th>
				<th>操作</th>
			</tr>
			<c:set var="a" value="${pageNum}" />
			<c:forEach items="${stuList}" var="row">
				<c:set var="b" scope="session" value="${a=a+1}" />
				<c:set var="id" value="${row.id}" />
				<c:set var="stu_numb" value="${row.stu_numb}" />
				<c:set var="stu_name" value="${row.stu_name}" />
				<c:set var="stu_age" value="${row.stu_age}" />
				<c:set var="stu_sex" value="${row.stu_sex}" />
				<tr>
					<td><input type="checkbox"></td>
					<td><c:out value="${b}" /> <input type="hidden" value="${id}"></td>
					<td><c:out value="${stu_numb}" /></td>
					<td><c:out value="${stu_name}" /></td>
					<td><c:out value="${stu_age}" /></td>
					<td><c:out value="${stu_sex}" /></td>
					<td><a href="#" onclick="dl('${id }')">删除</a>&nbsp;&nbsp;&nbsp;
						<a href="UpdateServlet?id=${id}">修改</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4">${pageNum}<c:if test="${pageNum>0}">
						<a href="javascript:voids(0);" onclick="next(${pageNum-5},5)">[上一页]</a>
					</c:if> <c:if test="${pageNum==0}">
               				             [上一页]
                    </c:if></td>
				<td colspan="3">${pageCount}<c:if test="${pageNum<pageCount-5}">
						<a href="javascript:void(0);" onclick="next(${pageNum+5}, 5);">[下一页]</a>
					</c:if> <c:if test="${pageNum>=pageCount-5}">
                         		   [下一页]
                 </c:if></td>
			</tr>
		</table>
	</div>
</body>
</html>