let currentPasswordErrMsg = "";
let newPasswordErrMsg = "";
let confirmPasswordErrMsg = "";
let inputPasswordInvalidErrMsg = "";
let confirmPasswordInvalidErrMsg = "";

let companyCodeEmptyErrMsg = "";
let companyCodeNumberErrMsg = "";
let companyCodeJpnCharacterErrMsg = "";
let companyCodeAlreadyExistErrMsg = "";
let companyJpnNameEmptyErrMsg = "";
let companyJpnNameAlradyErrMsg = "";
let companyEngNameEmptyErrMsg = "";
let companyEngNameAlradyErrMsg = "";
let countryNameEmptyErrMsg = "";

let userCodeEmptyErrorMsg = "";
let userCodeNumberErrMsg = "";
let userCodeJpnCharacterErrMsg = "";
let companyNameEmptyErrorMsg = "";
let userNameEmptyErrorMsg = "";
let startDateEmptyErrorMsg = "";
let endDateEmptyErrorMsg = "";
let startGreaterEndDateErrorMsg = "";
let userCodeAlreadyExistErrMsg = "";
let roleEmptyErrMsg = "";

let portCodeEmptyErrMsg = "";
let portNameEmptyErrMsg = "";
let portCodeAlreadyExistErrMsg = "";
let portCodeNumberErrMsg = "";
let portCodeJpnCharacterErrMsg = "";
let portNameAlreadyExistErrMsg = "";

let customerCodeEmptyErrMsg = "";
let invoiceCodeEmptyErrMsg = "";
let bookingNoEmptyErrMsg = "";
let poNoEmptyErrMsg = "";
let shippingAgencyEmptyErrMsg = "";
let tradeEmptyErrMsg = "";
let incotermsEmptyErrMsg = "";
let shipNameEmptyErrMsg = "";
let VOYNoEmptyErrMsg = "";
let billingMonthEmptyErrMsg = "";
let packingEmptyErrMsg = "";
let transportModeEmptyErrMsg = "";
let fortyFEmptyErrMsg = "";
let blNoAwbNoEmptyErrMsg = "";
let invoicePriceEmptyErrMsg = "";
let twentyFEmptyErrMsg = "";
let departPortEmptyErrMsg = "";
let arrivalPortEmptyErrMsg = "";
let cutEmptyErrMsg = "";
let etdDateEmptyErrMsg = "";
let etaDateEmptyErrMsg = "";
let anAdvancedFeeEmptyErrMsg = "";
let supplierEmptyErrMsg = "";
let typeItemEmptyErrMsg = "";
let cargoKgEmptyErrMsg = "";
let cargoM3EmptyErrMsg = "";
let ctsNosEmptyErrMsg = "";
let sizeEmptyErrMsg = "";
let hlPackBoxEmptyErrMsg = "";
let exchangeEmptyErrMsg = "";
let advanceAmtEmptyErrMsg = "";
let declarationDtEmptyErrMsg = "";
let inspectionDtEmptyErrMsg = "";
let deliveryDtEmptyErrMsg = "";
let possiblePickupDtEmptyErrMsg = "";
let pickupDtEmptyErrMsg = "";
let containerNoEmptyErrMsg = "";
let cargoInsuranceEmptyErrMsg = "";
let logisticsCostEmptyErrMsg = "";
let digitOnlyErrMsg = "";
let exportNotSelectErrMsg = "";
let exportInportEmptyErrMsg = "";
let rtShipEmptyErrMsg = "";
let vwAirEmptyErrMsg = "";
let inportTaxAdvanceAmtEmptyErrMsg = "";

let companyEmptyErrMsg = "";
let customerEmptyErrMsg = "";
let phoneNoEmptyErrMsg = "";
let mailEmptyErrMsg = "";
let exportItemEmptyErrMsg = "";
let freeTimeEmptyErrMsg = "";


let countryNameAlreadyExistErrMsg = "";
let countryCodeEmptyErrMsg = "";
let countryNameEnterEmptyErrMsg = "";
let countryCodeAlreadyExistErrMsg = "";
let countryCodeJpnCharacterErrMsg = "";
let patternNameAlreadyExistErrMsg = "";
let patternNameEmptyErrMsg = "";
let packingJpnCharacterErrMsg = "";
let packingNumberErrMsg = "";
let fortyFJpnCharacterErrMsg = "";
let fortyFNumberErrMsg = "";
let twentyFJpnCharacterErrMsg = "";
let twentyFNumberErrMsg = "";
let invoicePriceJpnCharacterErrMsg = "";
let invoicePriceNumberErrMsg = "";
let anAdvanceFeeJpnCharacterErrMsg = "";
let anAdvanceFeeNumberErrMsg = "";
let cargoKgJpnCharacterErrMsg = "";
let cargoKgNumberErrMsg = "";
let cargoM3JpnCharacterErrMsg = "";
let cargoM3NumberErrMsg = "";
let ctsNumberJpnCharacterErrMsg = "";
let ctsNumberDigitErrMsg = "";
let hlBoxesNumberJpnCharacterErrMsg = "";
let hlBoxesNumberDigitErrMsg = "";
let exchangeJpnCharacterErrMsg = "";
let exchangeNumberErrMsg = "";
let importTaxAdvanceAmountJpnCharacterErrMsg = "";
let importTaxAdvanceAmountNumberErrMsg ="";
let logisticsCostJpnCharacterErrMsg = "";
let logisticsCostNumberErrMsg = "";

