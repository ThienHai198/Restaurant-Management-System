<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Menu Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>Menu Management</h1>
    <h2>Add New Item</h2>
    <form action="${pageContext.request.contextPath}/manager/addMenuItem" method="post">
        <label for="name">Name:</label>
        <input type="text" name="name" id="name" required>
        <label for="price">Price:</label>
        <input type="number" step="0.01" name="price" id="price" required>
        <label for="category">Category:</label>
        <input type="text" name="category" id="category" required>
        <input type="submit" value="Add Item">
    </form>
    <h2>Current Menu</h2>
    <table>
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Category</th>
        </tr>
        <c:forEach var="item" items="${menuItems}">
            <tr>
                <td>${item.name}</td>
                <td>$${item.price}</td>
                <td>${item.category}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>