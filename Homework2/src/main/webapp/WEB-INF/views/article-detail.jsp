<%-- 
    Document   : article-detail
    Created on : 6 ene 2025, 13:34:32
    Author     : arnau
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Article Details</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css" />
        <script src="<c:url value='/resources/js/jquery-1.11.1.min.js' />"></script>
        <script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

        <!-- FontAwesome -->
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
              rel="stylesheet"
              integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
              crossorigin="anonymous"/>
    </head>
    <body>
        <div class="container mt-5">
            <div class="card bg-dark text-white">
                <img src="${pageContext.request.contextPath}/resources/img/${article.image}" alt="${article.title}" class="img-responsive">
                <div class="card-body">
                    <h3 class="card-title">${article.title}</h3>
                    <p class="card-text">
                        <strong>Author:</strong> ${article.author.name}<br>
                        <strong>Date:</strong> ${article.publishedDate}<br>
                        <strong>Views:</strong> ${article.views}
                    </p>
                    <c:if test="${article.isPrivate}">
                        <span class="badge badge-warning">Private</span>
                    </c:if>
                    <hr>
                    <p>${article.fullText}</p>
                    <h5>Topics:</h5>
                    <ul>
                        <c:forEach var="topic" items="${article.topics}">
                            <li>${topic.name}</li>
                            </c:forEach>
                    </ul>
                    <a href="${pageContext.request.contextPath}/Web/articles" class="btn btn-primary mt-3">Back to Articles</a>
                </div>
            </div>
        </div>
    </body>
</html>
