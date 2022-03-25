<%-- 
    Document   : HiepHeader
    Created on : Feb 7, 2022, 11:57:48 AM
    Author     : USE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/Header.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="header">
            <h2 class="mt-3">R & IT J</h2>
            <ul>
                <li class="nav-item ml-3">
                    <a href="RecruitmentPostedController">Recruitment Posted</a>
                </li>
                <li class="nav-item ml-3">
                    <a href="SearchCandidateController">Searching Candidate</a>
                </li>
                <li class="nav-item ml-3">
                    <a href="ManageRecruitmentPostedController">Manage Recruitment</a>
                </li>    
                <li class="nav-item ml-3">
                    <a href="#">Company Profile</a>
                </li>   
                <li class="nav-item ml-3">
                    <a type="button" class="btn btn-primary" href="login" >Login</a>
                </li>
                <li class="nav-item ml-3">
                    <a type="button" class="btn btn-primary" href="logout" >Logout</a>
                </li>
            </ul>            
        </div>
        <hr/>
    </body> 
</html>
