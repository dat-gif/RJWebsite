<%-- 
    Document   : FollowCompanyPage
    Created on : Jan 19, 2022, 5:11:45 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap-4.6.1-dist/css/bootstrap.min.css" media="all" type="text/css" rel="stylesheet">
        <script src="bootstrap-4.6.1-dist/jQuery/jquery-3.6.0.min.js"></script>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Follow Company</title>
    </head>
    <body>
        <header>
            <jsp:include page="component/Header.jsp">  
                <jsp:param name="account" value="${account}"/>
            </jsp:include>
        </header>
        <main class="container">
            <h3 class="text-capitalize display-5 mb-4" style="margin-top: 5rem">Follow companies</h3>
            <div class="row row-cols-4 align-content-center ">
                <c:forEach begin="0" end="7" var="o">
                    <div class="col my-2 p-2">
                        <jsp:include page="component/CardInfo.jsp"/>
                    </div>
                </c:forEach>
            </div>
            <!-- Phan trang -->
            <div class="row justify-content-center mt-4 mb-4">
                <jsp:include page="component/Pagination.jsp"/>
            </div>
        </main>
        <footer> <jsp:include page="component/Footer.jsp"/></footer>
        <script src="bootstrap-4.6.1-dist/js/bootstrap.min.js"></script>
    </body>
</body>
</html>
