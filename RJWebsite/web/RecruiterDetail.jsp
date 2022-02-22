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
                    <h3>Detail Information</h3>    
                    <div class="generalInfor">
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
                    <div class="locationInfor">
                        <p>Location</p>
                        <table>
                            <tr>
                                <td><i class="fas fa-map-marked-alt"></i></td>
                                <td>${recruiter.getAddress()}</td>
                            </tr>
                        </table>  
                    </div> 
                    <div class="description">
                        <p>${recruiter.getDescription()}</p>
                    </div>
                </div>
                <div id="candidateWrap" class="candidateWrap">
                    <div class="candidateList">
                        <h3>Candidate List</h3>
                        <table>
                            <thead>
                            <td>Full Name</td>
                            <td>Location</td>
                            <td>Profile</td>
                            <td>Status</td>
                            </thead>
                            <c:forEach items="${listCandidate}" var="candidate">
                                <tr>
                                    <td>${candidate.getFirstName()} ${candidate.getLastName()}</td>
                                    <td>${candidate.getAddress()}</td>
                                    <td><a href="#">View Profile</a></td>
                                    <td>${candidate.getJobStatus()}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>           
            </div>          
            <div class="right">
                <div class="skill">
                    <p>Skill</p>
                    <c:forEach items="${listSkill}" var="skill">
                        <p class="smallDetail">${skill.getName()}</p>      
                    </c:forEach>                        
                </div>
                <div class="location">
                    <p>Location</p>
                    <p class="smallDetail">${recruiter.getCity()}</p>
                </div>
            </div>
        </div>
        <script>
            document.querySelector(".candidate").addEventListener("click", () => {
                document.querySelector(".informationWrap").style.display = "none";
                document.querySelector(".candidateWrap").style.display = "block";
                document.querySelector(".candidate").style.backgroundColor = "white";
                document.querySelector(".information").style.backgroundColor = "#D8D8D8";
            })
            document.querySelector(".information").addEventListener("click", () => {
                document.querySelector(".informationWrap").style.display = "block";
                document.querySelector(".candidateWrap").style.display = "none";
                document.querySelector(".candidate").style.backgroundColor = "#D8D8D8";
                document.querySelector(".information").style.backgroundColor = "white";
            })
        </script>
        <jsp:include page="component/HiepFooter.jsp"/>
    </body>
</html>
