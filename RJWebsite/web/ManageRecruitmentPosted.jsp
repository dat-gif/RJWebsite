<%-- 
    Document   : ManageRecruitmentPosted
    Created on : Feb 7, 2022, 11:59:39 AM
    Author     : USE
--%>

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
            <input class="createRecruitment" type="button" value="Create new recruitment">       
            <table>
                <thead>
                <td>Name</td>
                <td>Expriation Date</td>
                <td>Address</td>
                <td>Salary</td>
                <td></td>
                <td>Action</td>
                <td></td>
                </thead>
                <tr>
                    <td>Java Dev</td>
                    <td>22/10/2000</td>
                    <td>So 6 ngo 88 Tu Hoa</td>
                    <td>2000$</td>
                    <td><a href="#">Detail</a></td>
                    <td><a href="#">Candidate List</a></td>
                    <td><a href="#">Edit</a></td>
                </tr>
                <tr>
                    <td>Java Dev</td>
                    <td>22/10/2000</td>
                    <td>So 6 ngo 88 Tu Hoa</td>
                    <td>2000$</td>
                    <td><a href="#">Detail</a></td>
                    <td><a href="#">Candidate List</a></td>
                    <td><a href="#">Edit</a></td>
                </tr>
                <tr>
                    <td>Java Dev</td>
                    <td>22/10/2000</td>
                    <td>So 6 ngo 88 Tu Hoa</td>
                    <td>2000$</td>
                    <td><a href="#">Detail</a></td>
                    <td><a href="#">Candidate List</a></td>
                    <td><a href="#">Edit</a></td>
                </tr>
            </table>
        </div>
        <div class="pagging">
            <div class="number">1</div>
            <div class="number">2</div>
            <div class="number">3</div>
            <div class="number">4</div>
        </div>
        <jsp:include page="component/HiepFooter.jsp"/>
    </body>
</html>
