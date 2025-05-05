<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Cook Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>Cook Dashboard</h1>
    <table>
        <tr>
            <th>Order ID</th>
            <th>Table ID</th>
            <th>Items</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.orderId}</td>
                <td>${order.tableId}</td>
                <td>${order.items}</td>
                <td>${order.status}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/cook/schedule?orderId=${order.orderId}">Set Schedule</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>