<%-- 
    Document   : LandingPage
    Created on : Jan 9, 2022, 5:20:23 PM
    Author     : Admin
--%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap-4.6.1-dist/css/bootstrap.min.css" media="all" type="text/css" rel="stylesheet">
        <script src="bootstrap-4.6.1-dist/jQuery/jquery-3.6.0.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Welcome</title>
    </head>
    <body>
        <header> 
            <jsp:include page="component/Header.jsp">  
                <jsp:param name="account" value="${account}"/>
            </jsp:include>

            <div
                class="text-center bg-image"
                style="
                background-image: url('asset/1067-1903x579.jpg');
                background-size: cover;
                "
                >
                <div class="mask" style="background-color: rgba(0, 0, 0, 0.3); height: 40rem; ">
                    <div class="d-flex justify-content-center align-items-center h-100">
                        <div class="text-white">
                            <h1 class="mb-3"> Thousand of IT job are waiting for you</h1>
                            <h4 class="mb-3">Create profile and join us now</h4>
                            <a class="btn btn-md btn-primary" href="register" role="button"
                               >Create profile</a
                            >


                            <hr class="my-3 mt-4" style="height:0.5px;border-width:0.5;color:white;background-color:white">
                            <form action="seachingjob" method="POST" class="d-flex">
                                <input placeholder="Quick search job...." type="search" name="txtSearch" value="${txtSearch}" class="form-control  rounded border-secondary" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
                                <button type="submit" class="btn btn-primary mx-3 d-inline ">search</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </header>


        <main class="container mt-5">
            <h2 class="text-center text-capitalize">new recruitment</h2>

            <div class="row row-cols-4 align-content-center">
                <c:forEach items="${listJob}" var="job"> 
                    <div class="col p-1 mt-1">
                        <% request.setCharacterEncoding("utf-8");%>
                        <jsp:include page="component/CardInfo.jsp">
                            <jsp:param name="title1" value="${job.getTitle()}"/>
                            <jsp:param name="title2" value="${job.recruiter.getName()}"/>
                            <jsp:param name="arraySkill" value="${job.skillListName}"/>
                            <jsp:param name="text2" value="${job.salaryRange}"/>
                            <jsp:param name="img" value="${job.recruiter.getAvatar()}"/>
                            <jsp:param name="link" value="jobdetail?jobId=${job.getjId()}"/>
                        </jsp:include>
                    </div>
                </c:forEach>  


            </div>
            <div class="row justify-content-center mt-4 mb-4">
                <a type="button" class="btn btn-primary" href="seachingjob">See More >></a>
            </div>
        </main>

        <jsp:include page="component/Footer.jsp"/>
        <script src="bootstrap-4.6.1-dist/js/bootstrap.min.js"></script>
    </body>
</html>
