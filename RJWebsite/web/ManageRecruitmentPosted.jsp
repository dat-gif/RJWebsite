<%-- 
    Document   : ManageRecruitmentPosted
    Created on : Feb 7, 2022, 11:59:39 AM
    Author     : USE
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="component/HiepHeader.jsp"/>
        <div class="w-auto">
            <h1 class="m-5">Manage Recruitment Posted</h1>
            <div class="ml-5 w-75 d-flex justify-content-end">
                <a href="CreateRecruitmentController"><input class="btn btn-primary" type="button" value="Create new recruitment"></a>       
            </div>
            <table class="table w-75 mt-5 mb-5 ml-auto mr-auto text-center">
                <thead class="thead-light">
                    <tr>
                        <th>Name</th>
                        <th>Hire Date</th>
                        <th>Address</th>
                        <th>Salary</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <c:forEach items="${listJob}" var="job">
                    <tr>
                        <td>${job.getTitle()}</td>
                        <td>${job.getHireDate()}</td>
                        <td>${job.getLocation()}</td>
                        <td>${job.getSalaryRange()}</td>
                        <td><a href="UpdateRecruitmentController?jobId=${job.getjId()}">Edit</a></td>
                        <td><a href="DeleteRecruitmentController?jobId=${job.getjId()}">Delete</a></td>
                    </tr> 
                </c:forEach>                              
            </table>
        </div>  
        <div class="row justify-content-center">
            <c:if test="${maxPage < 1}">
                <h3>No record</h3>
            </c:if>
            <ul class="pagination">              
                <c:if test="${maxPage > 1}">
                    <c:forEach begin="1" end="${maxPage}" var="i">  
                        <li class="page-item"><a class="page-link" href="ManageRecruitmentPostedController?index=${i}">${i}</a></li>
                        </c:forEach>
                    </c:if>                 
            </ul>
        </div>
        <jsp:include page="component/Footer.jsp"/>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>       
    </body>
</html>
