
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <title>Home Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
                <a href="/finalproject/home" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">        
                    <img  class="pl-3" src="/portal-content/img/logo.png" width="50" height="50">
                    <span class="fs-4">Home nVentory</span>
                </a>      
                <ul class="nav nav-pills">
                    <li  class="nav-item px-2">
                        <span class="fs-4 px-3"><c:out value="${user.firstName} ${user.lastName}"/></span>
                    </li>
                    <li class="nav-item px-2">
                        <a href="/finalproject/manageaccount" class="nav-link active" aria-current="page">
                            Manage Account                       
                        </a>
                    </li>

                    <li class="nav-item px-2">
                        <a href="/finalproject/manageinventory" class="nav-link active" aria-current="page">
                            Manage Inventory
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
            <main class="container">

                <div>
                    <h1>Inventory</h1>
                </div>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Category</th>
                            <th scope="col">Item</th>
                            <th scope="col">Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var = "i" value = "${1}"/>
                        <c:forEach var="item" items="${usersItems}">
                            <tr>
                                <td>${i}</td>
                                <td>${item.category.categoryName}</td>
                                <td>${item.itemName}</td>
                                <td>\$${item.price}</td>
                            </tr>
                            <c:set var = "i" value = "${i + 1}"/>
                        </c:forEach>
                    </tbody>
                </table>

            </main>          
        </div>
    </body>
</html>
