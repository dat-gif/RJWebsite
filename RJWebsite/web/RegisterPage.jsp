<%-- 
    Document   : RegisterPage
    Created on : Jan 18, 2022, 3:31:21 PM
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
        <title>Register</title>
    </head>
    <body>
        <header><jsp:include page="component/Header.jsp"/></header>
        <main class="container-fluid mt-3 mb-3">
            <div class="row row-cols-2 justify-content-center align-items-center">
                <div class="col no-gutters">
                    <img src="https://picsum.photos/1000/1000" class="img-fluid w-100" alt="alt"/>
                </div>
                <div class="col-5 p-5 " >
                    <h2 class="mb-4">Register</h2>
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Register as Candidate</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Register as Recruiter</a>
                        </li>

                    </ul>

                    <div class="tab-content mt-2 p-3" id="myTabContent">
                        <!-- Candidate register -->
                        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">

                            <form class="w-75" action="register" method="post">
                                <!--User Name -->
                                <div class="form-outline mb-4">
                                    <label class="form-label" for="0">Email</label>
                                    <input type="text" id="0" class="form-control"  name="email"/>
                                </div>
                                <div class="col form-outline mb-3">
                                    <label class="form-label" for="0">Phone Number</label>
                                    <input type="tel" pattern="(84|0[3|5|7|8|9])+([0-9]{8})\b" id="0" class="form-control" name="phoneNumber" />
                                </div>

                                <!-- Password input -->
                                <div class="form-outline mb-4">
                                    <label class="form-label" for="1">Password</label>
                                    <input type="password" id="1" class="form-control" name="password" />
                                </div>
                                <!-- Password input -->
                                <div class="form-outline mb-4">
                                    <label class="form-label" for="2">Confirm Password</label>
                                    <input type="password" id="2" class="form-control" name="confirmPassword" />
                                </div>
                                <input name="role" value="candidate" class="invisible"/>
                                <!-- Submit button -->
                                <button type="submit" class="btn btn-primary btn-block">Register Account</button>
                            </form>
                        </div>
                        <!-- Recruiter register -->
                        <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                            <form class="w-75" action="register" method="post">
                                <!--User Name -->
                                <div class="row row-cols-2">
                                    <div class="col form-outline mb-3">
                                        <label class="form-label" for="0">Company Name</label>
                                        <input type="text" id="0" class="form-control"  name="companyName"/>
                                    </div>
                                    <div class="col form-outline mb-3">
                                        <label class="form-label" for="0">Email</label>
                                        <input type="email" id="0" class="form-control" name="email"/>
                                    </div>
                                </div>


                                <div class="form-outline mb-3">
                                    <label class="form-label" for="1">Address</label>
                                    <input type="text" id="1" class="form-control" name="address"/>
                                </div>

                                <div class="row row-cols-2">
                                    <div class="col form-outline mb-3">
                                        <label class="form-label" for="0">Recruiter Name</label>
                                        <input type="text" id="0" class="form-control" name="recruiterName"/>
                                    </div>
                                    <div class="col form-outline mb-3">
                                        <label class="form-label" for="0">Phone Number</label>
                                        <input type="tel" pattern="(84|0[3|5|7|8|9])+([0-9]{8})\b" id="0" class="form-control" name="phoneNumber" />
                                    </div>
                                    <div class="col form-outline mb-3">
                                        <label class="form-label" for="0">Password</label>
                                        <input type="password" id="0" class="form-control" name="password" />
                                    </div>
                                    <div class="col form-outline mb-3">
                                        <label class="form-label" for="0">Confirm password</label>
                                        <input type="password" id="0" class="form-control" name="confirmPassword"  />
                                    </div>
                                </div>
                                <input name="role" value="recruiter" class="invisible"/>

                                <!-- Submit button -->
                                <button type="submit" class="btn btn-primary btn-block">Register Account</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <footer> <jsp:include page="component/Footer.jsp"/></footer>
        <script src="bootstrap-4.6.1-dist/js/bootstrap.min.js"></script>
        <script></script>
    </body>
</html>
