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
        <h1>Update Recruitment</h1>
        <form class="form" action="UpdateRecruitmentController?jobId=${job.getjId()}" method="POST">
            <table>
                <tr>
                    <td>Job Name</td>
                    <td><input type="text" name="jobName" required="" minlength="10" maxlength="100" value="${job.getTitle()}"></td>
                    <td>Salary</td>
                    <td><input type="text" name="salary" required="" minlength="10" maxlength="100" value="${job.getSalaryRange()}"></td>
                </tr>
                <tr>
                    <td>Quantity</td>
                    <td><input type="text" name="quantity" required="" minlength="10" maxlength="100" value="${job.getQuantity()}"></td>
                    <td>Role</td>
                    <td><input type="text" name="role" required="" minlength="10" maxlength="100" value="${job.getRole()}"></td>
                </tr>
                <tr>
                    <td>Experience</td>
                    <td><input type="text" name="experience" required="" minlength="10" maxlength="100" value="${job.getExperience()}"></td>
                    <td>Hire Date</td>
                    <td><input type="text" name="hireDate" required="" minlength="10" maxlength="100" value="${job.getHire_date()}"></td>
                </tr>
                <tr>
                    <td>Location</td>
                    <td><input type="text" name="location" required="" minlength="10" maxlength="100" value="${job.getLocation()}"></td>
                    <td>Skill</td>
                    <td><select name="" id="">
                            <c:forEach items="${listSkill}" var="skill">
                                <option value="${skill.getId()}" name="skillId">${skill.getName()}</option>
                            </c:forEach>                         
                        </select>
                    </td>
                </tr>           
            </table>
            <span>Description</span><input class="des" type="text" name="description" required="" minlength="10" maxlength="1000" value="${job.getDescription()}">
            <input class="btn" type="submit" value="Update recruitment">
        </form>
        <jsp:include page="component/HiepFooter.jsp"/>
    </body>
</html>
