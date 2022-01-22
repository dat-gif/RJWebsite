<%-- 
    Document   : CardInfo
    Created on : Jan 18, 2022, 11:46:49 AM
    Author     : Admin
--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>

        <div class="col p-1">
            <div class="card" >
                <img src="https://mdbcdn.b-cdn.net/img/new/standard/city/062.webp" class="card-img-top"/>
                <div class="card-body text-center pb-1 mb-3" >
                    <h4 class="card-title mt-2" ><c:out value="${param.title1}"/></h4>
                    <h6 class="card-text font-weight-light overflow-hidden"><c:out value="${param.title2}"/></h6>
                </div>
                <ul class="list-group list-group-flush text-center">
                    <li class="list-group-item">
                        <c:if test="${!empty param.arraySkill}">
                            <c:forEach items="${param.arraySkill}" var="skill">
                                <span><c:out value="${skill} ,"/>
                                </span>
                            </c:forEach>
                        </c:if>
                        <br/>
                        <p class="text-info"><c:out value="${param.text2}"/></p>
                </ul>

            </div>
        </div>
    </body>
</html>
