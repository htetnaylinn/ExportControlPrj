let sendImageFlag = false;
let sendBothFlag = false;
const likeReaction = "LIKE";
const dislikeReaction = "DISLIKE";

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
//    $("#greetings").html("");
}

$( document ).ready(function() {

    stompClient.connect({}, function (frame) {
        setConnected(true);
        let cname = $("#companyCode").val();
        let invoiceCode = $("#invoiceCode").val()
        sessionStorage.removeItem(invoiceCode)
        console.log('Connected: ' + frame);

        stompClient.subscribe(`/topic/${cname}/*`,function(greeting){
            if(JSON.parse(greeting.body).invoiceCode !== invoiceCode){
                showNoti(JSON.parse(greeting.body));
            }
        });

        stompClient.subscribe(`/topic/${cname}/${invoiceCode}`, function (greeting) {
            showGreeting(JSON.parse(greeting.body));
            document.getElementById('scroll').scrollTo(0, document.getElementById('scroll').scrollHeight);
        });
    });
    let userLatestMsg = $("#userlatestMessageId").val();
    let currentLatestMsg = $("#currentlatestMessageId").val();
    // alert(userLatestMsg)
    if(userLatestMsg != " "){
        document.getElementById(userLatestMsg).scrollIntoView({block: "end"});
    }
})

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendImage() {
    let cname = $("#companyCode").val()
    let ucode = $("#userCode").val()
    let invoiceCode = $("#invoiceCode").val()
    stompClient.send(`/app/${cname}/${invoiceCode}`, {}, JSON.stringify({'image': binaryFile,'userCode':ucode}));

}

function sendName() {
    let cname = $("#companyCode").val()
    let ucode = $("#userCode").val()
    let invoiceCode = $("#invoiceCode").val()
    stompClient.send(`/app/${cname}/${invoiceCode}`, {}, JSON.stringify({'name': $("#message").val(),'userCode':ucode}));

}

function sendBoth() {
    let cname = $("#companyCode").val()
    let ucode = $("#userCode").val()
    let invoiceCode = $("#invoiceCode").val()
    stompClient.send(`/app/${cname}/${invoiceCode}`, {}, JSON.stringify({'name': $("#message").val(),'image': binaryFile,'userCode':ucode}));
}

function sendReact(messageId){
    let reactionStatus;
    let likedMessage = document.getElementById(messageId)
    if(likedMessage.classList.contains("bi-hand-thumbs-up")){
        reactionStatus = likeReaction;
    }else if(likedMessage.classList.contains("bi-hand-thumbs-up-fill")){
        reactionStatus = dislikeReaction;
    }

    let cname = $("#companyCode").val()
    let ucode = $("#userCode").val()
    let invoiceCode = $("#invoiceCode").val()
    stompClient.send(`/app/${cname}/${invoiceCode}`, {}, JSON.stringify({'reactMessageId': messageId,'userCode':ucode,'reactionStatus':reactionStatus}));
}

function showGreeting(message) {
            let img = document.createElement('img');
            img.src =  message.imageUrl;
            img.style.width = "150px";
            img.style.height = "100px";
            let parentElementId = message.invoiceCode;
    if(message.sendText === false && message.sendImg === false ) {
            let ucode = $("#userCode").val()
            if(ucode === message.userCode){
                let likedMessage = document.getElementById(message.reactMessageId)
                if(likedMessage.classList.contains("bi-hand-thumbs-up")){
                    likedMessage.classList.remove("bi-hand-thumbs-up")
                    likedMessage.classList.add("bi-hand-thumbs-up-fill")
                    likedMessage.style.color = 'blue';
                }else if(likedMessage.classList.contains("bi-hand-thumbs-up-fill")){
                    likedMessage.classList.remove("bi-hand-thumbs-up-fill")
                    likedMessage.classList.add("bi-hand-thumbs-up")
                    likedMessage.style.color = '';
                }
            }
            let showReaction = document.getElementById("show-reaction-"+message.reactMessageId)
            let showCount = document.getElementById("show-count-"+message.reactMessageId)
            if(message.reactCount === 0){
                showReaction.classList.remove("bi-person-fill")
                showReaction.classList.add("bi-person")
                showCount.innerHTML = message.reactCount
            }else if(message.reactCount > 0){
                showReaction.classList.remove("bi-person")
                showReaction.classList.add('bi-person-fill')
                showCount.innerHTML = message.reactCount
            }
        }
    let reactMessageId = message.reactMessageId
    let showReactionId = "show-reaction-"+reactMessageId
    let showCountId = "show-count-"+reactMessageId
        if(message.sendText === true && message.sendImg === false){

             $("#"+parentElementId+"").append("<tr><td class='row w-100 m-0 pt-3 pb-3' ><div class='col-10' >" + message.content + "</div> <div class='col-2 p-0'>\n" +
                 "                                    <i id='"+reactMessageId+"' class='bi bi-hand-thumbs-up cursor-pointer' onclick=sendReact('"+reactMessageId+"')></i>\n" +
                 "                                   <i id='"+showReactionId+"' class='bi bi-person cursor-pointer' onclick=openReactPerson('"+reactMessageId+"')></i>\n" +
                 "                                   <span id='"+showCountId+"'>0</span>\n" +
                 "                                </div> </td></tr>");
        }

        if(message.sendImg === true){
            $("#"+parentElementId+"").append("<tr><td class='row w-100 m-0 pt-3 pb-3' ><div class='col-10' >" + message.content + "</div> <div class='col-2 p-0'>\n" +
                 "                                    <i id='"+reactMessageId+"' class='bi bi-hand-thumbs-up cursor-pointer' onclick=sendReact('"+reactMessageId+"')></i>\n" +
                 "                                   <i id='"+showReactionId+"' class='bi bi-person cursor-pointer' onclick=openReactPerson('"+reactMessageId+"')></i>\n" +
                 "                                   <span id='"+showCountId+"'>0</span>\n" +
                 "                                </div> " +
                "<a onclick=showFullImage('img"+reactMessageId+"')> <img id='img"+reactMessageId+"' src="+message.imageUrl+" style=width:150px;height:100px;margin-left:100px;border-radius: black 10px;></img> </a> </td></tr>");
//           $("#greetings").append("<tr><td><div>" + message.content + "</div><input type=image src="+message.imageUrl+" style=width:150px;height:100px;margin-left:80px;border-radius: black 10px;/></td></tr>");
             $("#imageTest").empty();

        }
}

