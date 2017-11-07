<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<title>学生页面</title>
<style type="text/css">
body{
	background-color: #71CABF;
}
.container{
	height: 300px;
	width: 600px;
	position:absolute;
	top:50%;
	left:50%;
	transform:translate(-50%,-50%);
	background-color: #fff;
	border-radius: 20px;/* 圆角 */
}
.top{
	position:relative;
	margin-top:20px;
	margin-left: 450px;
}
.info{
	position:relative;
	margin-top: 100px;
	margin-left: 200px;
}
.btn1{
	margin-left: 20px;
}
</style>
</head>
<body>
	<s:div class="container">
		<s:div class="top">
			<label>欢迎:</label>
			<a href="#">
				<s:text name="name"></s:text>
			</a>
		</s:div>
		<s:div class="info">
			<button class="btn btn-info btn1">查询</button>
			<button class="btn btn-info btn1">申请</button>
		</s:div>
	</s:div>
</body>
</html>