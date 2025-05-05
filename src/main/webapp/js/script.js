function validatePaymentForm() {
    var cardDetails = document.getElementById("cardDetails").value;
    if (cardDetails.length < 4) {
        alert("Please enter valid card details.");
        return false;
    }
    return true;
}