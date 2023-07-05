    $( document ).ready(function() {
    sessionStorage.setItem("menuNotification", "false");
    notificationElem = document.getElementById('notificationCircle')
    notificationElem.classList.remove("text-danger");
    notificationElem.classList.add("text-white");

        Object.keys(sessionStorage).forEach((key) => {
            let sessionValue = sessionStorage.getItem(key);
            if(sessionValue != 'true' && sessionValue != 'false'){
                document.getElementById('invoice-'+sessionValue).innerHTML = "未読";
                document.getElementById('invoice-'+sessionValue).style.backgroundColor = "red";
                document.getElementById('invoice-'+sessionValue).style.color = "white";
                document.getElementById('readStatus-'+sessionValue).style.backgroundColor = "red";
            }

        });

    stompClient.connect({}, function (frame) {

        var cname = $("#companyCode").val();

        console.log('Connected: ' + frame);
        stompClient.subscribe(`/topic/${cname}/*`, function (greeting) {
            showGreeting(JSON.parse(greeting.body));
        });
    });
})

function showGreeting(message) {
    let invoiceCode = message.invoiceCode;

    sessionStorage.setItem(invoiceCode, invoiceCode);
    document.getElementById('invoice-'+invoiceCode).innerHTML = "未読";
    document.getElementById('invoice-'+invoiceCode).style.backgroundColor = "red";
    document.getElementById('invoice-'+invoiceCode).style.color = "white";
    document.getElementById('readStatus-'+invoiceCode).style.backgroundColor = "red";

}



