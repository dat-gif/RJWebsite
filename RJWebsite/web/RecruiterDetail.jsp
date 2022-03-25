<%-- 
    Document   : DataManagement
    Created on : Feb 7, 2022, 11:50:28 AM
    Author     : USE
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/RecruiterDetail.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="component/HiepHeader.jsp"/>
        <div class="top">
            <img src="${recruiter.getAvatar()}" alt="">
            <div class="infor">
                <h2>${recruiter.getName()}</h2>
                <p>CMC GLOBAL</p>
                <p>Create date: ${recruiter.getCreateAt()}</p>
            </div>           
        </div>
        <div class="content">
            <div class="left">     
                <div class="information"><a>Information</a></div>
                <div class="candidate"><a>Candidate</a></div>

                <div id="informationWrap" class="informationWrap">
                    <h3 class="m-3">Detail Information</h3>    
                    <div class="generalInfor p-3">
                        <p>General Information</p>
                        <table>
                            <tr>
                                <td><i class="fa-solid fa-address-card"></i></td>
                                <td>Contact Name</td>
                                <td><i class="fas fa-user-friends"></i></td>
                                <td>Number of recruits</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>${recruiter.getContacterName()}</td>
                                <td></td>
                                <td>${recruiter.getEmployeeQuantity()}</td>
                            </tr>
                            <tr>
                                <td><i class="fa-solid fa-phone-office"></i></td>
                                <td>Contact Number</td>
                                <td><i class="fa-solid fa-phone"></i></td>
                                <td>Recruiter Number</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>${recruiter.getContacterPhone()}</td>
                                <td></td>
                                <td>${recruiter.getPhone()}</td>
                            </tr>
                        </table>
                    </div>
                    <div class="locationInfor p-3">
                        <p>Location</p>
                        <table>
                            <tr>
                                <td><i class="fas fa-map-marked-alt"></i></td>
                                <td>${recruiter.getAddress()}</td>
                            </tr>
                        </table>  
                    </div> 
                    <div class="description p-3">
                        <p>${recruiter.getDescription()}</p>
                    </div>
                </div>
                <div id="candidateWrap" class="candidateWrap">
                    <div class="candidateList">
                        <h3 class="m-3">Candidate list waiting to apply</h3>
                        <c:if test="${empty listCandidate}">
                            <h2 class="m-auto" style="color: green;">No candidate need to get approved</h2>
                        </c:if>
                        <c:if test="${not empty listCandidate}">
                            <table>
                                <thead>
                                <td>Full Name</td>
                                <td>Location</td>
                                <td>Profile</td>
                                <td>Status</td>
                                <td>Action</td>
                                </thead>
                                <c:forEach items="${listCandidate}" var="candidate">
                                    <tr>
                                        <td>${candidate.getFirstName()} ${candidate.getLastName()}</td>
                                        <td>${candidate.getAddress()}</td>
                                        <td><a href="CandidateDetailController?candidateId=1">View Profile</a></td>
                                        <td>${candidate.getJobStatus()}</td>
                                        <td><a href="ChangeStatusApplyController?candidateId=1&&jobId=${recruiter.getRecruiterId()}"><button class="btn btn-primary">Change</button></a></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:if>
                    </div>
                </div>           
            </div>          
            <div class="right">
                <div class="skill border border-dark rounded">
                    <p class="fw-bold">Skill</p>
                    <c:forEach items="${listSkill}" var="skill">
                        <p class="smallDetail">${skill.getName()}</p>      
                    </c:forEach>                        
                </div>
            </div>
        </div>
        <script>
<!-- co 2 tab information va candidateList. Khi click tab nay se display thong tin cua tab do va an di tab con lai-->
            document.querySelector(".candidate").addEventListener("click", () => {
                document.querySelector(".informationWrap").style.display = "none";
                document.querySelector(".candidateWrap").style.display = "block";
                document.querySelector(".candidate").style.backgroundColor = "#D8D8D8";
                document.querySelector(".information").style.backgroundColor = "white";
            })
            document.querySelector(".information").addEventListener("click", () => {
                document.querySelector(".informationWrap").style.display = "block";
                document.querySelector(".candidateWrap").style.display = "none";
                document.querySelector(".candidate").style.backgroundColor = "white";
                document.querySelector(".information").style.backgroundColor = "#D8D8D8";
            })
        </script>
        <jsp:include page="component/Footer.jsp"/>
    </body>
</html>
