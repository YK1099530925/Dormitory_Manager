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
<title>添加学生信息</title>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		//当输入学号的文本框改变时，查询库中是否存在此学号，存在则表示不能使用此学号
		$(":input[name=S_No]").change(function() {
			var snoVal = $(this).val();//得到输入的学号
			snoVal = $.trim(snoVal);//去掉空格
			var $this = $(this);//将DOM对象转换为jQuery对象
			if (snoVal != "") {
				//把当前节点后面的所有兄弟节点删除
				$this.nextAll("font").remove();
				var url = "Dormitory-validateSno";
				$.post(url, {
					"s_no" : snoVal
				}, function(data) {
					if (data == "1") {
						$this.after("<font color='green'>S_No可用</font>");
					} else if (data == "0") {
						$this.after("<font color='red'>S_No不可用</font>");
					} else {
						alert("服务器错误");
					}
				});
			} else {
				alert("S_No不能为空");
				this.focus();
			}
			return false;
		});
	})
</script>
<style type="text/css">
body {
	background-color: #71CABF;
}

.container {
	height: 300px;
	width: 600px;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background-color: #fff;
}

.passOne {
	margin-top: 10px;
}

.passTwo {
	margin-top: 50px;
	margin-left: 50px;
}
.subPositon{
	position:relative;
	left:40%;
}
</style>
</head>
<body>
	<s:form action="Dormitory-input" method="post" theme="simple">
		<s:div class="container">

			<s:div class="form-group passOne">
				<s:div class="col-md-4">
					<label>Building</label>&nbsp;&nbsp;
					<s:select list="#request.Buildings" listKey="id" listValue="B_No"
						name="building.id" id="bid">
					</s:select>
				</s:div>
				<s:div class="col-md-4">
					<label>Floor</label>&nbsp;&nbsp;
					<s:select list="#request.Floors" listKey="id" listValue="F_No"
						name="floor.id" id="fid">
					</s:select>
				</s:div>
				<s:div class="col-md-4">
					<label>Dorm</label>&nbsp;&nbsp;
					<s:select list="#request.Dorms" listKey="id" listValue="D_No"
						name="dorm.id" id="did">
					</s:select>
				</s:div>
			</s:div>
			<s:div class="passTwo">
				<s:if test="id != null">
					<s:div class="form-group col-md-12">
						<s:div class="col-md-2">
							<label>S_No</label>
						</s:div>
						<s:div class="col-md-5">
							<s:textfield class="form-control" name="S_No" disabled="true"></s:textfield>
						</s:div>
					</s:div>
					<s:div class="form-group col-md-12">
						<s:div class="col-md-2">
							<label>name</label>
						</s:div>
						<s:div class="col-md-5">
							<s:textfield class="form-control" name="name" disabled="true"></s:textfield>
							<s:hidden name="id"></s:hidden>
						</s:div>
					</s:div>
				</s:if>
				<s:else>
					<s:div class="form-group col-md-12">
						<s:div class="col-md-2">
							<label>S_No</label>
						</s:div>
						<s:div class="col-md-5">
							<s:textfield class="form-control" name="S_No"></s:textfield>
						</s:div>
					</s:div>
					<s:div class="form-group col-md-12">
						<s:div class="col-md-2">
							<label>name</label>
						</s:div>
						<s:div class="col-md-5">
							<s:textfield class="form-control" name="name"></s:textfield>
						</s:div>
					</s:div>
				</s:else>
				<s:div class="form-group col-md-12">
					<s:div class="col-md-2">
						<label>sex</label>&nbsp;&nbsp;
					</s:div>
					<s:div class="col-md-5">
						<s:textfield class="form-control" name="sex"></s:textfield>
					</s:div>
				</s:div>
				<s:div class="form-group col-md-12">
					<s:div class="col-md-2">
						<label>birth</label>
					</s:div>
					<s:div class="col-md-5">
						<s:textfield class="form-control" name="birth"></s:textfield>
					</s:div>
				</s:div>
			</s:div>
			<s:div class="subPositon">
				<s:submit class="btn btn-default" value="提交"></s:submit>
			</s:div>
		</s:div>
	</s:form>
</body>
</html>