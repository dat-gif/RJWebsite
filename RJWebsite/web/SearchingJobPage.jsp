<%-- 
    Document   : SearchingJobPage
    Created on : Jan 18, 2022, 4:08:27 PM
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
        <title>Searching Job</title>
    </head>
    <body>
        <header> 
            <jsp:include page="component/Header.jsp"/>

        </header>
        <hr style="margin: 2rem">
        <main class="container my-3">
            <!-- Searching -->

            <div class="row row-cols-2 ">
                <div class="col align-self-center"> 
                    <h3 class="text-capitalize display-4 mb-4">Searching for IT job...</h3>
                    <!-- Searching Form -->
                    <form action="seachingjob" method="POST">
                        <div class="input-group">
                            <input type="search" name="txtSearch" value="${txtSearch}" class="form-control  rounded border-secondary" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
                            <button type="submit" class="btn btn-outline-primary mx-3">search</button>
                        </div>
                        <div class="d-flex mt-3 ">
                            <select name="citySelect" class="form-select mr-2 border-secondary rounded w-25 form-control " value="${citySelect}">
                                <c:choose>
                                    <c:when test="${not empty citySelect}">
                                        <option selected="selected" value="${citySelect}" >
                                            ${citySelect}
                                        </option>    
                                    </c:when>
                                    <c:otherwise>
                                        <option selected="selected" value="All" >
                                            Location (All)
                                        </option>           
                                    </c:otherwise>
                                </c:choose>
                                <c:forEach items="${listCity}" var="city">
                                    <option value="${city.getName()}">${city.getName()}</option>
                                </c:forEach>
                            </select>    

                            <select name="skillSelect" class="form-select rounded border-secondary rounded w-25 form-control" value="${skillSelectId}">
                                <c:choose>
                                    <c:when test="${not empty skillSelectId}">
                                        <option selected="selected" value="${skillSelectId}" >
                                            ${skillSelect}
                                        </option>    
                                    </c:when>
                                    <c:otherwise>
                                        <option selected value="All_All Skill" >
                                            All Skill
                                        </option>   
                                    </c:otherwise>
                                </c:choose>
                                <option value="All_All-Skill" >
                                    All Skill
                                </option>   
                                <c:forEach items="${listSkill}" var="skill">
                                    <option value="${skill.getId()}_${skill.getName()}">${skill.getName()}</option>
                                </c:forEach>
                            </select>   
                        </div>
                    </form>      
                </div>
                <div class="col p-0 align-self-center">
                    <img src="https://picsum.photos/500/300" class="img-fluid w-100 " alt="alt"/>
                </div>
            </div>
            <hr class="my-4"/>

            <!-- Result -->
            <div class="row row-cols-4 align-content-center ">
                <c:choose>
                    <c:when test="${listJob.size() >0}">
                        <c:forEach items="${listJob}" var="job"> 
                            <div class="col p-1 mt-1">
                                <% request.setCharacterEncoding("utf-8");%>
                                <jsp:include page="component/CardInfo.jsp">
                                    <jsp:param name="title1" value="${job.getTitle()}"/>
                                    <jsp:param name="title2" value="${job.getRecruiter().getName()}"/>
                                    <jsp:param name="arraySkill" value="${job.skillListName}"/>
                                    <jsp:param name="text2" value="${job.salaryRange}"/>
                                    <jsp:param name="img" value="${job.getRecruiter().getAvatar()}"/>
                                    <jsp:param name="link" value="jobdetail?jobId=${job.getjId()}"/>
                                </jsp:include>
                            </div>
                        </c:forEach>                  
                    </c:when>
                    <c:otherwise>
                        <p class="text-left mt-3 text-secondary lead" style="font-size: 2rem">Sorry, data is no found.</p>
                        <section class="text-left mt-3 mx-auto text-secondary lead">
                            <p>Suggestion:</p>
                            <ul>
                                <li>Please make sure all words are spelled correctly.</li>
                                <li>Try other keywords.</li>
                                <li>Try different filter info (different city, skill).</li>
                                <li>Try removing keywords.</li>
                            </ul>
                        </section>
                    </c:otherwise>
                </c:choose>

            </div>
            <!-- Phan trang -->
            <div class="row justify-content-center mt-4 mb-4">
                <nav aria-label="...">
                    <ul class="pagination">

                        <c:forEach begin="1" end="${totalPage}" var="i">
                            <li class="page-item" value="${page}"  name="page">
                                <a class="page-link" href="seachingjob?page=${i}" >${i}</a>
                            </li>    
                        </c:forEach>

                    </ul>
                </nav>
            </div>

        </main>
        <!-- Footer -->
        <jsp:include page="component/Footer.jsp"/>
        <script src="bootstrap-4.6.1-dist/js/bootstrap.min.js"></script>
    </body>
</html>
