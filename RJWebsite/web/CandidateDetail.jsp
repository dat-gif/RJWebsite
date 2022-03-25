<%-- 
    Document   : CandidateDetail
    Created on : Mar 16, 2022, 1:40:52 PM
    Author     : USE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="component/HiepHeader.jsp"/>
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
                            <h2 class="display-5 font-weight-normal" style="position: relative; right: 0.2rem">
                                ${candidate.getFirstName()} ${candidate.getLastName()} 
                            </h2>                          
                        </div>
                    </div>                                      
                </div>
                <div class="row justify-content-between">
                    <div class="left col-9 ">
                        <div style="padding: 2.5rem 3rem" class="bg-light mb-2">
                            <div class="d-flex flex-row justify-content-between">
                                <h4 class="mt-2 mb-2" >CV</h4>
                            </div>

                            <div class="w-100">
                                <div class="d-flex flex-row mt-2">
                                    <div>
                                        <img src="https://picsum.photos/50/50" class="m-2" >
                                    </div>
                                    <div class="ml-5 mt-2">
                                        <c:if test="${not empty cvLink}">
                                            <p class="text-secondary mb-1">Link CV: <a href="https://www.topcv.vn/xem-cv/Bg5VAQFSBQYGDFFQDgBXUQRSAgBWA1UFAVReAA82c6">https://www.topcv.vn/xem-cv/Bg5VAQFSBQYGDFFQDgBXUQRSAgBWA1UFAVReAA82c6</a></p>          
                                        </c:if>                           
                                    </div>
                                </div>
                            </div>
                            <div class="right">
                                <div class="col-4 bg-light"style="width: 25.0%;
                                     flex: 0 0 25.0%;max-width: 25.0%; padding-bottom: 1rem;height: fit-content ;background-color: white" >
                                    <div class="pt-3 pl-2">
                                        <h6 class="mt-2 mb-2 text-capitalize" >Personal Information</h6>
                                        <div class="text-break my-3 text-secondary" >
                                            <p class="mb-1">Phone number: ${candidate.getPhone()}</p>
                                        </div>
                                        <div class="text-break my-3 text-secondary" >
                                            <p class="mb-1">Gender: Male</p>
                                        </div>
                                        <div class="text-break my-3 text-secondary" >
                                            <p class="mb-1">City: ${candidate.getCity()}</p>
                                        </div>
                                        <div class="text-break my-3 text-secondary" >
                                            <p class="mb-1">Birthday: ${candidate.getBirthDate()}</p>
                                        </div>                               
                                    </div>
                                </div>
                            </div>
                        </div>                    
                    </div>
                    <div class="col-3">
                        <div class="skill border border-dark rounded">
                            <p class="fw-bold m-3">Skill</p>                          
                            <p class="smallDetail text-center">Angular</p>                            
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="component/Footer.jsp"/>
    </body>
</html>
