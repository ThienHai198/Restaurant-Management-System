<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Place Order</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>Place Your Order</h1>
    <form action="${pageContext.request.contextPath}/customer/placeOrder" method="post">
        <label for="tableId">Select Table:</label>
        <select name="tableId" id="tableId">
            <c:forEach var="table" items="${tables}">
                <option value="${table.tableId}">Table ${table.tableNumber}</option>
            </c:forEach>
        </select>
        <h2>Select Items</h2>
        <table>
            <tr>
                <th>Select</th>
                <th>Name</th>
                <th>Price</th>
                <th>Category</th>
            </tr>
            <c:forEach var="item" items="${menuItems}">
                <tr>
                    <td><input type="checkbox" name="items" value="${item.id}"></td>
                    <td>${item.name}</td>
                    <td>$${item.price}</td>
                    <td>${item.category}</td>
                </tr>
            </c:forEach>
        </table>
        <input type="submit" value="Place Order">
    </form>
</body>
</html>