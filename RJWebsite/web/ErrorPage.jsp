<%-- 
    Document   : ErrorPage
    Created on : Feb 4, 2022, 7:07:58 PM
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
        <title>JSP Page</title>
    </head>
    <body>
        <!-- Header.jsp -->
        <header><jsp:include page="component/Header.jsp"/></header>
        <!-- Main -->
        <main style="min-height: 100vh" class="d-flex justify-content-center align-content-center">
            <div class="d-flex justify-content-center align-items-center" id="main">
                <h1 class="mr-3 pr-3 align-top border-right inline-block align-content-start">${errorCode}</h1>
                <div class="inline-block align-middle">
                    <div style="max-width: 40rem">
                    <h2 class="font-weight-normal lead" id="desc">${errorMesg}</h2>
                        <p class="font-weight-normal lead" id="desc">
                            <code>${systemErrorMesg}</code></p>
                    </div>
                </div>
            </div>
        </main>
        <!-- Footer.jsp -->
        <footer> <jsp:include page="component/Footer.jsp"/></footer>

    </body>
</html>
