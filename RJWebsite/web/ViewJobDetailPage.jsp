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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap-4.6.1-dist/css/bootstrap.min.css" media="all" type="text/css" rel="stylesheet">
        <script src="bootstrap-4.6.1-dist/jQuery/jquery-3.6.0.min.js"></script>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Job Detail</title>
    </head>

    <body style="background-color: #dae0e5">
        <header ><jsp:include page="component/Header.jsp"/></header>
        <main style="margin-top: 5rem">
            <!-- detail head -->
            <div style="background: white; margin-top: 1rem; margin-bottom: 1rem;padding-top: 2rem; padding-bottom: 1rem">
                <div class="container">
                    <div class="d-flex justify-content-between">
                        <div class="d-flex justify-content-center">
                            <image src="https://picsum.photos/200" class="rounded-circle "/>
                            <div class="mt-5 ml-5">   
                                <h2 class="display-5 font-weight-normal" style="position: relative; right: 0.2rem" >${jobTile}</h2>
                                <a class="font-weight-light h4" style="cursor: pointer">${jobCompany}</a>
                                <h4 class="font-weight-light">End date : ${endDate}</h4>
                            </div>
                        </div>                   
                            <form method="post" action="jobdetail">
                                
                            <button class="btn btn-primary align-self-baseline mt-2 btn-lg">Apply</button>
                            </form>
                    
                    </div>
                </div>
            </div>
            <!-- detail body -->
            <div class="container" >
                <div class="row justify-content-between">
                    <!-- left -->
                    <div class="col-9 bg-light" style="padding: 2.5rem 3rem" >
                        <h4 class="my-4 " >Detail Information</h4>
                        <!-- job info table -->
                        <h5 class="font-weight-normal mb-2" >General Information</h5>
                        <div class="row row-cols-2 p-3 mx-1" style="background-color: #dae0e5">
                            <!-- salary -->
                            <div class="col d-flex flex-row align-content-center">
                                <image src="https://picsum.photos/40" class="rounded align-self-baseline mt-1 my-auto "/>
                                <div class="pt-2 ml-3 my-auto"> 
                                    <h6 class="text-nowrap m-0">Salary</h6>
                                    <p class="m-0">${salary}</p>
                                </div>
                            </div>
                            <!-- Number of recruits -->
                            <div class="col d-flex flex-row align-content-center">
                                <image src="https://picsum.photos/40" class="rounded align-self-baseline mt-1 my-auto "/>
                                <div class="pt-2 ml-3 my-auto"> 
                                    <h6 class="text-nowrap m-0">Number of recruits</h6>
                                    <p class="m-0">${quantity}</p>
                                </div>
                            </div>
                            <!-- Position -->
                            <div class="col d-flex flex-row align-content-center mt-2">
                                <image src="https://picsum.photos/40" class="rounded align-self-baseline mt-1 my-auto "/>
                                <div class="pt-2 ml-3 my-auto"> 
                                    <h6 class="text-nowrap m-0">Position</h6>
                                    <p class="m-0">${role}</p>
                                </div>
                            </div>
                            <!-- Experience -->
                            <div class="col d-flex flex-row align-content-center mt-2">
                                <image src="https://picsum.photos/40" class="rounded align-self-baseline mt-1 my-auto "/>
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
                        <div class="mt-3">
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
