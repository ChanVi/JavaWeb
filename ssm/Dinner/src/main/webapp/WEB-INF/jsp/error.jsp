<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error page</title>
</head>
<body>
	<h3><font color="red">${msg }</font></h3>
	<br>
	<a style="text-decoration: none" href="javascript:history.go(-1)">
			<input type="button" name="cancel" value="取消">
	</a>
</body>
</html>