
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

    </head>
</head>
<body >
    <div class="container">
        <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
            <a href="/finalproject/user" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">        
                <img  class="pl-3" src="/portal-content/img/logo.png" width="50" height="50">
                <span class="fs-4">Home nVentory</span>
            </a>    
            <ul class="nav nav-pills">
                <li  class="nav-item px-2">
                    <span class="fs-4 px-3"><c:out value="${user.lastName}"/></span>
                </li>
                <li class="nav-item px-2">
                    <a href="/finalproject/category" class="nav-link active" aria-current="page">
                        Manage Categories                       
                    </a>
                </li>

                <li class="nav-item px-2">
                    <a href="home?action=logout" class="nav-link active" aria-current="page">
                        Logout
                    </a>
                </li>
            </ul> 
        </header>
    </div>
    <div>
        <div>
            <main>
                <div class="container p-3"> 
                    <form action="user" method="POST">
                        <input type="hidden" name="action" value="addUser">
                        <h2>Add User</h2>
                        <div>
                            <div class="input-group mb-3">
                                <span class="input-group-text" id="basic-addon1">Email</span>
                                <input required="email"  type="email" name="inputEmail" id="inputEmail" placeholder="Email" class="form-control" aria-label="Email" aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div>
                            <div class="input-group mb-3">
                                <span class="input-group-text" id="basic-addon2">Password</span>
                                <input required min=6 max=24 type="password" name="inputPassword" id="inputPassword" placeholder="Password" class="form-control" aria-label="Email" aria-describedby="basic-addon2">
                            </div>
                        </div>
                        <div>
                            <div class="input-group mb-3">
                                <span class="input-group-text" id="basic-addon3">First Name</span>
                                <input required min=2 max=24 type="text" name="inputFirstName" id="inputFirstName" placeholder="First Name" class="form-control" aria-label="firstName" aria-describedby="basic-addon3">
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text" id="basic-addon4">Last Name</span>
                                <input required min=2 max=24 type="text" name="inputLastName" id="inputLastName" placeholder="Last Name" class="form-control" aria-label="lastName" aria-describedby="basic-addon4">
                            </div>
                        </div>
                        <div>
                            <div class="input-group mb-3">
                                <span class="input-group-text">Role</span>
                                <select id="inputRole" name="inputRole" class="form-select" aria-label="Default select example">
                                    <option selected>Select a Role</option>
                                    <c:forEach var="role" items="${roles}">
                                        <option>${role.roleName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text">Active</span>
                                <select id="inputActive" name="inputActive" class="form-select" aria-label="Default select example">
                                    <option selected>Yes or No</option>
                                    <option>Yes</option>
                                    <option>No</option>
                                </select>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-dark">Save</button>

                        <p> <c:out value="${message.toString()}"/></p>

                    </form>
                    <div>
                        <h2>Manage Users</h2>
                        <table  class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Email</th>
                                    <th scope="col">First Name</th>
                                    <th scope="col">Last Name</th>
                                    <th scope="col">Role</th>
                                    <th scope="col">Active</th>
                                    <th scope="col">Edit</th>
                                    <th scope="col">Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="applicationUser" items="${users}">
                                    <tr>
                                        <td scope="row">${applicationUser.email}</td>
                                        <td>${applicationUser.firstName}</td>
                                        <td>${applicationUser.lastName}</td>
                                        <td>${applicationUser.role.roleName}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${applicationUser.active==true}">
                                                    Yes 
                                                </c:when>    
                                                <c:otherwise>
                                                    No
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td><a href="user?action=edit&user=${applicationUser.email}">Edit</a></td>
                                        <td><a href="user?action=delete&user=${applicationUser.email}">Delete</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>     
                    <c:if test="${selectedUser != null}">
                        <!--                      <!  --------------Edit User Form----------------------------->
                        <form action="user" method="POST">
                            <input type="hidden" name="action" value="update">
                            <h2>Edit User</h2>
                            <div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text" id="basic-addon5">Email</span>
                                    <input required readonly="readonly" name="selectedEmail" type="email" id="selectedEmail" value="${selectedUser.email}" class="form-control" aria-label="Email" aria-describedby="basic-addon5">
                                </div>
                            </div>
                            <div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text" id="basic-addon6">First Name</span>
                                    <input required name="selectedFirstName" type="text" id="selectedFirstName" value="${selectedUser.firstName}" class="form-control" aria-label="FirstName" aria-describedby="basic-addon6">
                                </div>
                            </div>
                            <div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text" id="basic-addon7">Last Name</span>
                                    <input required name="selectedLastName" type="text" id="selectedLastName" value="${selectedUser.lastName}" class="form-control" aria-label="lastName" aria-describedby="basic-addon7">
                                </div>
                            </div>
                            <div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text">Role</span>
                                    <select name="selectedRole" id="selectedRole" class="form-select" aria-label="Default select example">
                                        <option selected>Current: ${selectedUser.role.roleName}</option>
                                        <c:forEach var="role" items="${roles}">
                                            <option>${role.roleName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text">Active</span>
                                    <select name="selectedActive" id="selectedActive" class="form-select" aria-label="Default select example">
                                        <option selected>
                                            <c:choose>
                                                <c:when test="${selectedUser.active==true}">
                                                    Current: Yes
                                                </c:when>    
                                                <c:otherwise>
                                                    Current: No
                                                </c:otherwise>
                                            </c:choose></option>
                                        <option>Yes</option>
                                        <option>No</option>
                                    </select>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-dark">Save</button>
                            <a href="user?action=cancel" class="btn btn-light">Cancel</a>
                        </form>
                    </c:if>
                </div>
            </main>
            </body>
            </html>
