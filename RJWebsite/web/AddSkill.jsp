<%-- 
    Document   : AddSkill
    Created on : Feb 27, 2022, 2:35:39 PM
    Author     : admin
--%>

<%@page import="entity.Skill"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap-4.6.1-dist/css/bootstrap.min.css" media="all" type="text/css" rel="stylesheet">
        <script src="bootstrap-4.6.1-dist/jQuery/jquery-3.6.0.min.js"></script>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="adminstyle/CandidateDashboard.css">
        <title>Add Skill</title>
    </head>
    <body>
        <%
            Skill s = (Skill) request.getAttribute("skill");
        %>
        <jsp:include page="component/Adminheader.jsp"/>
        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <form action="AddSkillController" method="post">
                        <fieldset class="form-group">
                            <label>Skill Name</label> <input type="text"
                                                             class="form-control"
                                                             name="name" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Description</label><textarea type="text"
                                                               class="form-control"
                                                               name="description"></textarea>
                        </fieldset>
                        <button type="submit" class="btn btn-success">Add</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
