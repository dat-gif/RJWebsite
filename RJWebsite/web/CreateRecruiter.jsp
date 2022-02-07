<%-- 
    Document   : CreateRecruiter
    Created on : Feb 7, 2022, 11:45:59 AM
    Author     : USE
--%>

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
        <form class="form">
            <table>
                <tr>
                    <td>Job Name</td>
                    <td><input type="text" value=""></td>
                    <td>Salary</td>
                    <td><input type="text" value=""></td>
                </tr>
                <tr>
                    <td>Quantity</td>
                    <td><input type="text" value=""></td>
                    <td>Role</td>
                    <td><input type="text" value=""></td>
                </tr>
                <tr>
                    <td>Experience</td>
                    <td><input type="text" value=""></td>
                    <td>End Date</td>
                    <td><input type="text" value=""></td>
                </tr>
                <tr>
                    <td>Location</td>
                    <td><select name="" id="">
                            <option></option>
                        </select>
                    </td>
                    <td>Skill</td>
                    <td><select name="" id="">
                            <option></option>
                        </select>
                    </td>
                </tr>           
            </table>
            <span>Description</span><input class="des" type="text">
            <input class="btn" type="submit" value="Create new recruitment">
        </form>
        <jsp:include page="component/HiepFooter.jsp"/>
    </body>
</html>
