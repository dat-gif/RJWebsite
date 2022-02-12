<%-- 
    Document   : ApplyJobCard
    Created on : Jan 19, 2022, 3:58:57 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <div class="card text-center" >

            <c:choose>
                <c:when test="${param.status eq 'PENDING'}">
                    <div class="card-header bg-primary text-light">Pending</div>        
                </c:when>
                <c:when test="${param.status eq 'REJECT'}">
                    <div class="card-header bg-danger text-light">Reject</div>        
                </c:when>
                <c:when test="${param.status eq 'APPROVE'}">
                    <div class="card-header bg-success text-light">
                        Approve
                    </div>        
                </c:when>
                <c:otherwise>
                    <div class="card-header bg-dark text-light">
                        Loading
                    </div> 
                </c:otherwise>
            </c:choose>
            <div class="card-body " style="height: 17rem" >
                <a class="card-title mt-2 overflow-hidden h4 stretched-link text-primary" style="cursor: pointer" href="${param.link}" >  
                    <h5 class="card-title mt-4 overflow-hidden ">
                        ${param.title1}
                    </h5>
                </a>
                <p class="card-text overflow-hidden ">${param.title2}</p>
                <hr/>
                <p class="card-text overflow-hidden font-weight-bold ">${param.text1}</p>
                <p class="card-text overflow-hidden text-info "> ${param.text2} </p>
            </div>
            <div class="card-footer text-muted">${param.text3}</div>
        </div>
    </body>
</html>
