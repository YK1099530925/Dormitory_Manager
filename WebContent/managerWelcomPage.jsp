<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<title>管理员页面</title>
<style type="text/css">
body {
	background-color: #71CABF;
}

.container {
	height: 200px;
	width: 400px;
	background-color:#fff;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	border-radius: 20px;/* 圆角 */
}
.h3{
	margin-left: 75px;
}
.btn-pro{
	margin-top: 50px;
	margin-left: 70px;
}
</style>
</head>
<body>

	<s:div class="container">
		<table>
			<tr>
				<td>
					<s:div class="h3">
						<h3>寝室管理系统-管理员</h3>
					</s:div>
				</td>
			</tr>
			<tr>
				<td><a class="btn btn-info btn-pro" href="Dormitory-managerOper">查看信息</a> 
				<a class="btn btn-info btn-pro" href="Dormitory-insert">添加信息</a></td>
			</tr>
			<tr></tr>
		</table>
	</s:div>


</body>
</html>