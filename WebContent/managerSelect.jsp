<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<title>管理员查询页面</title>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$(".s").click(
						function() {
							var B_No = $("#bid :selected").val();
							var F_No = $("#fid :selected").val();
							var D_No = $("#did :selected").val();
							url = this.href;
							$.post(url,
									{
										'B_No' : B_No,
										'F_No' : F_No,
										'D_No' : D_No
											},
											function(data) {
												if (data != null) {
													//先清空元素
													$("#Info  tr:not(:first)").html("");
													var students = eval(data);
													$.each(students,function(index) {
														var $tr = $("<tr />");
														var id = students[index].id;
														var S_No = students[index].s_No;
														var name = students[index].name;
														var sex = students[index].sex;
														var birth = students[index].birth;

														var $td = "";
														$td = $("<td>"+ id+ "</td>");
														$tr.append($td);
														$td = $("<td>"+ S_No+ "</td>");
														$tr	.append($td);
														$td = $("<td>"+ name+ "</td>");
														$tr.append($td);
														$td = $("<td>"+ sex+ "</td>");
														$tr.append($td);
														$td = $("<td>"+ (birth.year % 100)+ "-"
																	+ (birth.month + 1)+ "-"
																	+ birth.date+ "</td>");
														$tr.append($td);
														$td = $("<td>"+ B_No+ "</td>");
														$tr.append($td);
														$td = $("<td>"+ F_No+ "</td>");
														$tr.append($td);
														$td = $("<td>"+ D_No+ "</td>");
														$tr.append($td);
														$td = $("<td><a href = 'Dormitory-insert?id="+id+"'>编辑</a></td>");
														$tr.append($td);
														$td = $("<td><a class='delete' href = 'Dormitory-delete?id="+id+"'>删除</a><input type='hidden' value='"+name+"'/></td>");
														$tr.append($td);
														$("#Info").append($tr);
													});
												} else {
													alert("没有");
												}
											}, "json");

							/* for(var i = 0; i < 3; i++){
								var $tr = $("<tr />");
								for(var j = 0; j < 8; j++){
									var $td = $("<td>1</td>");
									$tr.append($td);
								}
								$("#Info").append($tr);
							} */
							return false;
						});
		//如果直接使用$(".sdelete").click不行,所以需要动态绑定
		$(document).on("click", ".delete", function() {
			var name = $(this).next(":input").val();
			var flag = confirm("确定删除 "+name+" 的信息吗？");
			if(flag){
				var $tr = $(this).parent().parent();
				var url = this.href;
				$.post(url,{},function(data){
					if(data == "1"){
						alert("删除成功");
						$tr.remove();
					}else{
						alert("删除失败");
					}
				});
			}
		    return false;
		})
	})
	
</script>

<style type="text/css">
body{
	background-color: #71CABF;
}
.container{
	height: 330px;
	width: 600px;
	position:absolute;
	top:50%;
	left:50%;
	transform:translate(-50%,-50%);
	background-color: #fff;
	border-radius: 20px;/* 圆角 */
}
.passOne{
	margin-top: 20px;
}
.passTwo{
	margin-top: 10px;
}
.passThree{
	margin-left: 200px;
	
}
</style>
</head>
<body>
	<s:div class="container">
		<s:div class="form-group passOne">
			<s:div class="col-md-4">
				<label>Building</label>&nbsp;&nbsp;
				<s:select list="#request.Buildings" listKey="id"
						listValue="B_No" name="building.id" id="bid">
				</s:select>
			</s:div>
			<s:div class="col-md-3">
				<label>Floor</label>&nbsp;&nbsp;
				<s:select list="#request.Floors" listKey="id"
						listValue="F_No" name="floor.id" id="fid">
					</s:select>
			</s:div>
			<s:div class="col-md-3">
				<label>Dorm</label>&nbsp;&nbsp;
				<s:select list="#request.Dorms" listKey="id"
						listValue="D_No" name="dorm.id" id="did">
					</s:select>
			</s:div>
				<s:div>
					<a href="Dormitory-slecetInfo" class="btn btn-default s">查询</a>
				</s:div>
		</s:div>
		<s:div class="passTwo" id="passtwo">		
			<table class="table table-bordered" border="1" id="Info" border="1" cellpadding="10" cellspacing="0">
					<tr>
						<td>S_Id</td>
						<td>S_No</td>
						<td>name</td>
						<td>sex</td>
						<td>birth</td>
						<td>B_Id</td>
						<td>F_Id</td>
						<td>D_Id</td>
						<td>Edit</td>
						<td>Delete</td>
					</tr>
			</table>
		</s:div>
		<s:div class="passThree">
			<s:set name="page" value="#request.pages"></s:set>
			<s:if test="#page.hasPre">
				<a class="btn btn-info" href="Dormitory-slecetInfo?pageNow=<s:property value="#page.pageNow - 1" />">上一页</a>
			</s:if>
			<s:else>
				<a class="btn btn-danger">上一页</a>
			</s:else>
			<s:if test="#page.hasNext">
				<a class="btn btn-info" href="Dormitory-slecetInfo?pageNow=<s:property value="#page.pageNow + 1" />">下一页</a>
			</s:if>
			<s:else>
				<a class="btn btn-danger s" href="Dormitory-slecetInfo?pageNow=2">下一页</a>
			</s:else>
		</s:div>
	</s:div>
</body>
</html>