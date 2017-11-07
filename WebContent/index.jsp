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
<title>登录界面</title>
<style type="text/css">
body{
	background-color: #71CABF;
}
.container{
	height: 300px;
	width: 300px;
	position:absolute;
	top:50%;
	left:50%;
	transform:translate(-50%,-50%);
	background-color: #fff;
	border-radius: 20px;/* 圆角 */
}
.ms{
	position:relative;
	left:40%;
	margin-top: 50px;
}
</style>
</head>
<body>
	<s:div class="container">
		<h3 class="text-center">寝室管理系统</h3>
		<s:div class="ms">
			<a class="btn btn-primary" href="Dormitory-managerLogin">管理端</a>
		</s:div>
		<s:div class="ms">
			<a class="btn btn-primary" href="Student-studentLogin">学生端</a>
		</s:div>
	</s:div>
</body>
</html>