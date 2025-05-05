<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Order Status</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>Order Status</h1>
    <p>Order ID: ${order.orderId}</p>
    <p>Status: ${order.status}</p>
    <c:if test="${not empty order.prepTime}">
        <p>Expected Preparation Time: ${order.prepTime}</p>
    </c:if>
    <c:if test="${order.status == 'SCHEDULED'}">
        <a href="${pageContext.request.contextPath}/cashier/billing?orderId=${order.orderId}">View Bill</a>
    </c:if>
</body>
</html>