<%-- 
    Document   : CandidateProfile
    Created on : Feb 19, 2022, 4:27:48 PM
    Author     : Nguyen Dinh Dat
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
        <link href="css/root.css" media="all" type="text/css" rel="stylesheet">
        <title>Profile</title>
    </head>
    <body>
        <style>
            #btnControl {
                display: none;
            }
            #btnControl:checked + label > img {
                width: 36vw;
                height:auto;
            }
        </style>
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
                                <h2 class="display-5 font-weight-normal" style="position: relative; right: 0.2rem" >
                                    ${candidateInfo.getFirstName()} ${candidateInfo.getLastName()} 
                                </h2>
                                <c:if test="${empty candidateInfo.getFirstName()}">
                                    <h2 class="display-5 font-weight-normal" style="position: relative; right: 0.2rem" >
                                        ${candidateInfo.getEmail()}
                                    </h2>  
                                </c:if>
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
                                <button class="btn btn-outline-primary btn-sm" style="max-height: 2.6rem; min-width: 4rem" data-toggle="modal" data-target="#cvModal">Edit</button>
                            </div>

                            <div class="flex-column w-100">
                                <div class="d-flex flex-row mt-2">
                                    <div>
                                        <image src="https://picsum.photos/50/50" class="mt-2 mb-2" >
                                    </div>
                                    <div class="ml-4 mt-2">
                                        <h5>My CV</h5>
                                        <c:if test="${not empty cvLink}">
                                            <p class="text-secondary mb-1">Link CV: </p>
                                            <a href="${cvLink}" class="text-truncate">${cvLink}</a>
                                        </c:if>

                                    </div>
                                </div>

                                <input type="checkbox" id="btnControl"/>
                                <label class="btn" for="btnControl">                            
                                    <img src="${imgDecode}" alt="alt" class="img" style="max-width: 730px" id="btnLeft"/>
                                </label>
                                <p class="text-primary small font-italic font-weight-light">*Try click on to zoom image</p>
                            </div>

                            <!-- CV Modal -->
                            <div class="modal " id="cvModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title text-center text-capitalize" id="exampleModalLabel">My CV</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <form action="updatecv" method="POST"  enctype="multipart/form-data">
                                            <div class="modal-body">
                                                <div class="form-group">
                                                    <label for="recipient-name" class="col-form-label">Upload CV:</label>
                                                    <input type="file" name="file" class="ml-2 align-content-center" id="recipient-name" accept="image/*">
                                                    <p class="text-danger small font-italic font-weight-light">*Please choose image under 5 MB</p>
                                                    <input type="text" class="form-control" name="cvLink" placeholder="Link CV if you has..." />
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                <button type="submit" class="btn btn-primary">Save changes</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
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
                                    <c:choose>
                                        <c:when test="${not empty eduList}">
                                            <div class="flex-column">
                                                <c:forEach items="${eduList}" var="edu">
                                                    <div class="ml-4 mt-2">
                                                        <h5 class="text-uppercase">${edu.getSchool()}</h5>
                                                        <div style="font-size: 1rem; line-height: 1.6">
                                                            <p class="lead mb-1">Majors: ${edu.getField()}</p>
                                                            <p class="lead mb-1">Degree: ${edu.getDegree()}</p>
                                                            <p class="lead mb-1">From: ${edu.getStartTime()} - ${edu.getEndTime()}</p>
                                                            <p class="lead mb-1">Description: ${edu.getDescription()}</p>
                                                        </div>
                                                        <hr>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <h5 class="text-uppercase">List still empty</h5>
                                        </c:otherwise>
                                    </c:choose>


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
                                <div class="d-flex flex-column">
                                    <c:choose>
                                        <c:when test="${not empty skillList}">
                                            <c:forEach items="${skillList}" var="skill">
                                                <div class="d-flex flex-row">
                                                    <!-- Skill list -->
                                                    <div>
                                                        <image src="${skill.getIconBase64()}" class="mt-2 mb-2" style="width: 50px;height: 50px" >
                                                    </div>
                                                    <div class="ml-4 " style="max-width: 40rem;">
                                                        <h5 class="text-uppercase">${skill.getName()}</h5>
                                                        <div style="font-size: 1rem; line-height: 1.6">
                                                            <p class="lead mb-1  ">Description: ${skill.getDepscription()}</p>
                                                        </div>
                                                    </div>
                                                </div>             
                                                <hr style="border-bottom: solid 0.5px #dfe0e1; width: 80%">
                                            </c:forEach>                
                                        </c:when>
                                        <c:otherwise>
                                            <h5 class="text-uppercase">List still empty</h5>
                                        </c:otherwise>
                                    </c:choose>
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
                                    <div class="d-flex flex-column">
                                        <c:forEach items="${expList}" var="exp">
                                            <div class="ml-4 mt-2">
                                                <h5 class="text-uppercase">${exp.getCompanyName()}</h5>
                                                <div style="font-size: 1rem; line-height: 1.6">
                                                    <p class="lead mb-1">Position: ${exp.getWorkingRole()}</p>
                                                    <p class="lead mb-1">From: ${exp.getStartTime()}-${exp.getStartTime()}</p>
                                                    <p class="lead mb-1">Description:<br> ${exp.getDescription()}</p>
                                                </div>
                                                <hr>
                                            </div>   
                                        </c:forEach>
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
                                    <c:forEach items="${projectList}" var="project">

                                        <div class="ml-4 mt-2">
                                            <h5 class="text-uppercase">${project.getName()}</h5>
                                            <div style="font-size: 1rem; line-height: 1.6">
                                                <p class="lead mb-1 text-capitalize">Position: ${project.getRole()}</p>
                                                <p class="lead mb-1">From: ${project.getStartTime()} - ${project.getEndTime()} </p>
                                                <p class="mb-1 text-secondary" style="font-size: 0.99rem">
                                                    Description: ${project.getDescription()}</p>
                                            </div>
                                            <c:if test="${not empty project.getMedia()}">
                                                <image src="${project.getMedia()}" class="mt-2 mb-2" style="width: 470px; height: 300px">
                                            </c:if>
                                        </div>
                                    </c:forEach>
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
                                    <div class="d-flex flex-column">
                                        <c:forEach items="${certList}" var="cert">
                                            <div class="ml-4 mt-2">
                                                <h5 class="text-uppercase">${cert.getName()}</h5>
                                                <div style="font-size: 1rem; line-height: 1.6">
                                                    <p class="lead mb-1">Organization: ${cert.getHost()}</p>
                                                    <p class="lead mb-1">Date: ${cert.getCertificateTime()}</p>
                                                    <a class="lead mb-1" href="${cert.getLink()}">Link: ${cert.getLink()}</a>
                                                </div>
                                                <image src="${cert.getMedia()}" class="mt-2 mb-2" style="width: 34rem; height: fit-content"  >
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                                <button class="btn btn-primary btn-sm" style="max-height: 2.3rem">Edit</button>
                            </div>
                        </div>
                        <hr>


                    </div>


                    <!-- Right -->
                    <div class="col-4 bg-light"style="width: 25.0%;
                         flex: 0 0 25.0%;max-width: 25.0%; padding-bottom: 1rem;height: fit-content ;background-color: white" >
                        <div class="pt-3 pl-2">
                            <h6 class="mt-2 mb-2 text-capitalize" >Personal Information</h6>
                            <div class="text-break my-3 text-secondary" >
                                <p class="mb-1">Email: ${candidateInfo.getEmail()}</p>
                            </div>
                            <div class="text-break my-3 text-secondary" >
                                <p class="mb-1">Phone number: ${candidateInfo.getPhone()}</p>
                            </div>
                            <div class="text-break my-3 text-secondary" >
                                <p class="mb-1">Address: ${candidateInfo.getAddress()}</p>
                            </div>
                            <div class="text-break my-3 text-secondary" >
                                <p class="mb-1">Birthday: ${candidateInfo.getBirthDate()}</p>
                            </div>
                            <div class="text-break my-3 text-secondary" >
                                <c:choose>
                                    <c:when test="${candidateInfo.isGender()}">
                                        <p class="mb-1">Gender: Male</p>
                                    </c:when>
                                    <c:when test="${!candidateInfo.isGender()}">
                                        <p class="mb-1">Gender: Female</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="mb-1">Gender: Unknown</p>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <button class="btn btn-primary btn-sm float-right px-2" style="max-height: 2.5rem; min-width: 4rem">
                            Change Info
                        </button>
                    </div>
                </div>
            </div>
            <footer> <jsp:include page="component/Footer.jsp"/></footer>
            <script src="bootstrap-4.6.1-dist/js/bootstrap.min.js"></script>
        </main>
    </body>
</html>