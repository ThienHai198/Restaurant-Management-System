<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Payment</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</head>
<body>
    <h1>Payment</h1>
    <c:if test="${not empty paymentSuccess}">
        <h2>Thank You!</h2>
        <p>Your payment was successful. We hope you enjoyed your meal!</p>
        <a href="${pageContext.request.contextPath}/">Return to Home</a>
    </c:if>
    <c:if test="${empty paymentSuccess}">
        <p>Order ID: ${order.orderId}</p>
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
        <form action="${pageContext.request.contextPath}/customer/makePayment" method="post" onsubmit="return validatePaymentForm()">
            <input type="hidden" name="orderId" value="${order.orderId}">
            <input type="hidden" name="amount" value="${total}">
            <label for="cardDetails">Card Details:</label>
            <input type="text" name="cardDetails" id="cardDetails" required>
            <input type="submit" value="Pay Now">
        </form>
    </c:if>
</body>
</html>