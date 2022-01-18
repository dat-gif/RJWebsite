<%-- 
    Document   : Login
    Created on : Jan 18, 2022, 2:28:04 PM
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
        <title>Login</title>
    </head>
    <body>
        <header><jsp:include page="component/Header.jsp"/></header>
        <main class="container-fluid mt-3 mb-3">
            <div class="row row-cols-2 justify-content-center align-items-center">
                <div class="col no-gutters">
                    <img src="https://picsum.photos/1000/1000" class="img-fluid" alt="alt"/>
                </div>
                <div class="col-5 p-5 " >
                    <form class="w-75">
                        <h2 class="mb-4">Login</h2>
                        <!-- Email input -->
                        <div class="form-outline mb-4">
                            <label class="form-label" for="form1Example1">User Name</label>
                            <input type="email" id="form1Example1" class="form-control" />
                        </div>

                        <!-- Password input -->
                        <div class="form-outline mb-4">
                            <label class="form-label" for="form1Example2">Password</label>
                            <input type="password" id="form1Example2" class="form-control" />
                        </div>
                        <div class="row mb-4">
                            <div class="col d-flex justify-content-center">

                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" value="" id="form1Example3" checked />
                                    <label class="form-check-label" for="form1Example3"> Remember me </label>
                                </div>
                            </div>
                            <div class="col">
                                <!--<a href="#!">Forgot password?</a>-->
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
