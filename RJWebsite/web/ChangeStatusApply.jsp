<%-- 
    Document   : ChangeStatusApply
    Created on : Mar 15, 2022, 3:22:23 PM
    Author     : USE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="component/HiepHeader.jsp"/>
        <h1 class="m-5">Change Candidate Status Apply</h1>
        <form action="ChangeStatusApplyController" method="POST">
            <input class="d-none" name="candidateId" value="${cid}">
            <input class="d-none" name="jobId" value="${pid}">
            <div class="text-center mb-5">
                <select class="p-2" name="status">
                    <option value="APPROVE">APPROVE</option>
                    <option value="REJECT">REJECT</option>
                </select>
                <input type="submit" class="btn btn-primary" value="Change"/>
            </div>
            <h3 style="text-align: center; margin: 30px; color: green;">${msg}</h3>
        </form>      
        <jsp:include page="component/Footer.jsp"/>
    </body>
</html>
