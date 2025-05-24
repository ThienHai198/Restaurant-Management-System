<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Set Cooking Schedule</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>Set Cooking Schedule</h1>
    <p>Order ID: ${order.orderId}</p>
    <p>Items: ${order.items}</p>
    <form action="${pageContext.request.contextPath}/cook/setPrepTime" method="post">
        <input type="hidden" name="orderId" value="${order.orderId}">
        <label for="prepTime">Preparation Time:</label>
        <input type="datetime-local" name="prepTime" id="prepTime" required>
        <input type="submit" value="Set Time">
    </form>
</body>
</html>