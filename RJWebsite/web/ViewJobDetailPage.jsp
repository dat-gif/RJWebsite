<%-- 
    Document   : ViewJobDetaiilPage
    Created on : Jan 20, 2022, 2:35:23 PM
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
        <title>Job Detail</title>
    </head>

    <body style="background-color: #dae0e5">
        <header><jsp:include page="component/Header.jsp"/></header>
        <main >
            <!-- detail head -->
            <div style="background: white; margin-top: 1rem; margin-bottom: 1rem;padding-top: 2rem; padding-bottom: 1rem">
                <div class="container">
                    <div class="d-flex justify-content-between">
                        <div class="d-flex justify-content-center">
                            <image src="https://picsum.photos/200" class="rounded-circle "/>
                            <div class="mt-4 ml-5">   
                                <h2 class="display-4 font-weight-normal" >Java Dev</h2>
                                <h4 class="font-weight-light">Company A's B's d'Arezzo</h4>
                                <h4 class="font-weight-light">End date : 10/10/2021</h4>
                            </div>
                        </div>                   
                        <button class="btn btn-primary align-self-baseline mt-2 btn-lg">Apply</button>
                    </div>
                </div>
            </div>
            <!-- detail body -->
            <div class="container" >
                <div class="row justify-content-between">
                    <!-- right -->
                    <div class="col-9 bg-light" style="padding: 2.5rem 3rem" >
                        <h4 class="mt-4" >Detail Information</h4>
                        <!-- job info table -->
                        <h5 class=""style="text-decoration: underline" >General Information</h5>
                        <div class="row row-cols-2 p-3 mx-1" style="background-color: #dae0e5">
                            <!-- salary -->
                            <div class="col d-flex flex-row align-content-center">
                                <image src="https://picsum.photos/40" class="rounded align-self-baseline mt-1 my-auto "/>
                                <div class="pt-2 ml-3 my-auto"> 
                                    <h6 class="text-nowrap m-0">Salary</h6>
                                    <p class="m-0">Up to 1000$</p>
                                </div>
                            </div>
                            <!-- Number of recruits -->
                            <div class="col d-flex flex-row align-content-center">
                                <image src="https://picsum.photos/40" class="rounded align-self-baseline mt-1 my-auto "/>
                                <div class="pt-2 ml-3 my-auto"> 
                                    <h6 class="text-nowrap m-0">Number of recruits</h6>
                                    <p class="m-0">Up to 1000$</p>
                                </div>
                            </div>
                            <!-- Position -->
                            <div class="col d-flex flex-row align-content-center mt-2">
                                <image src="https://picsum.photos/40" class="rounded align-self-baseline mt-1 my-auto "/>
                                <div class="pt-2 ml-3 my-auto"> 
                                    <h6 class="text-nowrap m-0">Position</h6>
                                    <p class="m-0">Up to 1000$</p>
                                </div>
                            </div>
                            <!-- Experience -->
                            <div class="col d-flex flex-row align-content-center mt-2">
                                <image src="https://picsum.photos/40" class="rounded align-self-baseline mt-1 my-auto "/>
                                <div class="pt-2 ml-3 my-auto"> 
                                    <h6 class="text-nowrap m-0">Position</h6>
                                    <p class="m-0">Up to 1000$</p>
                                </div>
                            </div>
                        </div>
                        <h5 class="mt-3" style="text-decoration: underline">Location</h5>
                        <div class="row row-cols-2 p-3 mx-1" style="background-color: #dae0e5">
                            <p class="text-capitalize ml-3">Ha Noi, Ho chi minh</p>
                        </div>
                        <div class="mt-3">
                            <p class="text-justify">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce ultrices faucibus sapien, eu pretium arcu aliquam id. Duis nisl arcu, egestas sit amet commodo commodo, sodales non neque. Cras sagittis neque est, eu ultricies turpis vestibulum lobortis. Proin accumsan commodo lacus, feugiat malesuada lorem. Morbi vitae vestibulum nisi, quis vulputate lorem. Duis lacinia nisl finibus risus blandit, eget posuere ex commodo. Nullam consequat ligula sapien, sed tincidunt elit euismod vitae. Quisque at tellus eu elit facilisis rutrum sed nec magna. Nam ut finibus lectus, sed tincidunt lectus. Morbi in lorem quis urna posuere semper. In sit amet odio eros. Ut scelerisque ligula sed quam lacinia, quis ullamcorper odio viverra. Ut non faucibus magna.

                                Pellentesque vel nisi molestie, efficitur dui a, congue elit. Etiam augue arcu, tempus et odio vel, ullamcorper porttitor est. Vivamus egestas varius condimentum. Aenean mattis dolor in porta venenatis. Aenean in ornare nisl. In hac habitasse platea dictumst. Etiam quis diam mollis, congue nunc et, faucibus quam. Fusce non tristique augue, et auctor est. Nam justo enim, lacinia pharetra sollicitudin ut, aliquam et arcu. Pellentesque non augue massa. Ut molestie mauris cursus ipsum gravida rhoncus. Vivamus efficitur finibus congue. Quisque at vehicula odio. Donec sodales hendrerit fermentum.

                                Fusce tristique aliquam sodales. Fusce scelerisque nibh a ullamcorper ultricies. Ut a massa nec nibh dictum bibendum eu at massa. In et congue ex. Sed id neque in dolor aliquet viverra eu vitae ante. Nam nulla turpis, scelerisque sed consectetur sed, cursus nec arcu. Proin commodo egestas urna, ac ultrices ex accumsan sed.</p>
                        </div>
                    </div>

                    <div class="col-2 bg-light" style="width: 22.0%;
                         flex: 0 0 22.0%;max-width: 22.0%;" >

                    </div>
                </div>
            </div>
        </div>

    </main>
    <footer> <jsp:include page="component/Footer.jsp"/></footer>
    <script src="bootstrap-4.6.1-dist/js/bootstrap.min.js"></script>
</body>
</body>
</html>
