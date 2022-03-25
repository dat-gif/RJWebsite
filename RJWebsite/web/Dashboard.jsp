<%-- 
    Document   : Dashboard
    Created on : Jan 18, 2022, 9:44:38 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap-4.6.1-dist/css/bootstrap.min.css" media="all" type="text/css" rel="stylesheet">
        <script src="bootstrap-4.6.1-dist/jQuery/jquery-3.6.0.min.js"></script>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="adminstyle/Dashboard.css">
    </head>
    <body >
        <jsp:include page="component/Adminheader.jsp"/>

        <div class="container-fluid" style="height: 850px;" >
            <div class="row">
                <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse" style="height: 867px;" >
                    <div class="sidebar-sticky pt-3">
                        <ul class="nav flex-column">
                            <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                                <span>Personal</span>
                                <a class="d-flex align-items-center text-muted" href="#" aria-label="Add a new report">
                                    <span data-feather="plus-circle"></span>
                                </a>
                            </h6>
                            <li class="nav-item">
                                <a class="nav-link active" href="Dashboard">
                                    <span data-feather="home"></span>
                                    Dashboard <span class="sr-only">(current)</span>
                                </a>
                            </li>
                        </ul>


                        <ul class="nav flex-column mb-2">
                            <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                                <span>Account</span>
                                <a class="d-flex align-items-center text-muted" href="#" aria-label="Add a new report">
                                    <span data-feather="plus-circle"></span>
                                </a>
                            </h6>
                            <li class="nav-item">
                                <a class="nav-link" href="CandidateDashboard">
                                    <span data-feather="file-text"></span>
                                    Candidate
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="RecruiterDashBoard">
                                    <span data-feather="file-text"></span>
                                    Recruiter
                                </a>
                            </li>
                        </ul>
                        <ul class="nav flex-column mb-2">
                            <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                                <span>System</span>
                                <a class="d-flex align-items-center text-muted" href="#" aria-label="Add a new report">
                                    <span data-feather="plus-circle"></span>
                                </a>
                            </h6>
                            <li class="nav-item">
                                <a class="nav-link" href="JobDashboard">
                                    <span data-feather="file-text"></span>
                                    Job
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="SkillDashboard">
                                    <span data-feather="file-text"></span>
                                    Skill
                                </a>
                            </li>
                        </ul>

                    </div>
                </nav>
                <div class="container" style="margin-top :50px">
                    <div class="row">
                        <br/>
                        <div class="col text-center">
                            <h2>Recruitment And IT Job Search Website</h2>
                            <p>Admin Dashboard</p>
                        </div>



                    </div>
                    <div class="row text-center">
                        <div class="col" >
                            <div class="counter" style="background-color: #343A40; color: white">
                                <i class="fa fa-code fa-2x"></i>
                                <h2 class="timer count-title count-number" data-to="100" data-speed="1500">${totalRecruiter}</h2>
                                <p class="count-text ">Number of Recruiters</p>
                            </div>
                        </div>
                        <div class="col">
                            <div class="counter" style="background-color: #343A40; color: white">
                                <i class="fa fa-coffee fa-2x"></i>
                                <h2 class="timer count-title count-number" data-to="1700" data-speed="1500">${totalCandidate}</h2>
                                <p class="count-text ">Number of Candidates</p>
                            </div>
                        </div>
                        <div class="col">
                            <div class="counter" style="background-color: #343A40; color: white">
                                <i class="fa fa-lightbulb-o fa-2x"></i>
                                <h2 class="timer count-title count-number" data-to="11900" data-speed="1500">${totalJob}</h2>
                                <p class="count-text ">Number of Job</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="bootstrap-4.6.1-dist/js/bootstrap.min.js"></script>
    </body>
    <footer> <jsp:include page="component/Footer.jsp"/></footer>
</html>
