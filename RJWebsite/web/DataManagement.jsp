<%-- 
    Document   : DataManagement
    Created on : Feb 7, 2022, 11:50:28 AM
    Author     : USE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/DataManagement.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
    </head>
    <body>
        <jsp:include page="component/HiepHeader.jsp"/>
        <div class="top">
            <img src="https://png.pngtree.com/png-clipart/20200709/original/pngtree-technology-logo-template-png-image_3669874.jpg" alt="">
            <div class="infor">
                <h2>Data Management Officer</h2>
                <p>CMC GLOBAL</p>
                <p>End date: 24/1/2022</p>
            </div>           
            <button><a href="#">Edit</a></button>
        </div>
        <div class="content">
            <div class="left">
                <div class="header">
                    <div class="information"><a>Information</a></div>
                    <div class="candidate"><a>Candidate</a></div>
                </div> 
                <div id="informationWrap" class="informationWrap">
                    <h3>Detail Information</h3>    
                    <div class="generalInfor">
                        <p>General Information</p>
                        <table>
                            <tr>
                                <td><i class="fas fa-dollar-sign"></i></td>
                                <td>Salary</td>
                                <td><i class="fas fa-user-friends"></i></td>
                                <td>Number of recruits</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>2000</td>
                                <td></td>
                                <td>30</td>
                            </tr>
                            <tr>
                                <td><i class="fas fa-map-marker-alt"></i></td>
                                <td>Position</td>
                                <td><i class="fas fa-chart-bar"></i></td>
                                <td>Experience</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>Staff</td>
                                <td></td>
                                <td>2 years</td>
                            </tr>
                        </table>
                    </div>
                    <div class="locationInfor">
                        <p>Location</p>
                        <table>
                            <tr>
                                <td><i class="fas fa-map-marked-alt"></i></td>
                                <td>Hanoi, HochiMinh</td>
                            </tr>
                        </table>  
                    </div> 
                    <div class="description">
                        <p>Paragraph On Black Money – 200 Words for Classes 6, 7, 8 Students
                            Black money is a severe problem in almost every nation. In addition to the harsh economic issues caused by the deposition of black money, there is also the moral aspect that we cannot ignore. Sacrificing one’s conscience and morality to meet his selfish ends is a sign of anti-social behavior and should not be encouraged in any given situation.

                            Black money has far-reaching effects on a country’s economy. The large amounts of money that do not get registered in the tax department leads to a loss in the revenue received by the government from the public. This revenue is used to boost policy making, and development plans for the nation and a stalemate in such situations severely affect the country. Moreover, such lump-sum amounts of money rarely enter the entire banking channel. This leads to the stifling of the economy as these amounts remain concealed, and the banks fail to use it to boost small-time business people and entrepreneurs.

                            Black money also leads to many inaccuracies in the country’s economy. No nation can count the exact amount of black money present in the system. Such unreported amounts cannot be calculated as the Gross National Product or Gross Domestic Product, leading to inaccuracies and problems in policymaking. A sound system is needed very soon to sort out such issues.
                            Paragraph On Black Money – 200 Words for Classes 6, 7, 8 Students
                            Black money is a severe problem in almost every nation. In addition to the harsh economic issues caused by the deposition of black money, there is also the moral aspect that we cannot ignore. Sacrificing one’s conscience and morality to meet his selfish ends is a sign of anti-social behavior and should not be encouraged in any given situation.

                            Black money has far-reaching effects on a country’s economy. The large amounts of money that do not get registered in the tax department leads to a loss in the revenue received by the government from the public. This revenue is used to boost policy making, and development plans for the nation and a stalemate in such situations severely affect the country. Moreover, such lump-sum amounts of money rarely enter the entire banking channel. This leads to the stifling of the economy as these amounts remain concealed, and the banks fail to use it to boost small-time business people and entrepreneurs.

                            Black money also leads to many inaccuracies in the country’s economy. No nation can count the exact amount of black money present in the system. Such unreported amounts cannot be calculated as the Gross National Product or Gross Domestic Product, leading to inaccuracies and problems in policymaking. A sound system is needed very soon to sort out such issues.
                        </p>
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
                            <tr>
                                <td>Ronal Richard</td>
                                <td>Ha Noi</td>
                                <td><a href="#">View Profile</a></td>
                                <td>Pending</td>
                            </tr>
                            <tr>
                                <td>Ronal Richard</td>
                                <td>Ha Noi</td>
                                <td><a href="#">View Profile</a></td>
                                <td>Pending</td>
                            </tr>
                        </table>
                    </div>
                </div>           
            </div>          
            <div class="right">
                <div class="skill">
                    <p>Skill</p>
                    <p class="smallDetail">React Java</p>
                </div>
                <div class="location">
                    <p>Location</p>
                    <p class="smallDetail">Hanoi</p>
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
