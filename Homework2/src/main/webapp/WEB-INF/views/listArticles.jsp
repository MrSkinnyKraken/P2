<%-- 
    Document   : listArticles
    Created on : 30 dic 2024, 17:42:36
    Author     : rpino
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Articles List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
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
                    <!-- Filters -->
                    <form action="${mvc.uri('filter-articles')}" method="GET" class="form-inline">
                        <div class="form-group">
                            <label for="topic">Filter by Topic:</label>
                            <select name="topic" class="form-control">
                                <c:forEach var="topic" items="${topics}">
                                    <option value="${topic}">${topic}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="author">Filter by Author:</label>
                            <select name="author" class="form-control">
                                <c:forEach var="author" items="${authors}">
                                    <option value="${author}">${author}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Apply Filters</button>
                    </form>

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
                                            <p><strong>Author:</strong> ${article.author}</p>
                                            <p>${article.summary}</p>
                                            <p><strong>Date:</strong> ${article.date} | <strong>Views:</strong> ${article.views}</p>
                                            <c:if test="${article.private}">
                                                <span class="label label-warning">Private</span>
                                            </c:if>
                                            <a href="${mvc.uri('article-detail')}?id=${article.id}" class="btn btn-info">Read More</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>

