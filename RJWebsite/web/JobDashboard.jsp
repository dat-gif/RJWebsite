<%-- 
    Document   : CandiateDashboard
    Created on : Jan 18, 2022, 11:27:19 PM
    Author     : admin
--%>

<%@page import="entity.Job"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <body>
        <jsp:include page="component/Adminheader.jsp"/>

        <div class="container-fluid" style="height: 850px;">
            <div class="row">
                <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse" style="height: 867px;">
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
                    <h1 class="title">Job Manangement</h1>
                    <div class="searchcontainer">
                        <form action="DashboardSearchingController?index=1" method="post">
                            <div class="input-group searchbar" >
                                <input  value="${save}"  type="search" name="txtSearch" class="form-control  rounded border-secondary" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
                                <button type="submit" name="btnSearch" value="search" class="btn btn-outline-primary ml-1">search</button>
                            </div>
                        </form>
                    </div>
                    <c:choose>
                        <c:when test="${jobs.size() >0}">
                            <table class="table" style="width: 100%">
                                <thead class="thead-dark">
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">Job Title</th>
                                        <th scope="col">Recruiter</th>
                                        <th scope="col">HireDate</th>
                                        <th scope="col">Location</th>
                                        <th scope="col">Status</th>
                                        <th scope="col">Action</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <c:forEach items="${jobs}" var="j">
                                        <tr>
                                            <td>${j.jId}</td>
                                            <th scope="row">${j.title}</th>
                                            <td>${j.getRecruiter().name}</td>
                                            <td>${j.hireDate}</td>
                                            <td>${j.location}</td>
                                            <td><a href="UpdateStatusController?id=${j.jId}&index=${index}&txtSearch=${save}">
                                                    <c:choose>
                                                        <c:when test="${j.status}">
                                                            Deactivate
                                                        </c:when>
                                                        <c:otherwise>
                                                            Activate
                                                        </c:otherwise>
                                                    </c:choose>
                                                </a></td>
                                            <td><a href="DashboardCandidateApplyJobController?id=${j.jId}&index=1" id="a1">Candidate Applied</a>
                                                <a href="DashboardJobDetailController?jobId=${j.jId}" id ="a2">Details</a>
                                            </td>
                                        </tr>
                                    </c:forEach> 
                                </tbody>
                            </table>
                            <nav aria-label="Page navigation example" style="text-align: center">
                                <ul class="pagination">
                                    <c:if test="${index >1}">
                                        <li class="page-item"><a class="page-link" href="DashboardSearchingController?index=${index-1}&txtSearch=${save}">Previous</a></li>
                                        </c:if>
                                        <c:forEach begin="1" end="${end}" var="i">
                                        <li class="page-item"><a class="page-link" href="DashboardSearchingController?index=${i}&txtSearch=${save}">${i}</a></li>
                                        </c:forEach>
                                        <c:if test="${index<end}">
                                        <li class="page-item"><a class="page-link" href="DashboardSearchingController?index=${index+1}&txtSearch=${save}">Next</a></li>
                                        </c:if>
                                </ul>
                            </nav>
                        </c:when >
                        <c:otherwise>
                            <p class="text-left mt-3 text-secondary lead" style="font-size: 2rem">No data is found.</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <script src="bootstrap-4.6.1-dist/js/bootstrap.min.js"></script>
    </body>
    <footer> <jsp:include page="component/Footer.jsp"/></footer>
</html>
