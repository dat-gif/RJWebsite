<%-- 
    Document   : CandiateDashboard
    Created on : Jan 18, 2022, 11:27:19 PM
    Author     : admin
--%>

<%@page import="entity.Recruiter"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recruiter Dashboard</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap-4.6.1-dist/css/bootstrap.min.css" media="all" type="text/css" rel="stylesheet">
        <script src="bootstrap-4.6.1-dist/jQuery/jquery-3.6.0.min.js"></script>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="adminstyle/CandidateDashboard.css">
    </head>
    <body>
        <jsp:include page="component/Adminheader.jsp"/>

        <div class="container-fluid">
            <div class="row">
                <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
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
                <div class="content">
                    <h1>Recruiter Dashboard</h1>
                    <main role="main" class="searchcontainer">
                        <div class="input-group">
                            <input type="search" class="form-control  rounded border-secondary" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
                            <button type="button" class="btn btn-outline-primary ml-1">search</button>
                        </div>
                    </main>
                    
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Name</th>
                                <th scope="col">Website</th>
                                <th scope="col">Phone</th>
                                <th scope="col">Address</th>
                                <th scope="col">City</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach items ="${recruiters}" var="r">
                            <tr>
                                <td>${r.recruiterId}</td>
                                <th scope="row">${r.name}</th>
                                <td><a href="${r.website}">${r.website}</a></td>
                                <td>${r.phone}</td>
                                <td>${r.address}</td>
                                <td>${r.city}</td>
                                <td><a href="url" id ="a1">Review</a>&nbsp;&nbsp;<a href="url" id ="a2">Details</a></td>

                            </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                    <div class="row justify-content-center mt-4 mb-4">
                        <jsp:include page="component/Pagination.jsp"/>
                    </div>
                </div>
            </div>
        </div>
        <script src="bootstrap-4.6.1-dist/js/bootstrap.min.js"></script>
    </body>
</html>
