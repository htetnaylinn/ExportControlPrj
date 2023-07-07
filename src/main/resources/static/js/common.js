

//language toggle
function toggleClick(){
  var elm = document.getElementById('language');
  var form =  document.getElementById('changeLanguageForm');

    var location = window.location
//    if(elm.innerHTML == "Jpn"){
//      elm.innerHTML="Eng";
//      elm.style.backgroundColor = "#3eacd8";
//
//  }else{
//      elm.innerHTML = "Jpn";
//      elm.style.backgroundColor = "#ff5252";
//  }
 var x =  document.getElementById('toggle');

 var offset = location.toString().indexOf('/');

 var temp1 = location.toString().indexOf('/', offset+1);

 var result = location.toString().indexOf('/', temp1+1);

 var url = location.toString().substring(result+1);

 x.value = url;

setTimeout(change, 0001);

}

function change(){
    var change = document.getElementById('change');
    change.click();
}


function resetListForm($form) {
    $form.find('input:text, input:password, input:file, textarea').val('');
    // $form.find("select option:selected").text('');
    // $form.find("select option:selected").val('');
    // $form.find("select option:nth-child(1)").text('');
    // $form.find("select option:nth-child(1)").val('');
    $form.find("option:selected").prop("selected", false)
    // $form.find('input:checkbox').prop( 'checked', false );
    $form.find('input:checkbox').prop( 'checked', false );

    const errorElements = document.querySelectorAll('.errorMsg');

    for (let i = 0; i < errorElements.length; i++) {
        const errorElement = errorElements[i];
        errorElement.style.visibility = 'hidden';
    }
}

function showErrorMsg(elementId,errMsgJp,errMsgEn,errMsgId,languageFlag){
      if(languageFlag == 0){
            document.getElementById(errMsgId).textContent = errMsgJp;
      }else{
            document.getElementById(errMsgId).textContent = errMsgEn;
      }

      document.getElementById(elementId).style.visibility="visible";
    // document.getElementById(elementId).style.display = "block";

}

function inputChange(elementId){
      document.getElementById(elementId).style.visibility="hidden";
    // document.getElementById(elementId).style.display = "none";

}

function check(selectedId,comboList,no,final){
            var oDDL = document.getElementById(final);
            var siteSelect = oDDL.options[oDDL.selectedIndex].innerHTML.trim()  ;
            var siteNameT=comboList.trim();
            var siteListString=siteNameT.substring(1,siteNameT.length-1);
            var siteList=siteListString.split(",");
            for(var i=0;i<siteList.length;i++){
                var cond=(siteList[i].trim()==siteSelect);
                if(cond==true){
                  no=i;
                }
                console.log(siteList[i]+"is all");
                console.log(selectedId+"is Selected");

            }

            return no+1;
    }

function test(a,b,c,d,final){
  $(a).focus(function() {
    var chk=check(a,b,c,final);
      $(d).html("");
      $(d).val("");
    document.getElementById(final).selectedIndex=chk;
});
}

function showSystemError(systemErrMsg){
        iziToast.show({
        titleColor: 'red',
        overlay: true,
        overlayColor:'rgba(0, 0, 0, 0.12)',
        title: systemErrMsg,
        timeout: 20000,
        animateInside:'false',
        zindex: 1999,
        backgroundColor: 'white',
        transitionIn: 'bounceInDown',
        position: 'topCenter', // bottomRight, bottomLeft, topRight, topLeft, topCenter, bottomCenter
        progressBarColor: 'darkblue',
      });
        var elem = document.getElementById('select-element');
        if(elem != null ){
            elem.selectedIndex = 0;
        }
    }
function showLoadingProgress(loadingMsg){
    iziToast.show({
        titleColor: 'blue',
        overlay: true,
        overlayColor:'rgba(0, 0, 0, 0.12)',
        title: loadingMsg,
        timeout: 90000000,
        animateInside:'false',
        zindex: 1999,
        backgroundColor: 'white',
        transitionIn: 'bounceInDown',
        close:'false',
        closeOnEscape:'false',
        closeOnClick:'false',
        position: 'center', // bottomRight, bottomLeft, topRight, topLeft, topCenter, bottomCenter
        progressBarColor: 'darkblue',
    });
    }
function selectSearchType(searchType){
        switch(searchType) {
          case "CODE":
            $("#icon0").css("color", "green");
            break;
          case "PCODE":
            $("#icon1").css("color", "green");
            break;
          case "LOADING":
            $("#icon2").css("color", "green");

            break;
          case "UNLOADING":
            $("#icon3").css("color", "green");

            break;
          case "TRANSPORT":
            $("#icon4").css("color", "green");

            break;
          case "WEIGHT":
            $("#icon5").css("color", "green");

            break;
          case "PICK":
            $("#icon6").css("color", "green");

            break;
          default:
            // code block
        }
    }


function passwordRevealer(inputId,iconId){
    var input = document.getElementById(inputId);
    var icon = document.getElementById(iconId);
    var inputType = input.type;

    if(inputType == "password"){
        input.type = "text";
        icon.classList.remove("bi-eye");
        icon.classList.add("bi-eye-slash");
    }else if(inputType == "text"){
        input.type = "password";
        icon.classList.remove("bi-eye-slash");
        icon.classList.add("bi-eye");
    }

    function openCalender(dateTemp) {

        // var date = dateTemp;
        const myDatePicker = MCDatepicker.create({
            el: dateTemp,
            dateFormat: 'YYYY/MM/DD',
            bodyType: 'inline',
            theme: {
                display: {
                    foreground: 'rgba(255, 255, 255, 0.8)',
                    background: '#18477E'
                },
                main_background: '#fff',
                weekday: {
                    foreground: '#18477E'
                },
                date: {
                    active: {
                        picked: {
                            background: '#18477E'
                        }
                    }
                },
                button: {
                    success: {
                        foreground: '#18477E'
                    }
                },
            },
        });

        myDatePicker.open();

    }
}

function saveScreen(url){
    const tag = document.createElement("a");
    tag.href = url;
    tag.click();
}