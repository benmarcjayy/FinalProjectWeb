
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
                <img href="/finalproject/login" class="pl-3" src="/portal-content/img/logo.png" width="50" height="50">
                <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
                    <svg class="bi me-2" width="40" height="32"><use xlink:href="#"></use></svg>
                    <span class="fs-4">Home nVentory</span>
                </a>     
                <ul class="nav nav-pills">
                    <li class="nav-item">
                        <a href="#" class="nav-link active" aria-current="page">Contact Admin</a>
                    </li>
                </ul> 
            </header>
        </div>
        
        <div style="width:50%; margin: auto; ">
            <main class="form-signin" >    
                <form action="login" method="post">
                    <h2 class="h3 mb-3 fw-normal">Login</h2>
                    <!-- Email input -->
                    <div class="form-floating">
                        <input required type="email" class="form-control" id="userEmail" name="userEmail" class="form-control" />
                        <label class="form-label" for="userEmail">Email address</label>
                    </div>

                    <!-- Password input -->
                    <div class="form-floating">
                        <input required type="password" class="form-control" id="userPassword" name="userPassword" class="form-control" />
                        <label class="form-label" for="userPassword">Password</label>
                    </div>


                    <div class="checkbox mb-3">

                        <!-- Checkbox -->
                        <div>
                            <input type="checkbox" value="" id="form2Example31" checked />
                            <label for="form2Example31"> Remember me </label>
                        </div>

                    </div>

                    <!-- Submit-->
                    <button type="submit" class="w-100 btn btn-lg btn-primary">Sign in</button>

                    <!-- Register-->
                    <div class="py-2">
                        <p><a href="login?action=register" class="btn btn-outline-primary btn-sm">Register</a></p>
                    </div>

                    <c:if test="${message != null}">
                        <div>
                            <p><c:out value ="${message.toString()}"></c:out></p>
                            </div>
                    </c:if>
                </form>              
            </main>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>
