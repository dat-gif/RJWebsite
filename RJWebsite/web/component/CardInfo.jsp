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

        <div class="col p-1" >
            <div class="card">
                <img src="${param.img}" class="card-img-top"/>
                <div class="card-body text-center pb-1 mb-3" >
                    <a class="card-title mt-2 overflow-hidden h4 stretched-link text-primary" style="cursor: pointer" href="${param.link}" ><c:out value="${param.title1}"/></a>
                    <h6 class="card-text font-weight-light overflow-hidden"><c:out value="${param.title2}"/></h6>
                </div>
                <ul class="list-group list-group-flush text-center">
                    <li class="list-group-item">
                        <!-- Array skill -->

                        <c:if test="${fn:length(param.arraySkill) gt 2}">
                            <p class="text-truncate p-0">
                                <c:forEach items="${param.arraySkill}" var="skill">
                                    <c:set var="test" value="${fn:replace(skill, '[', '')}"/>
                                    <span>
                                        <c:out value="${fn:replace(test, ']', '')}"/>&nbsp;
                                    </span>
                                </c:forEach>
                            </p>                    
                        </c:if>
                        <p class="text-info"><c:out value="${param.text2}"/></p>
                </ul>

            </div>
        </div>
    </body>
</html>
