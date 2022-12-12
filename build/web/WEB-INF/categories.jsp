
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
<body>
    <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom container">

        <a href="/finalproject/user" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">        
            <img  class="pl-3" src="/portal-content/img/logo.png" width="50" height="50">
            <span class="fs-4">Home nVentory</span>
        </a>     
        <ul class="nav nav-pills">
            <li  class="nav-item px-2">
                <span class="fs-4 px-3"><c:out value="${user.lastName}"/></span>
            </li><!-- comment -->
            <li class="nav-item px-2">
                <a href="/finalproject/user" class="nav-link active" aria-current="page">
                    Manage Users                       
                </a>
            </li>

            <li class="nav-item px-2">
                <a href="home?action=logout" class="nav-link active" aria-current="page">
                    Logout
                </a>
            </li>
        </ul> 
    </header>

    <div>
        <div>
            <main>
                <div class="container"> 
                    <form>
                        <input type="hidden" name="action" value="addCategory">
                        <h2>Add Category</h2>
                        <div>
                            <div class="input-group mb-3">
                                <span class="input-group-text">Category</span>
                                <input required= type="text" name="newCategoryName" class="form-control" id="newCategoryName" placeholder="Category Name">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-dark">Save</button>
                        <p> <c:out value="${message.toString()}"/></p>
                    </form>
                    <div>
                        <h2>Manage Category</h2>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Category</th>
                                    <th scope="col">Edit</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="category" items="${categoryList}">
                                    <tr>
                                        <td>${category.categoryName}</td>
                                        <td><a href="category?action=editCategory&categoryID=${category.categoryId}">Edit</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>     
                    <c:if test="${selectedCategory != null}">
                        <form action="category" method="POST">
                            <input type="hidden" name="action" value="updateCategory">
                            <h2>Update Category</h2>
                            <div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text">Category</span>
                                    <input required name="selectedCategoryName" type="text" class="form-control" id="selectselectedCategoryNameedEmail" value="${selectedCategory.categoryName}">
                                </div>
                            </div>
                            <button type="submit" class="btn btn-dark">Save</button>
                            <a href="category?action=cancel" class="btn btn-light">Cancel</a>
                        </form>
                    </c:if>
                </div>
            </main>
            </body>
            </html>
