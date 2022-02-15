<%-- 
    Document   : CandidateApplyJobPage
    Created on : Jan 19, 2022, 3:56:23 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap-4.6.1-dist/css/bootstrap.min.css" media="all" type="text/css" rel="stylesheet">
        <script src="bootstrap-4.6.1-dist/jQuery/jquery-3.6.0.min.js"></script>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Following Company</title>
    </head>
    <body>
        <header><jsp:include page="component/Header.jsp"/></header>
        <main class="container">
            <h3 class="text-capitalize display-5 mb-4" style="margin-top: 5rem">Following company</h3>

            <!-- List following company  -->
            <div class="row row-cols-4 align-content-center ">
                <c:forEach items="${listRecruiter}" var="recruiter"> 
                    <div class="col p-1 mt-1">
                        <% request.setCharacterEncoding("utf-8");%>
                        <jsp:include page="component/CardInfo.jsp">
                            <jsp:param name="img" value="${recruiter.getAvatar()}"/>
                            <jsp:param name="title1" value="${recruiter.getName()}"/>
                            <jsp:param name="title2" value="${recruiter.getWebsite()}"/>
                            <jsp:param name="arraySkill" value="${recruiter.getSkillListName()}"/>
                        </jsp:include>
                    </div>
                </c:forEach> 
            </div>

            <!-- Paging -->
            <div class="row justify-content-center mt-4 mb-4">
                <nav aria-label="...">
                    <ul class="pagination">
                        <c:forEach begin="1" end="${totalPage}" var="i">
                            <li class="page-item" value="${page}"  name="page">
                                <a class="page-link" href="#" >${i}</a>
                            </li>    
                        </c:forEach>
                    </ul>
                </nav>
            </div>
        </main>
        <footer> <jsp:include page="component/Footer.jsp"/></footer>
        <script src="bootstrap-4.6.1-dist/js/bootstrap.min.js"></script>
    </body>
</body>
</html>
