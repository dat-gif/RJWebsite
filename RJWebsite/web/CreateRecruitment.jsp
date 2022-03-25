<%-- 
    Document   : CreateRecruiter
    Created on : Feb 7, 2022, 11:45:59 AM
    Author     : USE
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <h2 class="m-5">Create Recruitment</h2>
        <form class="form ml-5" action="CreateRecruitmentController" method="POST">
            <table class="table">
                <tr>
                    <td>Job Name <span style="color: red;">*</span></td>
                    <td><input type="text" name="jobName" required="" minlength="10" maxlength="100"></td>
                    <td>Salary <span style="color: red;">*</span></td>
                    <td><input type="text" name="salary" required="" minlength="10" maxlength="100"></td>
                </tr>
                <tr>
                    <td>Member <span style="color: red;">*</span></td>
                    <td><input type="text" name="quantity" required="" minlength="10" maxlength="100"></td>
                    <td>Role <span style="color: red;">*</span></td>
                    <td><input type="text" name="role" required="" minlength="10" maxlength="100"></td>
                </tr>
                <tr>
                    <td>Experience <span style="color: red;">*</span></td>
                    <td><input type="text" name="experience" required="" minlength="10" maxlength="100"></td>
                    <td>Hire Date <span style="color: red;">*</span></td>
                    <td><input class="date" type="date" name="hireDate" required="" max="2022-03-18"></td>
                </tr>
                <tr>
                    <td>Location <span style="color: red;">*</span></td>
                    <td><input type="text" name="location" required="" minlength="10" maxlength="100"></td>
                    <td>Skill <span style="color: red;">*</span></td>
                    <td><select class="dropdown" name="skill" id="">
                            <c:forEach items="${listSkill}" var="skill">
                                <option value="${skill.getId()}" name="">${skill.getName()}</option>
                            </c:forEach>                         
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Description <span style="color: red;">*</span></td>
                    <td><input class="" type="text" name="description" required="" minlength="10" maxlength="1000"></td>
                </tr>
            </table>
            <br/>
            <c:if test="${not empty error}">
                <h3 style="text-align: center; margin: 30px; color: red;">${error}</h3>
            </c:if>
            <h3 style="text-align: center; margin: 30px; color: green;">${msg}</h3>
            <div class="text-center">
                <input class="m-auto btn btn-primary mb-5" type="submit" value="Create new recruitment">
            </div>
        </form>
        <jsp:include page="component/Footer.jsp"/>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
