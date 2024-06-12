<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>personal</title>
</head>
<body>
	<c:forEach var="pvo" items="${requestScope.p_ar}">
		<p>${pvo.name } : ${pvo.email }</p>
	</c:forEach>
	
</body>
</html>