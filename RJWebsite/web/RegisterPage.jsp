<%-- 
    Document   : RegisterPage
    Created on : Jan 18, 2022, 3:31:21 PM
    Author     : Admin
--%>

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
                    <img src="https://picsum.photos/1000/1000" class="img-fluid" alt="alt"/>
                </div>
                <div class="col-5 p-5 " >
                    <!-- Candidate register -->
                    <h2 class="mb-4">Register</h2>
                    <form class="w-75">
                        <!--User Name -->
                        <div class="form-outline mb-4">
                            <label class="form-label" for="0">User Name</label>
                            <input type="text" id="0" class="form-control" />
                        </div>

                        <!-- Password input -->
                        <div class="form-outline mb-4">
                            <label class="form-label" for="1">Password</label>
                            <input type="password" id="1" class="form-control" />
                        </div>
                        <!-- Password input -->
                        <div class="form-outline mb-4">
                            <label class="form-label" for="1">Confirm Password</label>
                            <input type="password" id="1" class="form-control" />
                        </div>

                        <!-- Submit button -->
                        <button type="submit" class="btn btn-primary btn-block">Sign in</button>
                    </form>
                   
                    <!-- Recruiter register -->
                    <form class="w-75">
                        <!--User Name -->
                        <div class="row row-cols-2">
                            <div class="col form-outline mb-3">
                                <label class="form-label" for="0">Company Name</label>
                                <input type="text" id="0" class="form-control" />
                            </div>
                            <div class="col form-outline mb-3">
                                <label class="form-label" for="0">Email</label>
                                <input type="email" id="0" class="form-control" />
                            </div>
                        </div>


                        <div class="form-outline mb-3">
                            <label class="form-label" for="1">Address</label>
                            <input type="text" id="1" class="form-control" />
                        </div>

                        <div class="row row-cols-2">
                            <div class="col form-outline mb-3">
                                <label class="form-label" for="0">Recruiter Name</label>
                                <input type="text" id="0" class="form-control" />
                            </div>
                            <div class="col form-outline mb-3">
                                <label class="form-label" for="0">Phone Number</label>
                                <input type="number" id="0" class="form-control" />
                            </div>
                            <div class="col form-outline mb-3">
                                <label class="form-label" for="0">Password</label>
                                <input type="password" id="0" class="form-control" />
                            </div>
                            <div class="col form-outline mb-3">
                                <label class="form-label" for="0">Confirm password</label>
                                <input type="password" id="0" class="form-control" />
                            </div>
                        </div>


                        <!-- Submit button -->
                        <button type="submit" class="btn btn-primary btn-block">Sign in</button>
                    </form>
                </div>
            </div>
        </main>
        <footer> <jsp:include page="component/Footer.jsp"/></footer>
        <script src="bootstrap-4.6.1-dist/js/bootstrap.min.js"></script>
    </body>
</html>
