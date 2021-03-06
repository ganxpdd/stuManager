<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/bootstrap.css" rel="stylesheet"/>
<link href="css/bootstrap-table.css" rel="stylesheet"/>

<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-table.min.js"></script>
<script src="js/bootstrap-table-locale-all.js"></script>
<script src="https://unpkg.com/tableexport.jquery.plugin/tableExport.min.js"></script>
<script src="https://unpkg.com/bootstrap-table@1.14.2/dist/extensions/export/bootstrap-table-export.min.js"></script>

<meta charset="UTF-8">
<title>Insert title here</title>
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
</style>
</head>
<body>
	<div class="bootstrap-table">
	<input class="btn btn-secondary" type="submit" value="Submit">
	<button id="remove" class="btn btn-danger" disabled>Delete</button>
		<table id="table"  data-toggle="table" data-toolbar=".toolbar" data-locale="zh-CN"  data-search-align="left"
  data-buttons-align="right" >
		</table>
	</div>
<script type="text/javascript">


$("input[type='button']").click(function() {
	$("#table").bootstrapTable("refresh")
});

//bootstrap-table初始化
$("#table").bootstrapTable({
	url : "stuServlet",
	method : 'POST', //请求方式（*）
	toolbar : '#toolbar', //工具按钮用哪个容器
	striped : true, //是否显示行间隔色
	//pagination : true, //是否显示分页（*）
	search :true,
	//showPaginationSwitch : true, //显示切换分页
	//showHeader : true, //显示头部，默认显示
	showExport : true, //显示导出
	//showColumns : true, //是否显示所有的列（选择显示的列）
//showRefresh : true, //是否显示刷新按钮
	sortable : true, //是否启用排序
	pageNumber : 1, //初始化加载第一页，默认第一页,并记录
	pageSize : 5, //每页的记录行数（*）
	pageList : [ 5, 10, 15 ], //可供选择的每页的行数（*）
	strictSearch : true,
	minimumCountColumns : 2, //最少允许的列数
	clickToSelect : true, //是否启用点击选中行
	uniqueId : "id", //每一行的唯一标识，一般为主键列
	//showToggle : true, //是否显示详细视图和列表视图的切换按钮
	columns : [

		{
			field : 'state',
			checkbox : true,
			align : 'center',
			valign : 'middle'
		},
		{
            field: 'no',
            title: '序号',
            sortable: true,
            align: "center",
            width: 40,	
            formatter: function (value, row, index) {
                //获取每页显示的数量
                var pageSize=$('#table').bootstrapTable('getOptions').pageSize;
                //获取当前是第几页
                var pageNumber=$('#table').bootstrapTable('getOptions').pageNumber;
                //返回序号，注意index是从0开始的，所以要加上1
                return pageSize * (pageNumber - 1) + index + 1;
            }
        },
		{
			title : 'ID',
			field : 'id',
			align : 'center',
			valign : 'middle',
			sortable : true
		},
		{
			title : '学号',
			field : 'stu_numb',
			align : 'center',
			valign : 'middle',
			sortable : true
		},
		{
			title : '姓名 ',
			field : 'stu_name',
			sortable : true,
			editable : false,
			align : 'center'
		}, {
			title : '年龄',
			field : 'stu_age',

			sortable : true,
			editable : false,
			align : 'center'
		}, {
			title : '性别',
			field : 'stu_sex',
			sortable : true,
			align : 'center'
		}, {
			title : '操作',
			field : 'id',
			align : 'center',
			formatter:function(value,row,index){  
                var e = '<a href="#" onclick="edit(\''+ row.id + '\')">编辑</a> ';  
                var d = '<a href="#" onclick="del(\''+ row.id +'\')">删除</a> ';  
                     return e+d;  
                 } 
		}

	]
});

var id;
function del(id) {
	var an = confirm("是否删除?");
	if (an == true) {
		location.href = 'DeleteServlet?id=' + id;//修改传id到DeleteServlet
	} else {
		return false;
	}
};
function edit(id) {
	location.href = 'UpdateServlet?id=' + id;//修改传id到UpdateServlet
	
};
</script>
<script type="text/javascript">
var $table = $('#table')
var $remove = $('#remove')

$(function() {
  $table.on('check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table', function () {
    $remove.prop('disabled', !$table.bootstrapTable('getSelections').length)
  })
  $remove.click(function () {
    var ids = $.map($table.bootstrapTable('getSelections'), function (row) {
      return row.id
    })

    $table.bootstrapTable('remove', {
      field: 'id',
      values: ids
    })
    $remove.prop('disabled', true)
  })
})
</script>
</body>
</html>