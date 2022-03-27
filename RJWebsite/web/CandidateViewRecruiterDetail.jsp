<%-- 
    Document   : ViewJobDetaiilPage
    Created on : Jan 20, 2022, 2:35:23 PM
    Author     : Admin
--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap-4.6.1-dist/css/bootstrap.min.css" media="all" type="text/css" rel="stylesheet">
        <script src="bootstrap-4.6.1-dist/jQuery/jquery-3.6.0.min.js"></script>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/root.css" media="all" type="text/css" rel="stylesheet">
        <title>Company - ${name}</title>
    </head>

    <body>
        <header>
            <jsp:include page="component/Header.jsp">  
                <jsp:param name="account" value="${account}"/>
            </jsp:include>
        </header>
        <main style="margin-top: 4.5rem">
            <!-- Header -->
            <div style="background: white; margin-bottom: 1rem;padding-bottom: 1rem; padding-top: 0.5rem;">
                <div class="container">
                    <!-- Banner -->
                    <div style="height: 20rem;margin-bottom: 1rem">
                        <img src="${banner}" style="width:100%; height:100% ; border: 5px; object-fit: cover;" class="mb-3" alt="Responsive image">
                    </div>
                    <!-- Header -->
                    <div class="d-flex justify-content-between">
                        <div class="d-flex justify-content-center py-1">
                            <image src="${avatar}" class="rounded-circle shadow" style="height: 12rem"/>
                            <i class="bi bi-archive"></i>
                            <div class="mt-5 ml-5">   
                                <h2 class="display-5 font-weight-normal" style="position: relative; right: 0.2rem" >${name}</h2>
                                <c:choose>
                                    <c:when test="${fn:length(website) gt 50}">
                                        <a class="font-weight-light h4 text-truncate" style="cursor: pointer">${website}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="font-weight-light h4" style="cursor: pointer">${website}</a>
                                    </c:otherwise>
                                </c:choose>
                                <h4 class="font-weight-light mt-1">${qEmployee}</h4>
                            </div>
                        </div>                   
                        <form method="post" action="candidateviewrecruiterdetail">
                            <c:choose>
                                <c:when test="${followButton eq 'Follow'}">
                                    <button class="btn btn-primary align-self-baseline mt-2 btn-lg">${followButton}</button>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn btn-outline-primary align-self-baseline mt-2 btn-lg">${followButton}</button>
                                </c:otherwise>
                            </c:choose>  
                        </form>
                    </div>
                </div>
            </div>

            <!-- Body -->
            <div class="container" >
                <div class="row justify-content-between">
                    <!-- Left -->
                    <div class="col-9">
                        <!-- Intro -->
                        <div style="padding: 2.5rem 3rem" class="bg-light mb-2">
                            <h4 class="mt-2 mb-2" >Company Introduction</h4>
                            <p class="text-justify">${description} </p>
                        </div>
                        <!-- Recruitment -->
                        <div style="padding: 2.5rem 3rem" class="bg-light mb-2" id="joblist">
                            <h4 class="mt-2 mb-2" >Recruitment</h4>
                            <!-- Job card -->
                            <c:forEach items="${jobList}" var="job">

                                <div class="card d-flex flex-row flex-wrap align-content-center p-1 mt-2"  >
                                    <div style="max-width: 25%; height: 7rem" class="p-2 ml-4 " >
                                        <img class="card-img img-thumbnail" style="width:100%; height:100% ; border: 5px; object-fit: cover;" 
                                             src="${job.getRecruiter().getAvatar()}" alt="Card image cap">
                                    </div>
                                    <div style="height: inherit; overflow-wrap: break-word" class="ml-4 d-flex flex-column justify-content-center">
                                        <a class="card-title m-0 text-capitalize" style="font-size: 1.3rem;cursor: pointer" href="jobdetail?jobId=${job.getjId()}">
                                            ${job.getTitle()}
                                        </a>
                                        <p class="card-title m-0 lead text-uppercase" style="font-size: 1rem">${job.getRecruiter().getName()}</p>
                                        <hr class="m-1">
                                        <div class="mt-2">
                                            <span class="mt-1 mr-2" style="background-color: #dae0e5;padding: 0.15rem 0.8rem">${job.getSalaryRange()}</span>
                                            <span class="mt-1 mr-2" style="background-color: #dae0e5;padding: 0.15rem 0.8rem">${job.getRole()}</span>
                                            <span class="mt-1 mr-2" style="background-color: #dae0e5;padding: 0.15rem 0.8rem">${job.getHireDate()}</span>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <!-- Pagening recruiment -->

                            <div class="row justify-content-center mt-4 mb-4">
                                <nav aria-label="...">
                                    <ul class="pagination">
                                        <c:forEach begin="1" end="${jobTotalPage}" var="i">
                                            <li class="page-item" value="${page}"  name="page">
                                                <a class="page-link" href="candidateviewrecruiterdetail?recruiterId=${recruiterId}&page=${i}#joblist" >${i}</a>
                                            </li>    
                                        </c:forEach>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>

                    <!-- Right -->
                    <div class="col-4 bg-light"style="width: 24.0%;
                         flex: 0 0 24.0%;max-width: 24.0%; height: fit-content; padding-bottom: 1rem; background-color: white" >
                        <div class="pt-3 pl-2">
                            <h6 class="mt-2 mb-2" >Address</h6>
                            <div class="text-break ">
                                <p><u>${address}</u></p>
                            </div>
                            <h6 class="mt-2 mb-2" >Hotline</h6>
                            <div class="text-break ">
                                <p><u>${phone}</u></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </main>
    <footer> <jsp:include page="component/Footer.jsp"/></footer>
    <script src="bootstrap-4.6.1-dist/js/bootstrap.min.js"></script>
</body>
</body>
</html>
