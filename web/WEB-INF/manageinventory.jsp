
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
        <div>
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
                        <a href="/finalproject/home" class="nav-link active" aria-current="page">
                            Home 
                        </a>
                    </li>

                    <li class="nav-item px-2">
                        <a href="/finalproject/manageaccount" class="nav-link active" aria-current="page">
                            Manage Account                       
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
                    <div>
                        <h2>Manage Items</h2>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Category</th>
                                    <th scope="col">Item</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Edit</th>
                                    <th scope="col">Delete</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="item" items="${usersItems}">
                                    <tr>
                                        <td>${item.category.categoryName}</td>
                                        <td>${item.itemName}</td>
                                        <td>\$${item.price}</td>
                                        <td><a href="manageinventory?action=edit&itemId=${item.itemId}">Edit</a></td>
                                        <td><a href="manageinventory?action=delete&itemId=${item.itemId}">Delete</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>   
                    <div>
                        <c:if test="${selectedItem == null}">
                            <form action="manageinventory" method="POST">
                                <input type="hidden" name="action" value="create">
                                <h2>Add Item</h2>
                                <div>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text">Item</span>
                                        <input placeholder="Item Name" required name="newItemName" type="text" id="newItemName" class="form-control" aria-label="item-name" aria-describedby="basic-addon2">
                                    </div>
                                </div>
                                <div>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text">Price</span>
                                        <input placeholder="Item Price" required name="newItemPrice" step="any" type="number" class="form-control" id="newItemPrice" class="form-control" aria-label="item-price" aria-describedby="basic-addon">
                                    </div>
                                </div>
                                <div>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text">Category</span>
                                        <select name="newItemCategory" id="newItemCategory" class="form-control">
                                            <option selected>Select Category</option>
                                            <c:forEach var="category" items="${categoryList}">
                                                <option>${category.categoryName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <c:if test="${message != null}">
                                    <div>
                                        <p><c:out value ="${message.toString()}"></c:out></p>
                                        </div>
                                </c:if>
                                <button type="submit" class="btn btn-dark">Save</button>
                            </form>
                        </c:if>
                        <c:if test="${selectedItem != null}">
                            <form action="manageinventory" method="POST">
                                <input type="hidden" name="action" value="update">
                                <h2>Edit Item</h2>
                                <div>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text">Item</span>
                                        <input class="form-control" id="newItemPrice" class="form-control" required name="updatedItemName" type="text" class="form-control" id="updatedItemName"  value="<c:out value="${selectedItem.itemName}"/>">
                                    </div>
                                </div>
                                <div>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text">Price</span>
                                        <input class="form-control" id="newItemPrice" class="form-control" required name="updatedItemPrice" type="number" id="updatedItemPrice" value="<c:out value="${selectedItem.price}"/>">
                                    </div>
                                </div>
                                <div>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text">Category</span>
                                        <select name="updatedItemCategory" id="updatedItemCategory" class="form-control" id="newItemPrice" class="form-control">
                                            <option selected><c:out value="${selectedItem.category.categoryName}"/></option>
                                            <c:forEach var="category" items="${categoryList}">
                                                <option>${category.categoryName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <c:if test="${message != null}">
                                    <div>
                                        <p><c:out value ="${message.toString()}"></c:out></p>
                                        </div>
                                </c:if>
                                <button type="submit" class="btn btn-dark">Save</button>
                            </form>
                        </c:if>
                    </div>
                </main>
            </div>
        </div>
    </body>
</html>
