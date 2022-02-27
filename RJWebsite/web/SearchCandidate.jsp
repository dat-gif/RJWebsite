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
        <link href="css/SearchCandidate.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="component/HiepHeader.jsp"/>
        <div class="top">
            <div class="form">
                <p>Search Candidate</p>
                <form action="SearchCandidateController" method="POST">
                    <input type="text" name="txtSearch" class="txtSeach" required="" placeholder="Enter skill"> 
                    <input type="submit" value="search" class="btn">
                    <select name="city">
                        <c:forEach items="${listCity}" var="city">
                            <option value="${city.getName()}">${city.getName()}</option>
                        </c:forEach>                       
                    </select>
                </form>
            </div>
            <div class="image">
                <img src="https://picsum.photos/500/300" class="img-fluid w-100 " alt="alt"/>
            </div>
        </div>
        <hr/>
        <div class="content">
            <c:forEach items="${listCandidate}" var="candidate">
                <div class="candidate">
                    <img src="${candidate.avatar}"></img>
                    <p>${candidate.address}</p>
                    <p>${candidate.firstName} ${candidate.lastName}</p>
                </div>  
            </c:forEach>                    
        </div>
        <div class="paging">
            <c:if test="${maxPage < 1}">
                <h3>No one match the search condition!!!!</h3>
            </c:if>
            <c:if test="${maxPage > 1}">
                <c:forEach begin="1" end="${maxPage}" var="i">
                    <a class="${indexPage==i?"active":"number"}" href="SearchCandidateController?index=${i}&&txtSearch=${txtSearch}">${i}</a>
                </c:forEach>
            </c:if>
        </div>
        <jsp:include page="component/HiepFooter.jsp"/>
    </body>
</html>