$(function () {

    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#button-addon2" ).click(function() {
        let input = document.getElementById("message");
        let inputOuter = document.getElementById("input-wrapper")

        input.classList.remove('input-border-change');
        inputOuter.classList.remove('image-border');

    if($("#message").val() && fileSelected == "false"){
        sendName();
        sendImageFlag = false;
        $("#message").val('');
    }

    if(fileSelected == "true" && $("#message").val() == false){
        sendImage();
        sendImageFlag = true;
    }

    if($("#message").val() && fileSelected == "true"){
        sendBoth();
        sendBothFlag = true;
        $("#message").val('');

    }

    fileSelected = "false";
    });
});


let input = document.getElementById("message");

// Execute a function when the user presses a key on the keyboard
input.addEventListener("keypress", function(event) {
  // If the user presses the "Enter" key on the keyboard
  if (event.key === "Enter") {
    // Cancel the default action, if needed
    event.preventDefault();
    // Trigger the button element with a click
    document.getElementById("button-addon2").click();
  }
});


function openReactPerson(messageId){
    let myModal = new bootstrap.Modal(document.getElementById('reacted-person-modal'));
    $('#reactPersonList').empty()
    let showModal = false ;
    $.ajax({
        type: "GET",
        url: "/fetchReactedPerson/messageId",
        data: {messageId: messageId},
        success: function (result) {
            personList = result.reactedPerson;
            for (let i = 0; i < personList.length; i++) {
                $('#reactPersonList').append("<div class='row p-1'> <div class='col-8'>"+ personList[i] +"</div> <div class='col-4 text-end'> <i class='bi bi-hand-thumbs-up-fill' style='color:blue'></i> </div> </div>")
            }
            if(result.reactedPerson != null){
                myModal.show()
            }
        }
    })
}

function takeScreenShot(){

    document.body.style.userSelect = 'none'
    document.body.style.cursor = 'crosshair'
    const screenshotTarget = document.getElementById("capture");
    let tempCanvas = null
    console.log("")
    // alert(window.innerWidth)
    // alert(window.innerHeight)
    // console.log(blob)
    // console.log(screenshotTarget)
    // alert(screenshotTarget.outerHTML)
    // document.body.appendChild(blob);
    // html2canvas(document.body).then((canvas) => {
    //     tempCanvas = canvas
    //     document.body.appendChild(canvas);
    // });
    // html2canvas(screenshotTarget, {scrollY: -window.scrollY}).then(function(canvas) {
    //     // var img = canvas.toDataURL();
    //     // window.open(img);
    //     tempCanvas = canvas
    // });
    html2canvas(screenshotTarget, {
        width: 1280,
        height: 609
    }).then(function(canvas) {
      tempCanvas = canvas
    });
    // html2canvas(document.body, {
    //     onrendered: function(canvas) {
    //         document.body.appendChild(canvas);
    //         tempCanvas = canvas
    //     }
    // });
    let div = document.getElementById('div'), x1 = 0, y1 = 0, x2 = 0, y2 = 0;
    let finalx = 0;
    let finaly = 0;
    let finalh = 0;
    let finalw = 0;
    function reCalc() { //This will restyle the div
        let x3 = Math.min(x1,x2); //Smaller X
        let x4 = Math.max(x1,x2); //Larger X
        let y3 = Math.min(y1,y2); //Smaller Y
        let y4 = Math.max(y1,y2); //Larger Y
        div.style.left = x3 + 'px';
        div.style.top = y3 + 'px';
        div.style.width = x4 - x3 + 'px';
        div.style.height = y4 - y3 + 'px';
        finalw = x4 - x3;
        finalh = y4 - y3;
        finalx = x3;
        finaly = y3;

    }
    onmousedown = function(e) {
        div.style.display = ""
        div.hidden = 0; //Unhide the div
        x1 = e.clientX; //Set the initial X
        y1 = e.clientY; //Set the initial Y
        reCalc();

        onmousemove = function(e) {
            x2 = e.clientX; //Update the current position X
            y2 = e.clientY; //Update the current position Y
            reCalc();
        };

        onmouseup = function(e) {
            document.body.style.cursor = 'context-menu'
            div.hidden = 1; //Hide the div
            // alert(finalw)
            // alert(finalh)
            creatCanvas(finalx,finaly,finalw,finalh,tempCanvas)
        };
    };
}

