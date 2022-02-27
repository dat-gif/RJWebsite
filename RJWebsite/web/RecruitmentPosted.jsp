<%-- 
    Document   : RecruitmentPosted
    Created on : Feb 7, 2022, 12:01:34 PM
    Author     : USE
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
        <link href="css/RecruitmentPosted.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="component/HiepHeader.jsp"/>
        <div class="top">
            <div class="apply">
                <i class="fas fa-rocket"></i>
                <p>Apply Pending</p>
                <p>16</p>
            </div>
            <div class="recruit">
                <i class="fas fa-project-diagram"></i>
                <p>Recruit</p>
                <p>30</p>
            </div>
            <div class="followers">
                <i class="fas fa-database"></i>
                <p>Followers</p>
                <p>20</p>
            </div>
        </div>
        <div class="content">
            <h3>Recruitment Posted</h3>
            <c:forEach items="${listRecruiter}" var="recruiter">
                <div class="company">                   
                    <a href="RecruiterDetailController?recruiterId=${recruiter.getRecruiterId()}"><img src="${recruiter.getBanner()}"></img></a>
                    <p>${recruiter.getName()}</p>
                    <p>${recruiter.getWebsite()}</p>
                    <p>${recruiter.getSkillList()}</p>
                </div>
            </c:forEach>       
            <h3>Candidate</h3>
            <c:forEach items="${listCandidate}" var="candidate">
                <div class="candidate">
                    <img src="${candidate.avatar}"></img>
                    <p>${candidate.address}</p>
                    <p>${candidate.firstName} ${candidate.lastName}</p>
                </div>  
            </c:forEach>                 
        </div>
        <jsp:include page="component/HiepFooter.jsp"/>
    </body>
</html>
