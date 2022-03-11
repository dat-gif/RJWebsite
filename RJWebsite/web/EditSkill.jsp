<%-- 
    Document   : EditTest
    Created on : Feb 27, 2022, 4:25:46 AM
    Author     : admin
--%>

<%@page import="entity.Skill"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>Edit Skill</title>
    </head>
    <body>
        <c:set var="s" value="${skill}"/>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <form action="EditSkillController?id=${s.id}&index=${index}&txtSearch=${save}" method="post">
                        <Input type="hidden" value="${s.id}" name="id" class="form-control" id="recipient-name">
                        <fieldset class="form-group">
                            <label>Skill Name</label>
                            <input type="text" value="${s.name}"
                                                             class="form-control"
                                                             name="name" required="required">
                            
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Description</label><textarea type="text" value="${s.depscription}"
                                                                class="form-control"
                                                                name="description">${s.depscription}</textarea>
                        </fieldset>
                        <button type="submit" value="Update" name="op" class="btn btn-success">Edit</button>
                    </form>
                </div>
            </div>

    </body>
</html>
