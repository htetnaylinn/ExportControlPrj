let companyCodeEmptyErrMsg = "";
let companyCodeNumberErrMsg = "";
let companyCodeJpnCharacterErrMsg = "";
let companyCodeAlreadyExistErrMsg = "";
let companyJpnNameEmptyErrMsg = "";
let companyJpnNameAlradyErrMsg= "";
let companyEngNameEmptyErrMsg = "";
let companyEngNameAlradyErrMsg= "";
let countryNameEmptyErrMsg= "";

function loadCompanyErrorMessage(){
    $.get('/getCompanyErrorMessage', function(data){

        companyCodeEmptyErrMsg = data.data[0]
        companyCodeNumberErrMsg = data.data[1]
        companyCodeJpnCharacterErrMsg = data.data[2]
        companyCodeAlreadyExistErrMsg = data.data[3]
        companyJpnNameEmptyErrMsg = data.data[4]
        companyJpnNameAlradyErrMsg= data.data[5]
        companyEngNameEmptyErrMsg = data.data[6]
        companyEngNameAlradyErrMsg= data.data[7]
        countryNameEmptyErrMsg= data.data[8]
    });
}