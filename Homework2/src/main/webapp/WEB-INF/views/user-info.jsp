<%-- 
    Document   : user-info.jsp
    Created on : 27 ene 2025, 18:44:02
    Author     : arnau
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>User Info</title>
</head>
<body>
    <h1>User Info</h1>
    <p><strong>ID:</strong> ${user.id}</p>
    <p><strong>Name:</strong> ${user.name}</p>
    <p><strong>Email:</strong> ${user.email}</p>
    <c:if test="${not empty user.lastArticle}">
        <p><strong>Last Article:</strong> <a href="${user.lastArticle}">View Article</a></p>
    </c:if>
    <a href="${pageContext.request.contextPath}/articles">Return to Main Page</a>
</body>
</html>