<%-- 
    Document   : SearchCandidate
    Created on : Feb 21, 2022, 11:43:25 AM
    Author     : USE
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="component/HiepHeader.jsp"/>
        <div class="row row-cols-2">
            <div class="col align-self-center pl-5">
                <p class="text-capitalize display-4 mb-4">Search Candidate</p>
                <form action="SearchCandidateController" method="POST">
                    <div class="input-group">
                        <input type="text" name="txtSearch" class="w-50 p-2" required="" maxlength="100" placeholder="Enter skill" value="${txtSearch}"> 
                        <input type="submit" value="search" class="btn btn-outline-primary mx-3">
                    </div>
                    <select class="mt-3 dropdown" name="cityName">
                        <c:forEach items="${listCity}" var="city">
                            <option value="${city.getName()}">${city.getName()}</option>
                        </c:forEach>                       
                    </select>
                </form>
            </div>
            <div class="col p-0 align-self-center">
                <img src="https://picsum.photos/500/300" class="img-fluid w-100 " alt="alt"/>
            </div>
        </div>
        <hr/>
        <div class="w-75 row m-auto">
            <c:forEach items="${listCandidate}" var="candidate">              
                <div class="card" style="width: 18rem;">
                    <img class="card-img-top" src="${candidate.avatar}" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title">${candidate.firstName} ${candidate.lastName}</h5>
                        <p class="card-text">${candidate.address}</p>
                        <a href="CandidateDetailController?candidateId=${candidate.getCandIdateId()}" class="btn btn-primary">View detail</a>
                    </div>
                </div>
            </c:forEach>                    
        </div>        
        <div class="row justify-content-center">
            <c:if test="${maxPage < 1}">
                <h3>No one match the search condition!!!!</h3>
            </c:if>
            <ul class="pagination mt-5">              
                <c:if test="${maxPage > 1}">
                    <c:forEach begin="1" end="${maxPage}" var="i">  
                        <li class="page-item"><a class="page-link" href="SearchCandidateController?index=${i}&&txtSearch=${txtSearch}&&cityName=${cityName}">${i}</a></li>
                        </c:forEach>
                    </c:if>                 
            </ul>
        </div>
        <jsp:include page="component/Footer.jsp"/>
    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>
