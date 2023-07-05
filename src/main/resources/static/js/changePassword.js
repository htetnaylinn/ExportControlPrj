
function passwordValidator(validPsw,inputPsw){

         if(validPsw == inputPsw){
            return true;
         }else{
            return false;
         }
}


function showPswChangeSuccess(successMsg){
        iziToast.show({
        titleColor: 'green',
        overlay: true,
        overlayColor:'rgba(0, 0, 0, 0)',
        title: successMsg,
        timeout: 2000,
        animateInside:'false',
        zindex: 1999,
        backgroundColor: 'white',
        transitionIn: 'bounceInDown',
        position: 'topCenter', // bottomRight, bottomLeft, topRight, topLeft, topCenter, bottomCenter
        progressBarColor: 'green',
      });
    }