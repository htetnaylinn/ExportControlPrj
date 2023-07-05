let socket = new SockJS('/gs-guide-websocket');
let stompClient = Stomp.over(socket);
stompClient.debug = function (str) {
    console.log(str);
};

$( document ).ready(function() {
        // sessionExpired()
    var notificationFlag = sessionStorage.getItem("menuNotification");
    if(notificationFlag == "true"){
     notificationElem = document.getElementById('notificationCircle')
     notificationElem.classList.remove("text-white");
     notificationElem.classList.add("text-danger");
    }

    stompClient.connect({}, function (frame) {
        let invoiceCode = $("#invoiceCode").val()
        var cname = $("#companyCode").val();
        console.log('Connected: ' + frame);
        stompClient.subscribe(`/topic/${cname}/*`, function (greeting) {
            if(JSON.parse(greeting.body).invoiceCode !== invoiceCode) {
                showNoti(JSON.parse(greeting.body));
            }
        });
    });
})

function showNoti(message) {
    var loginUserCode = $("#loginUserCode").val();
    // alert(message.userCode +" - and -" +  loginUserCode)
    var path = window.location.pathname;
    var page = path.split("/").pop();
    if(message.userCode !== loginUserCode && page !== 'scheduleOne'){
        sessionStorage.setItem("menuNotification", "true");
        sessionStorage.setItem(message.invoiceCode,message.invoiceCode);
        let notificationElem = document.getElementById('notificationCircle')
        notificationElem.classList.remove("text-white");
        notificationElem.classList.add("text-danger");
    }

}
function sessionExpired(){
    $.ajax({
        url: "/check-session",
        method: "GET",
        contentType: 'application/json; charset=utf-8',
        success: function(data){
            console.log("OK")
        },
        error: function (data) {
            window.location.replace("/login");
        }
    })
}