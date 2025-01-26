<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>LogIn</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css" />
    <script src="<c:url value='/resources/js/jquery-1.11.1.min.js' />"></script>
    <script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

    <!-- FontAwesome -->
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
          rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
          crossorigin="anonymous"/>
</head>
<body>
    <div class="container">
        <div class="col-md-offset-2 col-md-8">
            <h2 class="text-center">LogIn</h2>
            
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">INICIA SESION</h3>
                </div>
                <div class="panel-body">
                    <form action="${mvc.uri('log-in')}" class="form-horizontal" method="POST">                        
                       
                        <!-- Email Field -->
                        <div class="form-group">
                            <label for="email" class="col-md-3 control-label">Email</label>
                            <div class="col-md-9">
                                <input type="text" name="email" value="${user.email}" class="form-control" />
                            </div>
                        </div>
                        
                        <!-- Password Field -->
                        <div class="form-group">
                            <label for="password" class="col-md-3 control-label">Password</label>
                            <div class="col-md-9">
                                <input type="password" name="password" value="${user.password}" class="form-control" />
                            </div>
                        </div>
                        
                        <!-- Buttons -->
                        <div class="form-group text-center">
                            <div class="col-md-12">
                                <input type="submit" value="Submit" class="btn btn-primary" />
                                <a href="${pageContext.request.contextPath}/Web/articles" class="btn btn-primary">Continue without LogIn</a>
                            </div>
                        </div>
                    </form>
                    
                    <!-- Error Messages -->
                    <c:if test="${not empty message}">
                        <div class="alert alert-danger" role="alert">
                            ${message}        
                        </div>
                    </c:if>
                    
                    <!-- Too Many Attempts Modal -->
                    <c:if test="${attempts.hasExceededMaxAttempts()}">
                        <div id="too-many-signup-attempts" class="modal top fade" role="alert" tabindex="-1" data-mdb-backdrop="static" data-mdb-keyboard="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h2 class="modal-title" id="too-many-signup-attempts">Please try again later.</h2>
                                    </div>
                                    <div class="modal-body">
                                        <div class="alert alert-danger" role="alert">
                                            <img class="mb-4" src="<c:url value='/resources/img/Invalid.png' />" alt="" width="134" height="92" />
                                            The maximum number of sign-up attempts has been exceeded!
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <script>
                            $("#too-many-signup-attempts").modal('show');
                        </script>
                    </c:if>
                    
                    <jsp:include page="/WEB-INF/views/layout/alert.jsp" />
                </div>
            </div>
        </div>
    </div>
</body>
</html>
