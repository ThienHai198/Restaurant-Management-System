<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Menu</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>Menu</h1>
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
    <a href="${pageContext.request.contextPath}/customer/order">Proceed to Order</a>
</body>
</html>