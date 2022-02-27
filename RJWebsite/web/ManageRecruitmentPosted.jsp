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
        <link href="css/ManageRecruitmentPosted.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="component/HiepHeader.jsp"/>
        <div class="container">
            <h1>Manage Recruitment Posted</h1>
            <a href="CreateRecruitmentController"><input class="createRecruitment" type="button" value="Create new recruitment"></a>       
            <table>
                <thead>
                <td>Name</td>
                <td>Hire Date</td>
                <td>Address</td>
                <td>Salary</td>
                <td></td>
                <td></td>
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
        <jsp:include page="component/HiepFooter.jsp"/>
    </body>
</html>
