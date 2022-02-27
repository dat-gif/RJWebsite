<%-- 
    Document   : EditTest
    Created on : Feb 27, 2022, 4:25:46 AM
    Author     : admin
--%>

<%@page import="entity.Skill"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Skill s = (Skill) request.getAttribute("skill");
        %>
        <form method="post" action="EditSkillController">
                <div class="modal fade" id="EditModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form method="post" action="EditSkillController">
                            <% Skill skill = (Skill)request.getAttribute("skill"); %>
                            <div class="form-group">
                                <label for="recipient-name" class="col-form-label">ID:</label>
                                <Input type="text" value="<%= s.getId() %>" name="id" class="form-control" id="recipient-name">
                            </div>
                            <div class="form-group">
                                <label for="recipient-name" class="col-form-label">Icon:</label>
                                <Input type="text" value="<%= s.getIconBase64() %>" name="icon" class="form-control" id="recipient-name">
                            </div>
                            <div class="form-group">
                                <label for="recipient-name" class="col-form-label">Skill</label>
                                <Input type="text" value="<%=skill.getName()%>" name="name" class="form-control" id="recipient-name">
                            </div>
                            <div class="form-group"> 
                                <label for="message-text" class="col-form-label">Description:</label>
                                <textarea value="<%=skill.getDepscription()%>" name="description" class="form-control" id="recipient-name"><%=skill.getDepscription()%></textarea>
                            </div>  
                    </div>        
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" value="Update" name="op" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>
        </form>
    </body>
</html>
