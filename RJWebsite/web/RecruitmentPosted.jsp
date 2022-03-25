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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="component/HiepHeader.jsp"/>
        <div class="top">
            <div class="apply">
                <i class="fas fa-rocket"></i>
                <p>Apply Pending</p>
                <p>3</p>
            </div>
            <div class="recruit">
                <i class="fas fa-project-diagram"></i>
                <p>Recruit</p>
                <p>2</p>
            </div>
            <div class="followers">
                <i class="fas fa-database"></i>
                <p>Followers</p>
                <p>0</p>
            </div>
        </div>
        <div class="m-auto" style="width: 85%">
            <div class="m-5 row">
                <h3 class="p-5">Recruitment Posted</h3>   
                <c:forEach items="${listRecruiter}" var="recruiter">
                    <div class="card" style="width: 18rem;">
                        <img class="card-img-top" src="${recruiter.getBanner()}" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title">${recruiter.getName()}</h5>
                            <p class="card-text">${recruiter.getWebsite()}</p>
                            <a href="RecruiterDetailController?recruiterId=${recruiter.getRecruiterId()}" class="btn btn-primary">View detail</a>
                        </div>
                    </div>
                </c:forEach>
            </div>     
            <div class="m-5 row">
                <h3 class="p-5">Candidate</h3>           
                <c:forEach items="${listCandidate}" var="candidate">
                    <div class="card" style="width: 20%;">
                        <img class="card-img-top" src="${candidate.avatar}" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title">${candidate.firstName} ${candidate.lastName}</h5>
                            <p class="card-text">${candidate.address}</p>
                            <a href="CandidateDetailController?candidateId=${candidate.getCandIdateId()}" class="btn btn-primary">View detail</a>
                        </div>
                    </div>
                </c:forEach>
            </div>                                       
        </div>
        <jsp:include page="component/Footer.jsp"/>
    </body>
</html>
