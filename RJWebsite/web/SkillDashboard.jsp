<%-- 
    Document   : CandiateDashboard
    Created on : Jan 18, 2022, 11:27:19 PM
    Author     : admin
--%>

<%@page import="entity.Skill"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Skill Dashboard</title>
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
                <div class="content" style="float:left; width: 80%" >
                    <h1 class="title ">Skill Management</h1>
                    <div class="searchcontainer">
                        <form action="SkillDashboardSearchingController?index=1" method="post">
                            <div class="input-group searchbar">
                                <input type="search" name="txtSearch" class="form-control  rounded border-secondary" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
                                <button type="submit" name="btnSearch" value="search" class="btn btn-outline-primary ml-1">search</button>
                            </div>
                        </form>
                    </div>
                    <a id="addbtn" class="btn btn-primary" href="AddSkill.jsp" style="float: right">Add New Skill</a>
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Name</th>
                                <th scope="col">Description</th>
                                <th scope="col">Status</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${skills}" var="s">
                                <tr>
                                    <td><image src="${s.iconBase64}" class="rounded-circle shadow" style="height: 70px"/></td>
                                    <th scope="row">${s.name}</th>
                                    <td class="de"><p>${s.depscription}</p></td>
                                    <th scope="col"><a href="UpdateStatusController?id=${s.id}&index=${index}&txtSearch=${save}&action=skill">
                                            <c:choose>
                                                <c:when test="${s.status}">
                                                    Activate
                                                </c:when>
                                                <c:otherwise>
                                                    Deactivate
                                                </c:otherwise>
                                            </c:choose>
                                        </a></th>
                                    <th>
                                        <a href ="EditSkillController?id=${s.id}&index=${index}&txtSearch=${save}" id ="a2" >Edit</a>&nbsp;
                                        <a href ="" id ="a2" data-toggle="modal" data-target="#exampleModal" data-id = "${s.id}">Delete</a>
                                    </th>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <nav aria-label="Page navigation example" style="text-align: center">
                        <ul class="pagination">
                            <c:if test="${index >1}">
                                <li class="page-item"><a class="page-link" href="SkillDashboardSearchingController?index=${index-1}&txtSearch=${save}">Previous</a></li>
                                </c:if>
                                <c:forEach begin="1" end="${end}" var="i">
                                <li class="page-item"><a class="page-link" href="SkillDashboardSearchingController?index=${i}&txtSearch=${save}">${i}</a></li>
                                </c:forEach>
                                <c:if test="${index < end}">
                                <li class="page-item"><a class="page-link" href="SkillDashboardSearchingController?index=${index+1}&txtSearch=${save}">Next</a></li>
                                </c:if>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
        <!-- Modal -->
        <form action="DeleteSkillDashboardController" method="get">
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Confirmation</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                       
                        <input type="hidden" id="id" name="id"/>
                        <input type="hidden" value="${index}" name="index"/>
                        <input type="hidden" value="${save}" name="txtSearch"/>
                        <div class="modal-body">
                            Are you sure you want to delete this skill?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                            <button type="submit" class="btn btn-danger" >Yes</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <script>$('#exampleModal').on('show.bs.modal', function (event) {
                var button = $(event.relatedTarget); // Button that triggered the modal
                var recipient = button.data('id'); // Extract info from data-* attributes
                var modal = $(this);
                modal.find('#id').val(recipient);
            });</script>
        <script src="bootstrap-4.6.1-dist/js/bootstrap.min.js"></script>
    </body>
    <footer> <jsp:include page="component/Footer.jsp"/></footer>
</html>
