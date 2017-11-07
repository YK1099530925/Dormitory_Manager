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
<title>管理员登录页面</title>

<style>
body {
	background-color: #71CABF;
}

.container {
	width: 300px;
	height: 250px;
	background-color: #fff;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	border-radius: 20px;
}
.btnmargin{
	margin-top:5px;
	margin-left: 25px;
}
.tab{
	margin-left: 50px;
}
</style>

</head>
<body>
	<s:div class="container">
		<!-- 在s:form中添加theme="simple"，当s:textfield标签中的label没有时，去除label占的位置 -->
		<s:form action="Dormitory-managerCheckLogin" method="post"
			theme="simple">

			<table class="tab">
				<caption class="text-center">
					<h4>用户登录</h4>
				</caption>
				<tr>
					<td><label>用户名</label> <s:textfield name="managerInfo.manager"
							label="管理员" class="form-control"></s:textfield></td>
				</tr>
				<tr>
					<td><label>密码</label> <s:password name="managerInfo.password"
							class="form-control"></s:password></td>
				</tr>
				<tr>
					<td><s:submit class="btn btn-info btnmargin" value="提交"></s:submit> 
					<s:reset class="btn btn-info btnmargin" value="重置"></s:reset></td>
				</tr>
			</table>
		</s:form>
	</s:div>
</body>
</html>