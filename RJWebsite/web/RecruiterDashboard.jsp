<%-- 
    Document   : CandiateDashboard
    Created on : Jan 18, 2022, 11:27:19 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                                <a class="nav-link active" href="Dashboard.jsp">
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
                                <a class="nav-link" href="CandidateDashboard.jsp">
                                    <span data-feather="file-text"></span>
                                    Candidate
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="RecruiterDashboard.jsp">
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
                                <a class="nav-link" href="JobDashboard.jsp">
                                    <span data-feather="file-text"></span>
                                    Job
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="SkillDashboard.jsp">
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
                                <th scope="col">Name</th>
                                <th scope="col">Website</th>
                                <th scope="col">Phone</th>
                                <th scope="col">Status</th>
                                <th scope="col">Premium</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row">ORA</th>
                                <td><a href="https://tuyendung.vietis.com.vn/">https://tuyendung.vietis.com.vn/</a></td>
                                <td>0393958404</td>
                                <td><a href="url">Activate</a></td>
                                <td><a href="url">Deactivate</a></td>
                                <td><a href="url" id ="a1">Review</a><a href="url" id ="a2">Details</a></td>
                            </tr>
                            <tr>
                                <th scope="row">ORA</th>
                                <td><a href="https://tuyendung.vietis.com.vn/">https://tuyendung.vietis.com.vn/</a></td>
                                <td>0393958404</td>
                                <td><a href="url">Activate</a></td>
                                <td><a href="url">Deactivate</a></td>
                                <td><a href="url" id ="a1">Review</a><a href="url" id ="a2">Details</a></td>
                            </tr>
                            <tr>
                                <th scope="row">ORA</th>
                                <td><a href="https://tuyendung.vietis.com.vn/">https://tuyendung.vietis.com.vn/</a></td>
                                <td>0393958404</td>
                                <td><a href="url">Activate</a></td>
                                <td><a href="url">Deactivate</a></td>
                                <td><a href="url" id ="a1">Review</a><a href="url" id ="a2">Details</a></td>
                            </tr>
                            <tr>
                                <th scope="row">ORA</th>
                                <td><a href="https://tuyendung.vietis.com.vn/">https://tuyendung.vietis.com.vn/</a></td>
                                <td>0393958404</td>
                                <td><a href="url">Activate</a></td>
                                <td><a href="url">Deactivate</a></td>
                                <td><a href="url" id ="a1">Review</a><a href="url" id ="a2">Details</a></td>
                            </tr>
                            <tr>
                                <th scope="row">ORA</th>
                                <td><a href="https://tuyendung.vietis.com.vn/">https://tuyendung.vietis.com.vn/</a></td>
                                <td>0393958404</td>
                                <td><a href="url">Activate</a></td>
                                <td><a href="url">Deactivate</a></td>
                                <td><a href="url" id ="a1">Review</a><a href="url" id ="a2">Details</a></td>
                            </tr>

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
