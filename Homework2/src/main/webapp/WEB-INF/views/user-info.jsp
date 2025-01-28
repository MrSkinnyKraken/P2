<%-- 
    Document   : user-info.jsp
    Created on : 27 ene 2025, 18:44:02
    Author     : arnau
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>User Info</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css" />
        <script src="<c:url value='/resources/js/jquery-1.11.1.min.js' />"></script>
        <script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta charset="ISO-8859-1">

        <!-- FontAwesome -->
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
              rel="stylesheet"
              integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
              crossorigin="anonymous"/>

    </head>
    <body class="bg-light">

        <div class="container mt-5">
            <div class="card shadow-sm rounded">
                <div class="card-body">
                    <h1 class="display-5 text-primary mb-4">User Info</h1>

                    <p><strong>ID:</strong> ${user.id}</p>
                    <p><strong>Name:</strong> ${user.name}</p>
                    <p><strong>Email:</strong> ${user.email}</p>

                    <hr class="my-4">
                    <div class="text-center">
                        <a class="btn btn-outline-primary btn-lg" href="${pageContext.request.contextPath}/Web/articles">
                            <i class="bi bi-arrow-left-circle-fill me-2"></i>Return to Main Page
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>