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
        <title>Apply Job</title>
    </head>
    <body>
        <header>
            <jsp:include page="component/Header.jsp">  
                <jsp:param name="account" value="${account}"/>
            </jsp:include>
        </header>
        <main class="container">
            <h3 class="text-capitalize display-5 mb-4" style="margin-top: 5rem">Job you have applied for...</h3>
            <div class="row row-cols-4 align-content-center ">
                <c:choose>
                    <c:when test="${listApplyJob.size() >0 }">
                        <c:forEach items="${listApplyJob}" var="job">
                            <div class="col p-1 my-2 px-3">
                                <jsp:include page="component/ApplyJobCard.jsp">
                                    <jsp:param name="title1" value="${job.getTitle()}"/>
                                    <jsp:param name="title2" value="${job.recruiter.getName()}"/>
                                    <jsp:param name="arraySkill" value="${job.skillListName}"/>
                                    <jsp:param name="text1" value="${job.getExperience()}"/>
                                    <jsp:param name="text2" value="${job.getSalaryRange()}"/>
                                    <jsp:param name="text3" value="End date: ${job.getHireDate()}"/>
                                    <jsp:param name="status" value="${job.getApplyStatus()}"/>
                                    <jsp:param name="link" value="jobdetail?jobId=${job.getjId()}"/>
                                </jsp:include>
                            </div>
                        </c:forEach>                
                    </c:when>
                    <c:otherwise>
                        <p class="text-center mt-3 mx-auto text-secondary lead" style="font-size: 2rem">Apply list is still empty</p>
                    </c:otherwise>
                </c:choose>

            </div>
            <!-- Phan trang -->
            <div class="row justify-content-center mt-4 mb-4">
                <nav aria-label="...">
                    <ul class="pagination">

                        <c:forEach begin="1" end="${totalPage}" var="i">
                            <li class="page-item" value="${page}"  name="page">
                                <a class="page-link" href="candidateapplyjobpage?page=${i}" >${i}</a>
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
