<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Billing</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>Billing</h1>
    <p>Order ID: ${order.orderId}</p>
    <p>Items: ${order.items}</p>
    <c:set var="total" value="0"/>
    <c:forEach var="itemId" items="${order.items.split(',')}">
        <c:forEach var="item" items="${menuItems}">
            <c:if test="${item.id == itemId}">
                <p>${item.name}: $${item.price}</p>
                <c:set var="total" value="${total + item.price}"/>
            </c:if>
        </c:forEach>
    </c:forEach>
    <p>Total: $${total}</p>
    <a href="${pageContext.request.contextPath}/customer/payment?orderId=${order.orderId}">Proceed to Payment</a>
</body>
</html>