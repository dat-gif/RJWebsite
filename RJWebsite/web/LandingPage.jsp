<%-- 
    Document   : LandingPage
    Created on : Jan 9, 2022, 5:20:23 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap-4.6.1-dist/css/bootstrap.min.css" media="all" type="text/css" rel="stylesheet">
        <script src="bootstrap-4.6.1-dist/jQuery/jquery-3.6.0.min.js"></script>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <header> 
            <jsp:include page="component/Header.jsp"/>
            <div
                class="text-center bg-image"
                style="
                background-image: url('https://mdbcdn.b-cdn.net/img/new/slides/041.webp');
                background-size: cover;
                "
                >
                <div class="mask" style="background-color: rgba(0, 0, 0, 0.6); height: 40rem; ">
                    <div class="d-flex justify-content-center align-items-center h-100">
                        <div class="text-white">
                            <h1 class="mb-3"> Thousand of IT job are waiting for you</h1>
                            <h4 class="mb-3">Create profile and join us now</h4>
                            <a class="btn btn-md btn-primary" href="#!" role="button"
                               >Create profile</a
                            >
                        </div>
                    </div>
                </div>
            </div>
        </header>


        <main class="container mt-5">
            <h2 class="text-center text-capitalize">new recruitment</h2>
            <div class="row row-cols-4 align-content-center">
                <div class="col p-1 mt-1">
                    <jsp:include page="component/CardInfo.jsp"/>
                </div>
                <div class="col p-1  mt-1">
                    <jsp:include page="component/CardInfo.jsp"/>
                </div>
                <div class="col p-1  mt-1">
                    <jsp:include page="component/CardInfo.jsp"/>
                </div>
                <div class="col p-1  mt-1">
                    <jsp:include page="component/CardInfo.jsp"/>
                </div>

            </div>
            <div class="row justify-content-center mt-4 mb-4">
                <button type="button" class="btn btn-primary">See More >></button>
            </div>
        </main>

        <jsp:include page="component/Footer.jsp"/>
        <script src="bootstrap-4.6.1-dist/js/bootstrap.min.js"></script>
    </body>
</html>
