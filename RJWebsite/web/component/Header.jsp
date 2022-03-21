<%-- 
    Document   : Hearder
    Created on : Jan 9, 2022, 4:45:47 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <!--        <meta charset="utf-8">
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <link href="bootstrap-4.6.1-dist/css/bootstrap.min.css" media="all" type="text/css" rel="stylesheet">
                <script src="bootstrap-4.6.1-dist/jQuery/jquery-3.6.0.min.js"></script>
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1">-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Header</title>
    </head>
    <body>
        <header class="position-fixed w-100 shadow-sm" style="position: fixed; left: 0; top: 0; z-index: 3">
            <nav class="navbar navbar-expand-lg navbar-light bg-light pl-5">
                <a class="navbar-brand" href="landingpage">R & IT J </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ml-auto align-items-center pr-5">
                        <!--                        <li class="nav-item ml-3">
                                                    <a class="nav-link" href="RecruitmentDashBoard">I'm Recruiter</a>
                                                </li>-->
                        <li class="nav-item dropdown ml-3">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-expanded="false">
                                About Job
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item small" href="seachingjob">Search Job</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item small" href="candidateapplyjobpage">Apply Job</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown ml-3">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-expanded="false">
                                About Company
                            </a>
                            <div class="dropdown-menu ml-3" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item small" href="seachingcompany">Search Company</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item small" href="companyfollowingPage">Following Company</a>
                            </div>
                        </li>

                        <c:if test="${empty param.account && param.role=='recruiter'}">
                            <li class="nav-item ml-3">
                                <a class="nav-link" href="#">Recruitment Posted</a>
                            </li>
                        </c:if>
                        <c:if test="${empty param.account && param.role=='recruiter'}">
                            <li class="nav-item ml-3">
                                <a class="nav-link" href="#">Searching Candidate</a>
                            </li>
                        </c:if>
                        <c:if test="${empty param.account && param.role=='recruiter'}">
                            <li class="nav-item ml-3">
                                <a class="nav-link" href="#">Company Profile</a>
                            </li>
                        </c:if>
                        <li class="nav-item ml-3">
                            <a class="nav-link" href="candidateprofilecontroller"> <i class="fa fa-user-circle-o" aria-hidden="true"></i> My Profile</a>
                        </li>

                        <c:if test="${empty param.account}">
                            <li class="nav-item ml-3">
                                <a type="button" class="btn btn-primary" href="login" >Login</a>
                            </li>
                        </c:if>
                        <c:if test="${not empty param.account}">
                            <li class="nav-item ml-3">
                                <a type="button" class="btn btn-link" href="logout" >Logout</a>
                            </li>
                        </c:if>
                        <c:if test="${empty param.account}">
                            <li class="nav-item ml-3">
                                <a type="button" class="btn btn-outline-primary" href="register">Register</a>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </nav>
            <!--        <script src="bootstrap-4.6.1-dist/js/bootstrap.min.js"></script>-->
        </header>
    </body>
</html>
