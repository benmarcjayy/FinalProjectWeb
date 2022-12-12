
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    </head>
    <body class="container">
        <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
                <img class="pl-3" src="/portal-content/img/logo.png" width="50" height="50">
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
        <main>
            <div>
                <h2>Create an account</h2>
                <form action="register" method="POST">
                    <div class="input-group mb-3">
                        <span class="input-group-text">Item</span>
                        <input class="form-control" id="newItemPrice" required name="updatedItemName" type="text" class="form-control" id="updatedItemName"  value="<c:out value="${selectedItem.itemName}"/>">
                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text">First Name</span>
                        <input required type="text" name="inputFirstName" class="form-control" id="inputFirstName"/>
                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text">Last Name</span>
                        <input required type="text" name="inputLastName" class="form-control" id="inputLastName"/>
                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text">Email</span>
                        <input required type="email" name="inputEmail" class="form-control" id="inputEmail"/>
                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text">Password</span>
                        <input required type="password" class="form-control"  minlength="8" maxlength="20" name="inputPasswordFirst" id="inputPasswordFirst"/>
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text">Confirm Password</span>
                        <input required type="password" class="form-control" minlength="8" maxlength="20" name="inputPasswordSecond" id="inputPasswordSecond"/>     
                    </div>
                    <div>
                        <button class="btn btn-lg btn-primary">Register</button>
                    </div>

                    <p>Have an account? <a href="/finalproject/login">Login here</a></p>
                    <c:if test="${message != null}">
                        <div>
                            <p><c:out value ="${message.toString()}"></c:out></p>
                            </div>
                    </c:if>
                </form>
            </div>
        </main>
    </body>
</html>
