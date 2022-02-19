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
        <title>Header</title>
    </head>
    <body>
        <header class="position-fixed w-100" style="position: fixed; left: 0; top: 0; z-index: 1">
            <nav class="navbar navbar-expand-lg navbar-light bg-light pl-5">
                <a class="navbar-brand" href="landingpage">R & IT J </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ml-auto align-items-center pr-5">
                        <li class="nav-item ml-3">
                            <a class="nav-link" href="#">My Profile</a>
                        </li>
                        <li class="nav-item ml-3">
                            <a class="nav-link" href="RecruiterDashBoard">I'm Recruiter</a>
                        </li>
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

                        <li class="nav-item ml-3">
                            <a class="nav-link" href="#">Blog</a>
                        </li>
                        <li class="nav-item ml-3">
                            <a class="nav-link" href="#">Recruitment Posted</a>
                        </li>
                        <li class="nav-item ml-3">
                            <a class="nav-link" href="#">Searching Candidate</a>
                        </li>
                        <li class="nav-item ml-3">
                            <a class="nav-link" href="#">Company Profile</a>
                        </li>
                        <li class="nav-item ml-3 ">
                            <svg xmlns="http://www.w3.org/2000/svg" width=25 height="25" fill="currentColor" class="bi bi-person-circle"viewBox="0 0 16 16"  >
                            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                            <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                            </svg>
                        </li>
                        <li class="nav-item ml-3">
                            <a type="button" class="btn btn-primary" href="login" >Login</a>
                        </li>
                        <li class="nav-item ml-3">
                            <a type="button" class="btn btn-primary" href="logout" >Logout</a>
                        </li>
                        <li class="nav-item ml-3">
                            <a type="button" class="btn btn-outline-primary" href="register">Register</a>
                        </li>
                    </ul>
                </div>
            </nav>
            <!--        <script src="bootstrap-4.6.1-dist/js/bootstrap.min.js"></script>-->
        </header>
    </body>
</html>
