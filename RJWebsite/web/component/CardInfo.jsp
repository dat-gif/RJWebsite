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
                <!-- Image -->
                <img src="${param.img}" class="card-img-top "/>
                <span class="border-bottom"></span>
                <div class="card-body text-center pb-1 mb-3" >
                    <!-- Title -->
                    <c:choose>
                        <c:when test="${fn:length(param.title1) gt 18}">
                            <a class="card-title mt-2 overflow-hidden h5 stretched-link text-primary" style="cursor: pointer" href="${param.link}" >
                                <c:out value="${param.title1}"/>
                            </a>
                        </c:when>
                        <c:when test="${fn:length(param.title1) gt 25}">
                            <a class="card-title mt-2 overflow-hidden h4 stretched-link text-primary text-truncate" style="cursor: pointer" href="${param.link}" >
                                <c:out value="${param.title1}"/>
                            </a>
                        </c:when>
                        <c:otherwise>                           
                            <a class="card-title mt-2 overflow-hidden h4 stretched-link text-primary" style="cursor: pointer" href="${param.link}" >
                                <c:out value="${param.title1}"/>
                            </a>
                        </c:otherwise>
                    </c:choose>
                    <!-- Sub title -->
                    <h6 class="card-text font-weight-light overflow-hidden"><c:out value="${param.title2}"/></h6>
                </div>

                <ul class="list-group list-group-flush text-center">
                    <li class="list-group-item">
                        <!-- Array skill -->
                        <c:choose>
                            <c:when test="${fn:length(param.arraySkill) gt 2}">
                                <p class="text-truncate p-0">
                                    <c:forEach items="${param.arraySkill}" var="skill">
                                        <c:set var="test" value="${fn:replace(skill, '[', '')}"/>
                                        <span>
                                            <c:out value="${fn:replace(test, ']', '')}"/>&nbsp;
                                        </span>
                                    </c:forEach>
                                </p>                    
                                <p class="text-info"><c:out value="${param.text2}"/></p>
                            </c:when>
                            <c:otherwise>
                                <p class="text-info mt-4"><c:out value="${param.text2}"/></p>
                            </c:otherwise>
                        </c:choose>
                </ul>

            </div>
        </div>
    </body>
</html>
