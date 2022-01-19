<%-- 
    Document   : SearchingCompanyPage
    Created on : Jan 19, 2022, 2:59:08 PM
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
        <title>Searching Job</title>
    </head>
    <body>
        <header> 
            <jsp:include page="component/Header.jsp"/>

        </header>

        <main class="container my-3">
            <!-- Searching -->
            <div class="row row-cols-2 ">
                <div class="col align-self-center"> 
                    <h3 class="text-capitalize display-4 mb-4">Searching for IT Company...</h3>
                    <div class="input-group">
                        <input type="search" class="form-control rounded border-secondary" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
                        <button type="button" class="btn btn-outline-primary mx-3">search</button>
                    </div>
                    <div class="d-flex mt-3 ">
                        <select class="form-select mr-2 border-secondary rounded w-25 form-control ">
                            <option value="1">One</option>
                            <option value="2">Two</option>
                            <option value="3">Three</option>
                        </select>    
                    </div>

                </div>
                <div class="col p-0 align-self-center">
                    <img src="https://picsum.photos/500/300" class="img-fluid w-100 " alt="alt"/>
                </div>
            </div>
            <hr class="my-4"/>
            <!-- Result -->
            <div class="row row-cols-4 align-content-center ">
                <c:forEach begin="0" end="12" var="0">
                    <div class="col p-2 mt-1">
                        <jsp:include page="component/CardInfo.jsp"/>
                    </div>
                </c:forEach>


            </div>
            <div class="row justify-content-center mt-4 mb-4">
                <jsp:include page="component/Pagination.jsp"/>
            </div>
        </main>

        <jsp:include page="component/Footer.jsp"/>
        <script src="bootstrap-4.6.1-dist/js/bootstrap.min.js"></script>
    </body>
</html>

