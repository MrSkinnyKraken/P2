<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Articles List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
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
    <div class="container">
        <div class="col-md-offset-2 col-md-8">
            <h2 class="text-center">Welcome to the Articles Page</h2>
            <div class="panel panel-info">
                <div class="panel-heading">
                    <div class="panel-title">Available Articles</div>
                </div>
                <div class="panel-body">

                    
                    
                    
                    
                    

                    <!-- Articles List -->
                    <div id="articles-list" class="mt-4">
                        <c:forEach var="article" items="${articles}">
                            <div class="article-item panel panel-default">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <img src="${article.image}" alt="${article.title}" class="img-responsive">
                                        </div>
                                        <div class="col-md-8">
                                            <h4>${article.title}</h4>
                                            <p><strong>Author:</strong> ${article.author.name}</p>
                                            <p>${article.summary}</p>
                                            <p><strong>Date:</strong> ${article.publishedDate} | <strong>Views:</strong> ${article.views}</p>
                                            <c:if test="${article.isPrivate}">
                                                <span class="label label-warning">Private</span>
                                            </c:if>
                                            <a href="${pageContext.request.contextPath}/Web/Articles/${article.id}" class="btn btn-info">Read More</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <!-- Pagination Controls -->
                    <div class="text-center mt-4">
                        <c:if test="${pageNumber > 1}">
                            <a href="${mvc.uri('articles')}&page=${pageNumber - 1}" class="btn btn-secondary">Previous</a>
                        </c:if>

                        <c:if test="${pageNumber < totalPages}">
                            <a href="${mvc.uri('articles')}&page=${pageNumber + 1}" class="btn btn-secondary">Next</a>
                        </c:if>
                    </div>

                </div>
            </div>
        </div>
    </div>
</body>
</html>
