<form action="${pageContext.request.contextPath}/customer/placeOrder" method="post">
    <input type="hidden" name="customerId" value="1" /> <%-- Hoặc lấy từ session --%>

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
                <td><input type="checkbox" name="items[]" value="${item.id}"></td>
                <td>${item.name}</td>
                <td>$${item.price}</td>
                <td>${item.category}</td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Place Order">
</form>
