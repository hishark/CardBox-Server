<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align="center">
		<h4>用户列表</h4>
		<table cellspacing="0">
			<thead>
				<tr>
					<th>账号</th>
					<th>密码</th>
				</tr>
			</thead>
			<tbody>
				<!-- items从Controller的mv传出的cs取值  起个别名为c-->
				<c:forEach items="${alluser}" var="c" varStatus="st">
					<tr>
						<td>${c.user_account}</td>
						<td>${c.user_password}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>