function creatCanvas(valueX,valueY,valueW,valueH,canvas){
    // console.log(blob)
    //     canvas.setAttribute("width",500)
    //     canvas.setAttribute("height",400)
        let width = valueW;
        let height = valueH;
        // alert("width"+width)
        // alert("height"+height)
        let extra_canvas = document.createElement("canvas");
        extra_canvas.setAttribute("width",valueW*2 + "px");
        extra_canvas.setAttribute('height',valueH*2 + "px");
        let ctx = extra_canvas.getContext('2d');
        ctx.drawImage(canvas, valueX*1.51999999, valueY*1.5,width*1.5,height*1.5,0,0,width*1.62,height*1.5)
        const base64image = extra_canvas.toDataURL("image/png");
        // window.location.href = base64image;
        let img = document.createElement('img');
        img.src =  base64image;
        img.style.width = valueW+"px";
        img.style.height =valueH+"px";
        let image = document.getElementById("imageTest");
        image.appendChild(img);
}
        function urlsToAbsolute(nodeList) {
            if (!nodeList.length) {
                return [];
            }
            let attrName = 'href';
            if (nodeList[0].__proto__ === HTMLImageElement.prototype
                || nodeList[0].__proto__ === HTMLScriptElement.prototype) {
                attrName = 'src';
            }
            nodeList = [].map.call(nodeList, function (el, i) {
                let attr = el.getAttribute(attrName);
                if (!attr) {
                    return;
                }
                let absURL = /^(https?|data):/i.test(attr);
                if (absURL) {
                    return el;
                } else {
                    return el;
                }
            });
            return nodeList;
        }

        function screenshotPage() {
            urlsToAbsolute(document.images);
            urlsToAbsolute(document.querySelectorAll("link[rel='stylesheet']"));
            let screenshot = document.documentElement.cloneNode(true);
            let b = document.createElement('base');
            b.href = document.location.protocol + '//' + location.host;
            let head = screenshot.querySelector('head');
            head.insertBefore(b, head.firstChild);
            screenshot.style.pointerEvents = 'none';
            screenshot.style.overflow = 'hidden';
            screenshot.style.webkitUserSelect = 'none';
            screenshot.style.mozUserSelect = 'none';
            screenshot.style.msUserSelect = 'none';
            screenshot.style.oUserSelect = 'none';
            screenshot.style.userSelect = 'none';
            screenshot.dataset.scrollX = window.scrollX;
            screenshot.dataset.scrollY = window.scrollY;
            const script = document.createElement('script');
            script.textContent = '(' + addOnPageLoad_.toString() + ')();';
            screenshot.querySelector('body').appendChild(script);
            const blob = new Blob([screenshot.outerHTML], {
                type: 'text/html'
            });
            return blob;
}

        function addOnPageLoad_() {
            window.addEventListener('DOMContentLoaded', function (e) {
                const scrollX = document.documentElement.dataset.scrollX || 0;
                const scrollY = document.documentElement.dataset.scrollY || 0;
                window.scrollTo(scrollX, scrollY);
            });
        }

        function generate() {
            window.URL = window.URL || window.webkitURL;
            window.open(window.URL.createObjectURL(screenshotPage()));
            console.log(window.URL.createObjectURL(screenshotPage()))
            // takeScreenShot((screenshotPage()))
        }

function blobToBase64() {
        let blob = screenshotPage()
        const reader = new FileReader();

    reader.addEventListener("load",() => {
    });

        reader.addEventListener("loadend",() => {
        // takeScreenShot(reader.result)
    });

        reader.readAsDataURL(blob);
}


