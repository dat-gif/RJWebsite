<%-- 
    Document   : CandidateProfile
    Created on : Feb 19, 2022, 4:27:48 PM
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
        <title>Profile</title>
    </head>
    <body>
        <!-- Header.jsp -->
        <header>
            <jsp:include page="component/Header.jsp"/>
        </header>

        <main style="margin-top: 4.5rem">
            <div style="background: white; margin-bottom: 1rem;padding-bottom: 1rem; padding-top: 0.5rem;">
                <!-- Profile header -->
                <div class="container">
                    <!-- Banner -->
                    <div style="height: 20rem;margin-bottom: 1rem">
                        <img src="https://picsum.photos/1000/300" style="width:100%; height:100% ; border: 5px; object-fit: cover;" class="mb-3" alt="Responsive image">
                    </div>
                    <!-- Header -->
                    <div class="d-flex justify-content-between" style="max-height: 160px">
                        <div class="d-flex justify-content-center py-0" style="height: inherit">
                            <image src="https://picsum.photos/300/300"  class="rounded-circle shadow" style="height: 14rem;position: relative; top: -4rem; left: 1rem"/>
                            <i class="bi bi-archive"></i>
                            <div class="mt-3 ml-5" style="max-height: 152px">   
                                <h2 class="display-5 font-weight-normal" style="position: relative; right: 0.2rem" >${name}- 123</h2>
                            </div>
                        </div>                   
                        <form method="" action="">                             
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
            <div class="container">
                <div class="row justify-content-between">
                    <!-- Left -->
                    <div class="col-9">
                        <!-- Intro -->
                        <div style="padding: 2.5rem 3rem" class="bg-light mb-2">
                            <div class="d-flex flex-row justify-content-between">
                                <h4 class="mt-2 mb-2" >My CV</h4>
                                <button class="btn btn-outline-primary btn-sm" style="max-height: 2.6rem; min-width: 4rem">Add</button>
                            </div>
                            <div class="d-flex flex-row mt-3 justify-content-between">
                                <div class="d-flex flex-row mt-2">
                                    <div>
                                        <image src="https://picsum.photos/50/50" class="mt-2 mb-2" >
                                    </div>
                                    <div class="ml-4 mt-2">
                                        <h5>My CV</h5>
                                        <a href="#">some-link-test</a>
                                    </div>
                                </div>
                                <button class="btn btn-primary btn-sm" style="max-height: 2.3rem">Edit</button>
                            </div>
                        </div>

                        <!-- Education -->
                        <div style="padding: 2.5rem 3rem" class="bg-light mb-2">
                            <div class="d-flex flex-row justify-content-between">
                                <h4 class="mt-2 mb-2" >Education</h4>
                                <button class="btn btn-outline-primary btn-sm" style="max-height: 2.6rem; min-width: 4rem">Add</button>
                            </div>
                            <div class="d-flex flex-row mt-3 justify-content-between">
                                <div class="d-flex flex-row mt-2">
                                    <div>
                                        <image src="https://picsum.photos/50/50" class="mt-2 mb-2" >
                                    </div>                                 
                                    <!-- Edu list -->
                                    <div class="ml-4 mt-2">
                                        <h5 class="text-uppercase">FPT University</h5>
                                        <div style="font-size: 1rem; line-height: 1.6">
                                            <p class="lead mb-1">Majors: Somr thing</p>
                                            <p class="lead mb-1">From: 00/00/000</p>
                                            <p class="lead mb-1">Description: Somr thing</p>
                                        </div>
                                    </div>
                                </div>
                                <button class="btn btn-primary btn-sm" style="max-height: 2.3rem" >Edit</button>
                            </div>
                        </div>

                        <!-- Main Skill -->
                        <div style="padding: 2.5rem 3rem" class="bg-light mb-2">
                            <div class="d-flex flex-row justify-content-between">
                                <h4 class="mt-2 mb-2" >Skill</h4>
                                <button class="btn btn-outline-primary btn-sm" style="max-height: 2.6rem; min-width: 4rem">Add</button>
                            </div>
                            <div class="d-flex flex-row mt-3 justify-content-between">
                                <div class="d-flex flex-row mt-2">
                                    <!-- Skill list -->
                                    <div>
                                        <image src="https://picsum.photos/50/50" class="mt-2 mb-2" >
                                    </div>
                                    <div class="ml-4 mt-2">
                                        <h5 class="text-uppercase">Jaca</h5>
                                        <div style="font-size: 1rem; line-height: 1.6">
                                            <p class="lead mb-1">Description: Somr thing</p>
                                        </div>
                                    </div>
                                </div>
                                <button class="btn btn-primary btn-sm" style="max-height: 2.3rem">Edit</button>
                            </div>
                        </div>

                        <!-- Experience -->
                        <div style="padding: 2.5rem 3rem" class="bg-light mb-2">
                            <div class="d-flex flex-row justify-content-between">
                                <h4 class="mt-2 mb-2" >Experience</h4>
                                <button class="btn btn-outline-primary btn-sm" style="max-height: 2.6rem; min-width: 4rem">Add</button>
                            </div>
                            <div class="d-flex flex-row mt-3 justify-content-between">
                                <div class="d-flex flex-row mt-2">
                                    <div>
                                        <image src="https://picsum.photos/50/50" class="mt-2 mb-2" >
                                    </div>
                                    <!-- Experiecnce list -->
                                    <div class="ml-4 mt-2">
                                        <h5 class="text-uppercase">FPT University</h5>
                                        <div style="font-size: 1rem; line-height: 1.6">
                                            <p class="lead mb-1">Position: Somr thing</p>
                                            <p class="lead mb-1">From: 00/00/000</p>
                                            <p class="lead mb-1">Description: Somr thing</p>
                                        </div>
                                    </div>
                                </div>
                                <button class="btn btn-primary btn-sm" style="max-height: 2.3rem">Edit</button>
                            </div>
                        </div>

                        <!-- Project -->
                        <div style="padding: 2.5rem 3rem" class="bg-light mb-2">
                            <div class="d-flex flex-row justify-content-between">
                                <h4 class="mt-2 mb-2" >Project</h4>
                                <button class="btn btn-outline-primary btn-sm" style="max-height: 2.6rem; min-width: 4rem">Add</button>
                            </div>
                            <div class="d-flex flex-row mt-3 justify-content-between">
                                <div class="d-flex flex-row mt-2">
                                    <div>
                                        <image src="https://picsum.photos/50/50" class="mt-2 mb-2" >
                                    </div>
                                    <!-- Project list -->
                                    <div class="ml-4 mt-2">
                                        <h5 class="text-uppercase">FPT University</h5>
                                        <div style="font-size: 1rem; line-height: 1.6">
                                            <p class="lead mb-1">Position: Somr thing</p>
                                            <p class="lead mb-1">From: 00/00/000</p>
                                            <p class="lead mb-1">Description: Somr thing</p>
                                        </div>
                                        <image src="https://picsum.photos/450/300" class="mt-2 mb-2" >
                                    </div>

                                </div>
                                <button class="btn btn-primary btn-sm" style="max-height: 2.3rem">Edit</button>
                            </div>
                        </div>
                        <!-- Certificate -->
                        <div style="padding: 2.5rem 3rem" class="bg-light mb-2">
                            <div class="d-flex flex-row justify-content-between">
                                <h4 class="mt-2 mb-2" >Certificate</h4>
                                <button class="btn btn-outline-primary btn-sm" style="max-height: 2.6rem; min-width: 4rem">Add</button>
                            </div>
                            <div class="d-flex flex-row mt-3 justify-content-between">
                                <div class="d-flex flex-row mt-2">
                                    <div>
                                        <image src="https://picsum.photos/50/50" class="mt-2 mb-2" >
                                    </div>
                                    <!-- Certificate list -->
                                    <div class="ml-4 mt-2">
                                        <h5 class="text-uppercase">FPT University</h5>
                                        <div style="font-size: 1rem; line-height: 1.6">
                                            <p class="lead mb-1">Organization: Somr thing</p>
                                            <p class="lead mb-1">Date: 00/00/000</p>
                                            <p class="lead mb-1">Link: </p>
                                        </div>
                                        <image src="https://picsum.photos/450/300" class="mt-2 mb-2" >
                                    </div>
                                </div>
                                <button class="btn btn-primary btn-sm" style="max-height: 2.3rem">Edit</button>
                            </div>
                        </div>
                        <hr>
                        <!-- Prize -->
                        <div style="padding: 2.5rem 3rem" class="bg-light mb-2">
                            <div class="d-flex flex-row justify-content-between">
                                <h4 class="mt-2 mb-2" >Prize</h4>
                                <button class="btn btn-outline-primary btn-sm" style="max-height: 2.6rem; min-width: 4rem">Add</button>
                            </div>
                            <div class="d-flex flex-row mt-3 justify-content-between">
                                <div class="d-flex flex-row mt-2">
                                    <div>
                                        <image src="https://picsum.photos/50/50" class="mt-2 mb-2" >
                                    </div>
                                    <!-- Prize -->
                                    <div class="ml-4 mt-2">
                                        <h5 class="text-uppercase">FPT University</h5>
                                        <div style="font-size: 1rem; line-height: 1.6">
                                            <p class="lead mb-1">Organization: Somr thing</p>
                                            <p class="lead mb-1">From: 00/00/000</p>
                                        </div>
                                    </div>
                                </div>
                                <button class="btn btn-primary btn-sm" style="max-height: 2.3rem">Edit</button>
                            </div>
                        </div>
                        <!-- Social Activities -->
                        <div style="padding: 2.5rem 3rem" class="bg-light mb-2">
                            <div class="d-flex flex-row justify-content-between">
                                <h4 class="mt-2 mb-2" >Social Activities</h4>
                                <button class="btn btn-outline-primary btn-sm" style="max-height: 2.6rem; min-width: 4rem">Add</button>
                            </div>
                            <div class="d-flex flex-row mt-3 justify-content-between">
                                <div class="d-flex flex-row mt-2">
                                    <div>
                                        <image src="https://picsum.photos/50/50" class="mt-2 mb-2" >
                                    </div>
                                    <!-- Social list -->
                                    <div class="ml-4 mt-2">
                                        <h5 class="text-uppercase">FPT University</h5>
                                        <div style="font-size: 1rem; line-height: 1.6">
                                            <p class="lead mb-1">Organization:: Somr thing</p>
                                            <p class="lead mb-1">Date: 00/00/000</p>
                                        </div>
                                    </div>
                                </div>
                                <button class="btn btn-primary btn-sm" style="max-height: 2.3rem">Edit</button>
                            </div>
                        </div>
                    </div>


                    <!-- Right -->
                    <div class="col-4 bg-light"style="width: 25.0%;
                         flex: 0 0 25.0%;max-width: 25.0%; height: fit-content; padding-bottom: 1rem; background-color: white" >
                        <div class="pt-3 pl-2">
                            <h6 class="mt-2 mb-2 text-capitalize" >Personal Information</h6>
                            <div class="text-break my-3 text-secondary" >
                                <p class="mb-1">Email: Somr thing</p>
                            </div>
                            <div class="text-break my-3 text-secondary" >
                                <p class="mb-1">Phone number: Somr thing</p>
                            </div>
                            <div class="text-break my-3 text-secondary" >
                                <p class="mb-1">Address: Somr thing laskjdfhlkasjdfhlkj kasjdhflkasjdhf  asdjkfh lhlaskjdfh a sdfjkhalskdjfh lS</p>
                            </div>
                            <div class="text-break my-3 text-secondary" >
                                <p class="mb-1">Birthday: Somr thing</p>
                            </div>
                            <div class="text-break my-3 text-secondary" >
                                <p class="mb-1">Gender: Somr thing</p>
                            </div>


                        </div>
                        <button class="btn btn-primary btn-sm float-right px-2" style="max-height: 2.5rem; min-width: 4rem">Change Info</button>
                    </div>
                </div>
            </div>
            <footer> <jsp:include page="component/Footer.jsp"/></footer>
            <script src="bootstrap-4.6.1-dist/js/bootstrap.min.js"></script>
        </main>
    </body>
</html>
