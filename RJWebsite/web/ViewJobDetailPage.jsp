<%-- 
    Document   : ViewJobDetaiilPage
    Created on : Jan 20, 2022, 2:35:23 PM
    Author     : Admin
--%>

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
        <title>Job Detail - ${jobTile}</title>
        <link href="css/root.css" media="all" type="text/css" rel="stylesheet">
    </head>

    <body>
        <header ><jsp:include page="component/Header.jsp"/></header>
        <main style="margin-top: 4rem">
            <!-- Detail head -->
            <div style="background: white; margin-top: 1rem; margin-bottom: 1rem;padding-top: 2rem; padding-bottom: 1rem">
                <div class="container">
                    <div class="d-flex justify-content-between">
                        <div class="d-flex justify-content-center">
                            <image src="${avatar}" class="rounded-circle shadow" style="height: 12rem"/>
                            <i class="bi bi-archive"></i>
                            <div class="mt-5 ml-5">   
                                <h2 class="display-5 font-weight-normal" style="position: relative; right: 0.2rem" >${jobTile}</h2>
                                <a class="font-weight-light h4" style="cursor: pointer" href="candidateviewrecruiterdetail?recruiterId=${recruiterId}">${jobCompany}</a>
                                <h4 class="font-weight-light">End date : ${endDate}</h4>
                            </div>
                        </div>                   
                        <form method="post" action="jobdetail">
                            <c:choose>
                                <c:when test="${jobApplyButton eq 'Apply'}">

                                    <button class="btn btn-primary align-self-baseline mt-2 btn-lg">${jobApplyButton}</button>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn btn-outline-primary align-self-baseline mt-2 btn-lg">${jobApplyButton}</button>
                                </c:otherwise>
                            </c:choose>  
                        </form>
                    </div>
                </div>
            </div>
            <!-- detail body -->

            <div class="container" >
                <div class="row justify-content-between">
                    <!-- Left -->
                    <div class="col-9 bg-light" style="padding: 2.5rem 3rem" >
                        <h4 class="my-4 " >Detail Information</h4>
                        <!-- job info table -->
                        <h5 class="font-weight-normal mb-2" >General Information</h5>
                        <div class="row row-cols-2 p-3 mx-1" style="background-color: #dae0e5; height: 10rem">
                            <!-- salary -->
                            <div class="col d-flex flex-row align-content-center">
                                <i class="fa fa-money fa-2x align-self-center" aria-hidden="true"></i>
                                <div class="pt-2 ml-3 my-auto"> 
                                    <h6 class="text-nowrap m-0">Salary</h6>
                                    <p class="m-0">${salary}</p>
                                </div>
                            </div>
                            <!-- Number of recruits -->
                            <div class="col d-flex flex-row align-content-center">
                                <i class="fa fa-users fa-2x align-self-center" aria-hidden="true"></i>
                                <div class="pt-2 ml-3 my-auto"> 
                                    <h6 class="text-nowrap m-0">Number of recruits</h6>
                                    <p class="m-0">${quantity}</p>
                                </div>
                            </div>
                            <!-- Position -->
                            <div class="col d-flex flex-row align-content-center mt-2">
                                <i class="fa fa-address-card fa-2x align-self-center" aria-hidden="true"></i>
                                <div class="pt-2 ml-3 my-auto"> 
                                    <h6 class="text-nowrap m-0">Position</h6>
                                    <p class="m-0">${role}</p>
                                </div>
                            </div>
                            <!-- Experience -->
                            <div class="col d-flex flex-row align-content-center mt-2">
                                <i class="fa fa-suitcase fa-2x align-self-center" aria-hidden="true"></i>
                                <div class="pt-2 ml-3 my-auto"> 
                                    <h6 class="text-nowrap m-0">Experience</h6>
                                    <p class="m-0">${experience}</p>
                                </div>
                            </div>
                        </div>

                        <h5 class="mt-3 font-weight-normal mb-2" >Location</h5>
                        <div class="row pt-3 mx-1 text-center" style="background-color: #dae0e5">
                            <p class="text-capitalize ml-3 p-0">${location}</p>
                        </div>
                        <hr>
                        <div class="mt-3">
                            <h5 class="font-weight-normal mb-2" >Description</h5>
                            <p class="text-justify">${description}</p>
                            <h5 class="font-weight-normal mb-2" >Requirements</h5>
                            <p class="text-justify">${description}</p>
                            <h5 class="font-weight-normal mb-2" >Benefit</h5>
                            <p class="text-justify">${description}</p>

                        </div>
                    </div>

                    <div class="col-2 bg-light" style="width: 22.0%;
                         flex: 0 0 22.0%;max-width: 22.0%; height: fit-content; padding-bottom: 1rem" >
                        <div class="pt-3 px-2">
                            <p class="h6">Skill</p>
                            <div class=" text-break">
                                <c:forEach items="${skill}" var="job">
                                    <span class="mr-1"><mark>${job}</mark>, </span>
                                        </c:forEach>
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
