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
        <link href="css/CreateRecruitment.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
    </head>
    <body>
        <jsp:include page="component/HiepHeader.jsp"/>
        <h1>Create Recruitment</h1>
        <form class="form" action="CreateRecruitmentController" method="post">
            <table>
                <tr>
                    <td>Job Name</td>
                    <td><input type="text" name="jobName"></td>
                    <td>Salary</td>
                    <td><input type="text" name="salary"></td>
                </tr>
                <tr>
                    <td>Quantity</td>
                    <td><input type="text" name="quantity"></td>
                    <td>Role</td>
                    <td><input type="text" name="role"></td>
                </tr>
                <tr>
                    <td>Experience</td>
                    <td><input type="text" name="experience"></td>
                    <td>Hire Date</td>
                    <td><input type="text" name="hireDate"></td>
                </tr>
                <tr>
                    <td>Location</td>
                    <td><input type="text" name="location"></td>
                    <td>Skill</td>
                    <td><select name="" id="">
                            <c:forEach items="${listSkill}" var="skill">
                                <option value="${skill.getId()}" name="skillId">${skill.getName()}</option>
                            </c:forEach>                         
                        </select>
                    </td>
                </tr>           
            </table>
            <span>Description</span><input class="des" type="text" name="description">
            <input class="btn" type="submit" value="Create new recruitment">
        </form>
        <jsp:include page="component/HiepFooter.jsp"/>
    </body>
</html>
