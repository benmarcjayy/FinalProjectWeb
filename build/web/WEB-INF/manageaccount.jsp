
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    </head>
    <body class="container">
        <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
            <a href="/finalproject/home" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">        
                <img  class="pl-3" src="/portal-content/img/logo.png" width="50" height="50">
                <span class="fs-4">Home nVentory</span>
            </a>     
            <ul class="nav nav-pills">
                <li  class="nav-item px-2">
                    <span class="fs-4 px-3"><c:out value="${user.firstName} ${user.lastName}"/></span>
                </li>

                <li class="nav-item px-3">
                    <a href="/finalproject/home" class="nav-link active" aria-current="page">
                        Home 
                    </a>
                </li>

                <li class="nav-item px-2">
                    <a href="/finalproject/manageinventory" class="nav-link active" aria-current="page">
                        Manage Inventory
                    </a>
                </li>

                <li class="nav-item px-3">
                    <a href="home?action=logout" class="nav-link active" aria-current="page">
                        Logout
                    </a>
                </li>
            </ul> 
        </header>
        <div>
            <div>
                <main>
                    <div>
                        <h1>Manage Account</h1>
                    </div>
                    <form method="POST" action="manageaccount">
                        <div class="input-group mb-3">
                            <span class="input-group-text">First Name</span>
                            <input required name="firstName" type="firstName" class="form-control w-25" id="firstName" value="<c:out value="${user.firstName}"></c:out>">
                            </div>    
                            <div class="input-group mb-3">
                                <span class="input-group-text">last Name</span>
                                <input required name="lastName" type="lastName" class="form-control w-25" id="lastName" value="<c:out value="${user.lastName}"></c:out>">
                            </div>  
                            <div class="input-group mb-3">
                                <span class="input-group-text">Email</span>
                                <input required name="email" type="email" class="form-control w-25"   id="newEmail" value="<c:out value="${user.email}"></c:out>">
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text">Enter Current Password</span>                          
                                <input required name="currentPassword" type="password" class="form-control w-25" id="currentPassword" placeholder="Current Password">
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text">Enter New Password</span>
                                <input required name="newPassword" type="password" class="form-control w-25" id="newPassword" placeholder="New Password">
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text">Confirm New Password</span>
                                <input required name="retypeNewPassword" type="password" class="form-control w-25" id="retypeNewPassword" placeholder="New Password">
                            </div>
                        <c:if test="${message != null}">
                            <div>
                                <p><c:out value ="${message.toString()}"></c:out></p>
                                </div>
                        </c:if>
                        <button type="submit" class="btn btn-dark">Change</button>
                    </form>
                    <div class="py-2">
                        <a href="manageaccount?action=deactivate" class="btn btn-outline-dark">Deactivate Account</a>
                    </div>

                </main>
            </div>
        </div>
    </div>
    <form>
</body>
</html>