let groupCodeEmptyErrMsg = "";
let groupNameEmptyErrMsg = "";
let repCompanyEmptyErrMsg = "";
let groupCodeJpnCharacterErrMsg = "";
let groupCodeNumberErrMsg = "";
let groupCodeAlreadyExistErrMsg = "";

let productNameAlreadyExistErrMsg = "";
let productNameEmptyErrMsg = "";
let productCodeEmptyErrMsg = "";
let productCodeJpnCharacterErrMsg = "";
let productCodeNumberErrMsg = "";
let productCodeAlreadyExistErrMsg = "";

function loadErrorMessage() {
    $.get('/getPasswordChangeErrorMessage', function (data) {

        currentPasswordErrMsg = data.data[0]
        newPasswordErrMsg = data.data[1]
        confirmPasswordErrMsg = data.data[2]
        inputPasswordInvalidErrMsg = data.data[3]
        confirmPasswordInvalidErrMsg = data.data[4]
    });

    $.get('/getCompanyErrorMessage', function (data) {

        companyCodeEmptyErrMsg = data.data[0]
        companyCodeNumberErrMsg = data.data[1]
        companyCodeJpnCharacterErrMsg = data.data[2]
        companyCodeAlreadyExistErrMsg = data.data[3]
        companyJpnNameEmptyErrMsg = data.data[4]
        companyJpnNameAlradyErrMsg = data.data[5]
        companyEngNameEmptyErrMsg = data.data[6]
        companyEngNameAlradyErrMsg = data.data[7]
        countryNameEmptyErrMsg = data.data[8]
    });


    $.get('/getUserErrorMessage', function (data) {

        userCodeEmptyErrorMsg = data.data[0]
        userCodeNumberErrMsg = data.data[1]
        userCodeJpnCharacterErrMsg = data.data[2]
        companyNameEmptyErrorMsg = data.data[3]
        userNameEmptyErrorMsg = data.data[4]
        startDateEmptyErrorMsg = data.data[5]
        endDateEmptyErrorMsg = data.data[6]
        startGreaterEndDateErrorMsg = data.data[7]
        userCodeAlreadyExistErrMsg = data.data[8]
        roleEmptyErrMsg = data.data[9]
    });

    $.get('/getPortErrorMessage', function (data) {

        portCodeEmptyErrMsg = data.data[0]
        portNameEmptyErrMsg = data.data[1]
        portCodeAlreadyExistErrMsg = data.data[2]
        portCodeNumberErrMsg = data.data[3]
        portCodeJpnCharacterErrMsg = data.data[4]
        portNameAlreadyExistErrMsg = data.data[5]
    });

    $.get('/getExportRegistrationErrorMessage', function (data) {

        customerCodeEmptyErrMsg = data.data[0]
        invoiceCodeEmptyErrMsg = data.data[1]
        bookingNoEmptyErrMsg = data.data[2]
        poNoEmptyErrMsg = data.data[3]
        shippingAgencyEmptyErrMsg = data.data[4]
        tradeEmptyErrMsg = data.data[5]
        incotermsEmptyErrMsg = data.data[6]
        shipNameEmptyErrMsg = data.data[7]
        VOYNoEmptyErrMsg = data.data[8]
        billingMonthEmptyErrMsg = data.data[9]
        packingEmptyErrMsg = data.data[10]
        transportModeEmptyErrMsg = data.data[11]
        fortyFEmptyErrMsg = data.data[12]
        blNoAwbNoEmptyErrMsg = data.data[13]
        invoicePriceEmptyErrMsg = data.data[14]
        twentyFEmptyErrMsg = data.data[15]
        departPortEmptyErrMsg = data.data[16]
        arrivalPortEmptyErrMsg = data.data[17]
        cutEmptyErrMsg = data.data[18]
        etdDateEmptyErrMsg = data.data[19]
        etaDateEmptyErrMsg = data.data[20]
        anAdvancedFeeEmptyErrMsg = data.data[21]
        supplierEmptyErrMsg = data.data[22]
        typeItemEmptyErrMsg = data.data[23]
        cargoKgEmptyErrMsg = data.data[24]
        cargoM3EmptyErrMsg = data.data[25]
        ctsNosEmptyErrMsg = data.data[26]
        sizeEmptyErrMsg = data.data[27]
        hlPackBoxEmptyErrMsg = data.data[28]
        exchangeEmptyErrMsg = data.data[29]
        advanceAmtEmptyErrMsg = data.data[30]
        declarationDtEmptyErrMsg = data.data[31]
        inspectionDtEmptyErrMsg = data.data[32]
        deliveryDtEmptyErrMsg = data.data[33]
        possiblePickupDtEmptyErrMsg = data.data[34]
        pickupDtEmptyErrMsg = data.data[35]
        containerNoEmptyErrMsg = data.data[36]
        cargoInsuranceEmptyErrMsg = data.data[37]
        logisticsCostEmptyErrMsg = data.data[38]
        digitOnlyErrMsg = data.data[39]
        // exportNotSelectErrMsg = data.data[40]
        // exportInportEmptyErrMsg = data.data[40]
        rtShipEmptyErrMsg = data.data[40]
        vwAirEmptyErrMsg = data.data[41]
        inportTaxAdvanceAmtEmptyErrMsg = data.data[42]
        freeTimeEmptyErrMsg = data.data[43]
        patternNameAlreadyExistErrMsg = data.data[44]
        patternNameEmptyErrMsg = data.data[45]
        packingJpnCharacterErrMsg = data.data[46]
        packingNumberErrMsg = data.data[47]
        fortyFJpnCharacterErrMsg = data.data[48]
        fortyFNumberErrMsg = data.data[49]
        twentyFJpnCharacterErrMsg = data.data[50]
        twentyFNumberErrMsg = data.data[51]
        invoicePriceJpnCharacterErrMsg = data.data[52]
        invoicePriceNumberErrMsg = data.data[53]
        anAdvanceFeeJpnCharacterErrMsg = data.data[54]
        anAdvanceFeeNumberErrMsg = data.data[55]
        cargoKgJpnCharacterErrMsg = data.data[56]
        cargoKgNumberErrMsg = data.data[57]
        cargoM3JpnCharacterErrMsg = data.data[58]
        cargoM3NumberErrMsg = data.data[59]
        ctsNumberJpnCharacterErrMsg = data.data[60]
        ctsNumberDigitErrMsg = data.data[61]
        hlBoxesNumberJpnCharacterErrMsg = data.data[62]
        hlBoxesNumberDigitErrMsg = data.data[63]
        exchangeJpnCharacterErrMsg = data.data[64]
        exchangeNumberErrMsg = data.data[65]
        importTaxAdvanceAmountJpnCharacterErrMsg = data.data[66]
        importTaxAdvanceAmountNumberErrMsg = data.data[67]
        logisticsCostJpnCharacterErrMsg = data.data[68]
        logisticsCostNumberErrMsg = data.data[69]

    });

    $.get('/getInquiryErrorMessage', function (data) {

        companyEmptyErrMsg = data.data[0]
        customerEmptyErrMsg = data.data[1]
        phoneNoEmptyErrMsg = data.data[2]
        mailEmptyErrMsg = data.data[3]
        exportItemEmptyErrMsg = data.data[4]
    });

    $.get('/getCountryErrorMessage', function (data) {

        countryNameAlreadyExistErrMsg = data.data[0];
        countryCodeEmptyErrMsg = data.data[1];
        countryNameEnterEmptyErrMsg = data.data[2];
        countryCodeAlreadyExistErrMsg = data.data[3];
        countryCodeJpnCharacterErrMsg = data.data[4];
    });

    $.get('/getGroupErrorMessage', function (data) {

        groupCodeEmptyErrMsg = data.data[0];
        groupNameEmptyErrMsg = data.data[1];
        repCompanyEmptyErrMsg = data.data[2];
        groupCodeJpnCharacterErrMsg = data.data[3];
        groupCodeNumberErrMsg = data.data[4];
        groupCodeAlreadyExistErrMsg = data.data[5];

    });

    $.get('/getProductErrorMessage', function (data) {

        productNameAlreadyExistErrMsg = data.data[0];
        productNameEmptyErrMsg = data.data[1];
        productCodeEmptyErrMsg = data.data[2];
        productCodeJpnCharacterErrMsg = data.data[3];
        productCodeNumberErrMsg = data.data[4];
        productCodeAlreadyExistErrMsg = data.data[5];

    });
}

function loadInquiryMessage(){
    $.get('/getInquiryErrorMessage', function (data) {

        companyEmptyErrMsg = data.data[0]
        customerEmptyErrMsg = data.data[1]
        phoneNoEmptyErrMsg = data.data[2]
        mailEmptyErrMsg = data.data[3]
        exportItemEmptyErrMsg = data.data[4]
    });
}