<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Articles List</title>
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
    <div class="container-fluid">
        <div class="col-md-12">
            <h2 class="text-center">Welcome to the Articles Page</h2>

            <!-- User Options -->
            <div class="user-menu panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">User Options</h3>
                        <div class="row user-options">
                            <div class="col-md-12">
                            <c:choose>
                                <c:when test="${not empty sessionScope.loggedInUser}">
                                    <p>Welcome, ${sessionScope.loggedInUser.name}!</p>
                                    <a href="${pageContext.request.contextPath}/Web/UserInfo" class="menu-item">User Info</a>
                                    <a href="${pageContext.request.contextPath}/Web/Logout" class="menu-item">Log Out</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/Web/LogIn" class="menu-item">Log In</a>
                                    <a href="${pageContext.request.contextPath}/Web/SignUp" class="menu-item">Sign Up</a>
                                </c:otherwise>
                            </c:choose>
                            </div>
                        </div>
                </div>
            </div>

            <!-- Filter Section -->
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Filter Articles</h3>
                </div>
                <div class="panel-body">
                    <form id="filterForm" method="GET" action="${pageContext.request.contextPath}/Web/articles">
                        <div class="row">
                            <!-- Topics Checkbox -->
                            <div class="col-md-6">
                                <div class="filter-dropdown">
                                    <label>Topics</label>
                                    <div class="dropdown-content">
                                        <c:forEach var="topic" items="${topics}">
                                            <label>
                                                <input type="checkbox" name="topic" value="${topic}"> ${topic}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>

                            <!-- Authors Checkbox (Single Selection) -->
                            <div class="col-md-6">
                                <div class="filter-dropdown">
                                    <label>Authors</label>
                                    <div class="dropdown-content">
                                        <c:forEach var="author" items="${authors}">
                                            <label>
                                                <input type="checkbox" name="author" value="${author.name}" class="author-checkbox"> ${author.name}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-md-12 text-center">
                                <button type="submit" class="btn btn-primary">Search</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            
            <!-- Articles List -->
            <div id="articles-list" class="mt-4">
                <c:forEach var="article" items="${articles}">
                    <div class="article-item panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-4">
                                    <img src="${pageContext.request.contextPath}/resources/img/${article.image}" alt="${article.title}" class="img-responsive">
                                </div>
                                <div class="col-md-8">
                                    <h4>${article.title}</h4>
                                    <p><strong>Author:</strong> ${article.author.name}</p>
                                    <p>${article.summary}</p>
                                    <p><strong>Date:</strong> ${article.publishedDate} | <strong>Views:</strong> ${article.views}</p>
                                    <c:if test="${article.isPrivate}">
                                        <span class="label label-warning">Private</span>
                                    </c:if>
                                    <a href="${pageContext.request.contextPath}/Web/Articles/${article.id}" class="btn btn-primary">Read More</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>
