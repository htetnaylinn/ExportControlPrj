package com.example.exportControl.restController

import com.example.exportControl.message.Response
import com.example.exportControl.model.PatternRegistration
import com.example.exportControl.model.requestModel.PatternRequestModel
import com.example.exportControl.repository.CompanyMasterRepository
import com.example.exportControl.repository.PatternRegistrationRepository
import com.example.exportControl.repository.ProductMasterRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@CrossOrigin(origins = arrayOf("*"))
class AjaxMessage {
    @Autowired
    var messageSource: MessageSource? = null

    @Autowired
    var companyRepo: CompanyMasterRepository? = null

    @Autowired
    private val patternRepo: PatternRegistrationRepository? = null

    @Autowired
    private val proRepo: ProductMasterRepository? = null

    @GetMapping(value = ["/getPasswordChangeErrorMessage"])
    fun getMyAjaxMessage(): Response {
        val messageList: MutableList<String> = ArrayList()
        val currentPasswordErrMsg = messageSource?.getMessage("currentPasswordErrMsg", null, Locale.ENGLISH).toString()
        val newPasswordErrMsg = messageSource?.getMessage("newPasswordErrMsg", null, Locale.ENGLISH).toString()
        val confirmPasswordErrMsg = messageSource?.getMessage("confirmPasswordErrMsg", null, Locale.ENGLISH).toString()
        val inputPasswordInvalidErrMsg =
            messageSource?.getMessage("inputPasswordInvalidErrMsg", null, Locale.ENGLISH).toString()
        val confirmPasswordInvalidErrMsg =
            messageSource?.getMessage("confirmPasswordInvalidErrMsg", null, Locale.ENGLISH).toString()

        messageList.add(currentPasswordErrMsg)
        messageList.add(newPasswordErrMsg)
        messageList.add(confirmPasswordErrMsg)
        messageList.add(inputPasswordInvalidErrMsg)
        messageList.add(confirmPasswordInvalidErrMsg)

        return Response("message", messageList)
    }

    @GetMapping(value = ["/getExportData/patternName"])
    fun getExportData(@RequestParam(value = "patternName") patternName: String?): Response {
        val patternObj: PatternRegistration? = patternRepo?.getPatternObj(patternName)
        return Response("message", patternObj)
    }

    // .setDateFormat("dd/MM/yyyy")
    @GetMapping(value = ["/patternEdit/tblData"])
    fun getPatternList(@RequestParam(value = "tblData") tblData: String?): String {
        val exportData: MutableList<String> = ArrayList()

        val je: JsonElement = JsonParser().parse(tblData)
        val gson: Gson = GsonBuilder()
            .setDateFormat("yyyy/MM/dd")
            .create()

        val patternArr = gson.fromJson(tblData, Array<PatternRegistration>::class.java).asList()

        for (innerArray in patternArr) {
            val patternReq = PatternRequestModel()
            patternReq.patternCode = innerArray.patternCode
            patternReq.invoiceCode = innerArray.invoiceCode
            patternReq.customerName = innerArray.customerName
            patternReq.patternName = innerArray.patternName
            patternReq.billingMonth = innerArray.billingMonth
            patternReq.bookingNo = innerArray.bookingNo
            patternReq.poNO = innerArray.poNO
            patternReq.shippingAgency = innerArray.shippingAgency
            patternReq.exportInport = innerArray.exportInport
            patternReq.incoterms = innerArray.incoterms
            patternReq.shipName = innerArray.shipName
            patternReq.voyNo = innerArray.voyNo
            patternReq.packing = innerArray.packing
            patternReq.transportMode = innerArray.transportMode
            patternReq.fourtyf = innerArray.fourtyf
            patternReq.twentyf = innerArray.twentyf
            patternReq.invoicePrice = innerArray.invoicePrice
            patternReq.blawbNo = innerArray.blawbNo
            patternReq.departurePort = innerArray.departurePort
            patternReq.arrivalPort = innerArray.arrivalPort
            patternReq.cut = innerArray.cut
            patternReq.etd = innerArray.etd
            patternReq.eta = innerArray.eta
            patternReq.anAdvanceFee = innerArray.anAdvanceFee
            patternReq.supplierName = innerArray.supplierName
            patternReq.representativeItem = innerArray.representativeItem
            patternReq.freightKg = innerArray.freightKg
            patternReq.freightM3 = innerArray.freightM3
            patternReq.ctsNumber = innerArray.ctsNumber
            patternReq.size = innerArray.size
            patternReq.rtShip = innerArray.rtShip
            patternReq.vwAir = innerArray.vwAir
            patternReq.hlBoxesNumber = innerArray.hlBoxesNumber
            patternReq.moneyOrder = innerArray.moneyOrder
            patternReq.inportTaxAdvanceAmount = innerArray.inportTaxAdvanceAmount
            patternReq.remarks = innerArray.remarks
            patternReq.declarationDate = innerArray.declarationDate
            patternReq.inspectionDate = innerArray.inspectionDate
            patternReq.deliveryDateAndTime = innerArray.deliveryDateAndTime
            patternReq.possiblePickUpDate = innerArray.possiblePickUpDate
            patternReq.pickUpDate = innerArray.pickUpDate
            patternReq.containerNo = innerArray.containerNo
            patternReq.cargoInsurance = innerArray.cargoInsurance
            patternReq.logisticsCost = innerArray.logisticsCost
            patternReq.freeTime = innerArray.freeTime
            patternRepo?.updatePatternRegistration(patternReq)
        }
        //return Response("message", "message")
        return "redirect:/patternEdit"
    }

    @GetMapping(value = ["/getCompanyErrorMessage"])
    fun getCompanyErrorMessage(): Response {
        val messageList: MutableList<String> = ArrayList()
        val companyCodeEmptyErrMsg =
            messageSource?.getMessage("companyCodeEmptyErrMsg", null, Locale.ENGLISH).toString()
        val companyCodeNumberErrMsg =
            messageSource?.getMessage("companyCodeNumberErrMsg", null, Locale.ENGLISH).toString()
        val companyCodeJpnCharacterErrMsg =
            messageSource?.getMessage("companyCodeJpnCharacterErrMsg", null, Locale.ENGLISH).toString()
        val companyCodeAlreadyExistErrMsg =
            messageSource?.getMessage("companyCodeAlreadyExistErrMsg", null, Locale.ENGLISH).toString()
        val companyJpnNameEmptyErrMsg =
            messageSource?.getMessage("companyJpnNameEmptyErrMsg", null, Locale.ENGLISH).toString()
        val companyJpnNameAlradyErrMsg =
            messageSource?.getMessage("companyJpnNameAlradyErrMsg", null, Locale.ENGLISH).toString()
        val companyEngNameEmptyErrMsg =
            messageSource?.getMessage("companyEngNameEmptyErrMsg", null, Locale.ENGLISH).toString()
        val companyEngNameAlradyErrMsg =
            messageSource?.getMessage("companyEngNameAlradyErrMsg", null, Locale.ENGLISH).toString()
        val countryNameEmptyErrMsg =
            messageSource?.getMessage("countryNameEmptyErrMsg", null, Locale.ENGLISH).toString()

        messageList.add(companyCodeEmptyErrMsg)
        messageList.add(companyCodeNumberErrMsg)
        messageList.add(companyCodeJpnCharacterErrMsg)
        messageList.add(companyCodeAlreadyExistErrMsg)
        messageList.add(companyJpnNameEmptyErrMsg)
        messageList.add(companyJpnNameAlradyErrMsg)
        messageList.add(companyEngNameEmptyErrMsg)
        messageList.add(companyEngNameAlradyErrMsg)
        messageList.add(countryNameEmptyErrMsg)
        return Response("message", messageList)
    }


    @GetMapping(value = ["/getUserErrorMessage"])
    fun getUserErrorMessage(): Response {
        val messageList: MutableList<String> = ArrayList()
        val userCodeEmptyErrorMsg = messageSource?.getMessage("userCodeEmptyErrorMsg", null, Locale.ENGLISH).toString()
        val userCodeNumberErrMsg = messageSource?.getMessage("userCodeNumberErrMsg", null, Locale.ENGLISH).toString()
        val userCodeJpnCharacterErrMsg =
            messageSource?.getMessage("userCodeJpnCharacterErrMsg", null, Locale.ENGLISH).toString()
        val companyNameEmptyErrorMsg =
            messageSource?.getMessage("companyNameEmptyErrorMsg", null, Locale.ENGLISH).toString()
        val userNameEmptyErrorMsg = messageSource?.getMessage("userNameEmptyErrorMsg", null, Locale.ENGLISH).toString()
        val startDateEmptyErrorMsg =
            messageSource?.getMessage("startDateEmptyErrorMsg", null, Locale.ENGLISH).toString()
        val endDateEmptyErrorMsg = messageSource?.getMessage("endDateEmptyErrorMsg", null, Locale.ENGLISH).toString()
        val startGreaterEndDateErrorMsg =
            messageSource?.getMessage("startGreaterEndDateErrorMsg", null, Locale.ENGLISH).toString()
        val userCodeAlreadyExistErrMsg =
            messageSource?.getMessage("userCodeAlreadyExistErrMsg", null, Locale.ENGLISH).toString()
        val roleEmptyErrMsg =
            messageSource?.getMessage("roleEmptyErrMsg", null, Locale.ENGLISH).toString()

        messageList.add(userCodeEmptyErrorMsg)
        messageList.add(userCodeNumberErrMsg)
        messageList.add(userCodeJpnCharacterErrMsg)
        messageList.add(companyNameEmptyErrorMsg)
        messageList.add(userNameEmptyErrorMsg)
        messageList.add(startDateEmptyErrorMsg)
        messageList.add(endDateEmptyErrorMsg)
        messageList.add(startGreaterEndDateErrorMsg)
        messageList.add(userCodeAlreadyExistErrMsg)
        messageList.add(roleEmptyErrMsg)

        return Response("message", messageList)
    }

    @GetMapping(value = ["/getPortErrorMessage"])
    fun getPortErrorMessage(): Response {
        val messageList: MutableList<String> = ArrayList()
        val portCodeEmptyErrMsg = messageSource?.getMessage("portCodeEmptyErrMsg", null, Locale.ENGLISH).toString()
        val portNameEmptyErrMsg = messageSource?.getMessage("portNameEmptyErrMsg", null, Locale.ENGLISH).toString()
        val portCodeAlreadyExistErrMsg =
            messageSource?.getMessage("portCodeAlreadyExistErrMsg", null, Locale.ENGLISH).toString()
        val portCodeNumberErrMsg = messageSource?.getMessage("portCodeNumberErrMsg", null, Locale.ENGLISH).toString()
        val portCodeJpnCharacterErrMsg =
            messageSource?.getMessage("portCodeJpnCharacterErrMsg", null, Locale.ENGLISH).toString()
        val portNameAlreadyExistErrMsg =
            messageSource?.getMessage("portNameAlreadyExistErrMsg", null, Locale.ENGLISH).toString()


        messageList.add(portCodeEmptyErrMsg)
        messageList.add(portNameEmptyErrMsg)
        messageList.add(portCodeAlreadyExistErrMsg)
        messageList.add(portCodeNumberErrMsg)
        messageList.add(portCodeJpnCharacterErrMsg)
        messageList.add(portNameAlreadyExistErrMsg)

        return Response("message", messageList)
    }

    @GetMapping(value = ["/getExportRegistrationErrorMessage"])
    fun getExportRegistrationErrorMessage(): Response {
        val messageList: MutableList<String> = ArrayList()

        val customerCodeEmptyErrMsg =
            messageSource?.getMessage("customerCodeEmptyErrMsg", null, Locale.ENGLISH).toString()
        val invoiceCodeEmptyErrMsg =
            messageSource?.getMessage("invoiceCodeEmptyErrMsg", null, Locale.ENGLISH).toString()
        val bookingNoEmptyErrMsg = messageSource?.getMessage("bookingNoEmptyErrMsg", null, Locale.ENGLISH).toString()
        val poNoEmptyErrMsg = messageSource?.getMessage("poNoEmptyErrMsg", null, Locale.ENGLISH).toString()
        val shippingAgencyEmptyErrMsg =
            messageSource?.getMessage("shippingAgencyEmptyErrMsg", null, Locale.ENGLISH).toString()
        val tradeEmptyErrMsg = messageSource?.getMessage("tradeEmptyErrMsg", null, Locale.ENGLISH).toString()
        val incotermsEmptyErrMsg = messageSource?.getMessage("incotermsEmptyErrMsg", null, Locale.ENGLISH).toString()
        val shipNameEmptyErrMsg = messageSource?.getMessage("shipNameEmptyErrMsg", null, Locale.ENGLISH).toString()
        val VOYNoEmptyErrMsg = messageSource?.getMessage("VOYNoEmptyErrMsg", null, Locale.ENGLISH).toString()
        val billingMonthEmptyErrMsg =
            messageSource?.getMessage("billingMonthEmptyErrMsg", null, Locale.ENGLISH).toString()
        val packingEmptyErrMsg = messageSource?.getMessage("packingEmptyErrMsg", null, Locale.ENGLISH).toString()
        val transportModeEmptyErrMsg =
            messageSource?.getMessage("transportModeEmptyErrMsg", null, Locale.ENGLISH).toString()
        val fortyFEmptyErrMsg = messageSource?.getMessage("fortyFEmptyErrMsg", null, Locale.ENGLISH).toString()
        val blNoAwbNoEmptyErrMsg = messageSource?.getMessage("blNoAwbNoEmptyErrMsg", null, Locale.ENGLISH).toString()
        val invoicePriceEmptyErrMsg =
            messageSource?.getMessage("invoicePriceEmptyErrMsg", null, Locale.ENGLISH).toString()
        val twentyFEmptyErrMsg = messageSource?.getMessage("twentyFEmptyErrMsg", null, Locale.ENGLISH).toString()
        val departPortEmptyErrMsg = messageSource?.getMessage("departPortEmptyErrMsg", null, Locale.ENGLISH).toString()
        val arrivalPortEmptyErrMsg =
            messageSource?.getMessage("arrivalPortEmptyErrMsg", null, Locale.ENGLISH).toString()
        val cutEmptyErrMsg = messageSource?.getMessage("cutEmptyErrMsg", null, Locale.ENGLISH).toString()
        val etdDateEmptyErrMsg = messageSource?.getMessage("etdDateEmptyErrMsg", null, Locale.ENGLISH).toString()
        val etaDateEmptyErrMsg = messageSource?.getMessage("etaDateEmptyErrMsg", null, Locale.ENGLISH).toString()
        val anAdvancedFeeEmptyErrMsg =
            messageSource?.getMessage("anAdvancedFeeEmptyErrMsg", null, Locale.ENGLISH).toString()
        val supplierEmptyErrMsg = messageSource?.getMessage("supplierEmptyErrMsg", null, Locale.ENGLISH).toString()
        val typeItemEmptyErrMsg = messageSource?.getMessage("typeItemEmptyErrMsg", null, Locale.ENGLISH).toString()
        val cargoKgEmptyErrMsg = messageSource?.getMessage("cargoKgEmptyErrMsg", null, Locale.ENGLISH).toString()
        val cargoM3EmptyErrMsg = messageSource?.getMessage("cargoM3EmptyErrMsg", null, Locale.ENGLISH).toString()
        val ctsNosEmptyErrMsg = messageSource?.getMessage("ctsNosEmptyErrMsg", null, Locale.ENGLISH).toString()
        val sizeEmptyErrMsg = messageSource?.getMessage("sizeEmptyErrMsg", null, Locale.ENGLISH).toString()
        val hlPackBoxEmptyErrMsg = messageSource?.getMessage("hlPackBoxEmptyErrMsg", null, Locale.ENGLISH).toString()
        val exchangeEmptyErrMsg = messageSource?.getMessage("exchangeEmptyErrMsg", null, Locale.ENGLISH).toString()
        val advanceAmtEmptyErrMsg = messageSource?.getMessage("advanceAmtEmptyErrMsg", null, Locale.ENGLISH).toString()
        val declarationDtEmptyErrMsg =
            messageSource?.getMessage("declarationDtEmptyErrMsg", null, Locale.ENGLISH).toString()
        val inspectionDtEmptyErrMsg =
            messageSource?.getMessage("inspectionDtEmptyErrMsg", null, Locale.ENGLISH).toString()
        val deliveryDtEmptyErrMsg = messageSource?.getMessage("deliveryDtEmptyErrMsg", null, Locale.ENGLISH).toString()
        val possiblePickupDtEmptyErrMsg =
            messageSource?.getMessage("possiblePickupDtEmptyErrMsg", null, Locale.ENGLISH).toString()
        val pickupDtEmptyErrMsg = messageSource?.getMessage("pickupDtEmptyErrMsg", null, Locale.ENGLISH).toString()
        val containerNoEmptyErrMsg =
            messageSource?.getMessage("containerNoEmptyErrMsg", null, Locale.ENGLISH).toString()
        val cargoInsuranceEmptyErrMsg =
            messageSource?.getMessage("cargoInsuranceEmptyErrMsg", null, Locale.ENGLISH).toString()
        val logisticsCostEmptyErrMsg =
            messageSource?.getMessage("logisticsCostEmptyErrMsg", null, Locale.ENGLISH).toString()
        val digitOnlyErrMsg = messageSource?.getMessage("digitOnlyErrMsg", null, Locale.ENGLISH).toString()
//        val exportNotSelectErrMsg = messageSource?.getMessage("exportNotSelectErrMsg", null, Locale.ENGLISH).toString()
//        val exportInportEmptyErrMsg = messageSource?.getMessage("exportInportEmptyErrMsg", null, Locale.ENGLISH).toString()
        val rtShipEmptyErrMsg = messageSource?.getMessage("rtShipEmptyErrMsg", null, Locale.ENGLISH).toString()
        val vwAirEmptyErrMsg = messageSource?.getMessage("vwAirEmptyErrMsg", null, Locale.ENGLISH).toString()
        val inportTaxAdvanceAmtEmptyErrMsg =
            messageSource?.getMessage("inportTaxAdvanceAmtEmptyErrMsg", null, Locale.ENGLISH).toString()
        val freeTimeEmptyErrMsg = messageSource?.getMessage("freeTimeEmptyErrMsg", null, Locale.ENGLISH).toString()
        val patternNameAlreadyExistErrMsg =
            messageSource?.getMessage("patternNameAlreadyExistErrMsg", null, Locale.ENGLISH).toString()
        val patternNameEmptyErrMsg =
            messageSource?.getMessage("patternNameEmptyErrMsg", null, Locale.ENGLISH).toString()

        val packingJpnCharacterErrMsg =
            messageSource?.getMessage("packingJpnCharacterErrMsg", null, Locale.ENGLISH).toString()
        val packingNumberErrMsg = messageSource?.getMessage("packingNumberErrMsg", null, Locale.ENGLISH).toString()
        val fortyFJpnCharacterErrMsg =
            messageSource?.getMessage("fortyFJpnCharacterErrMsg", null, Locale.ENGLISH).toString()
        val fortyFNumberErrMsg = messageSource?.getMessage("fortyFNumberErrMsg", null, Locale.ENGLISH).toString()
        val twentyFJpnCharacterErrMsg =
            messageSource?.getMessage("twentyFJpnCharacterErrMsg", null, Locale.ENGLISH).toString()
        val twentyFNumberErrMsg = messageSource?.getMessage("twentyFNumberErrMsg", null, Locale.ENGLISH).toString()
        val invoicePriceJpnCharacterErrMsg =
            messageSource?.getMessage("invoicePriceJpnCharacterErrMsg", null, Locale.ENGLISH).toString()
        val invoicePriceNumberErrMsg =
            messageSource?.getMessage("invoicePriceNumberErrMsg", null, Locale.ENGLISH).toString()
        val anAdvanceFeeJpnCharacterErrMsg =
            messageSource?.getMessage("anAdvanceFeeJpnCharacterErrMsg", null, Locale.ENGLISH).toString()
        val anAdvanceFeeNumberErrMsg =
            messageSource?.getMessage("anAdvanceFeeNumberErrMsg", null, Locale.ENGLISH).toString()
        val cargoKgJpnCharacterErrMsg =
            messageSource?.getMessage("cargoKgJpnCharacterErrMsg", null, Locale.ENGLISH).toString()
        val cargoKgNumberErrMsg = messageSource?.getMessage("cargoKgNumberErrMsg", null, Locale.ENGLISH).toString()
        val cargoM3JpnCharacterErrMsg =
            messageSource?.getMessage("cargoM3JpnCharacterErrMsg", null, Locale.ENGLISH).toString()
        val cargoM3NumberErrMsg = messageSource?.getMessage("cargoM3NumberErrMsg", null, Locale.ENGLISH).toString()
        val ctsNumberJpnCharacterErrMsg =
            messageSource?.getMessage("ctsNumberJpnCharacterErrMsg", null, Locale.ENGLISH).toString()
        val ctsNumberDigitErrMsg = messageSource?.getMessage("ctsNumberDigitErrMsg", null, Locale.ENGLISH).toString()
        val hlBoxesNumberJpnCharacterErrMsg =
            messageSource?.getMessage("hlBoxesNumberJpnCharacterErrMsg", null, Locale.ENGLISH).toString()
        val hlBoxesNumberDigitErrMsg =
            messageSource?.getMessage("hlBoxesNumberDigitErrMsg", null, Locale.ENGLISH).toString()
        val exchangeJpnCharacterErrMsg =
            messageSource?.getMessage("exchangeJpnCharacterErrMsg", null, Locale.ENGLISH).toString()
        val exchangeNumberErrMsg =
            messageSource?.getMessage("exchangeNumberErrMsg", null, Locale.ENGLISH).toString()
        val importTaxAdvanceAmountJpnCharacterErrMsg =
            messageSource?.getMessage("importTaxAdvanceAmountJpnCharacterErrMsg", null, Locale.ENGLISH).toString()
        val importTaxAdvanceAmountNumberErrMsg =
            messageSource?.getMessage("importTaxAdvanceAmountNumberErrMsg", null, Locale.ENGLISH).toString()
        val logisticsCostJpnCharacterErrMsg =
            messageSource?.getMessage("logisticsCostJpnCharacterErrMsg", null, Locale.ENGLISH).toString()
        val logisticsCostNumberErrMsg =
            messageSource?.getMessage("logisticsCostNumberErrMsg", null, Locale.ENGLISH).toString()

        messageList.add(customerCodeEmptyErrMsg)
        messageList.add(invoiceCodeEmptyErrMsg)
        messageList.add(bookingNoEmptyErrMsg)
        messageList.add(poNoEmptyErrMsg)
        messageList.add(shippingAgencyEmptyErrMsg)
        messageList.add(tradeEmptyErrMsg)
        messageList.add(incotermsEmptyErrMsg)
        messageList.add(shipNameEmptyErrMsg)
        messageList.add(VOYNoEmptyErrMsg)
        messageList.add(billingMonthEmptyErrMsg)
        messageList.add(packingEmptyErrMsg)
        messageList.add(transportModeEmptyErrMsg)
        messageList.add(fortyFEmptyErrMsg)
        messageList.add(blNoAwbNoEmptyErrMsg)
        messageList.add(invoicePriceEmptyErrMsg)
        messageList.add(twentyFEmptyErrMsg)
        messageList.add(departPortEmptyErrMsg)
        messageList.add(arrivalPortEmptyErrMsg)
        messageList.add(cutEmptyErrMsg)
        messageList.add(etdDateEmptyErrMsg)
        messageList.add(etaDateEmptyErrMsg)
        messageList.add(anAdvancedFeeEmptyErrMsg)
        messageList.add(supplierEmptyErrMsg)
        messageList.add(typeItemEmptyErrMsg)
        messageList.add(cargoKgEmptyErrMsg)
        messageList.add(cargoM3EmptyErrMsg)
        messageList.add(ctsNosEmptyErrMsg)
        messageList.add(sizeEmptyErrMsg)
        messageList.add(hlPackBoxEmptyErrMsg)
        messageList.add(exchangeEmptyErrMsg)
        messageList.add(advanceAmtEmptyErrMsg)
        messageList.add(declarationDtEmptyErrMsg)
        messageList.add(inspectionDtEmptyErrMsg)
        messageList.add(deliveryDtEmptyErrMsg)
        messageList.add(possiblePickupDtEmptyErrMsg)
        messageList.add(pickupDtEmptyErrMsg)
        messageList.add(containerNoEmptyErrMsg)
        messageList.add(cargoInsuranceEmptyErrMsg)
        messageList.add(logisticsCostEmptyErrMsg)
        messageList.add(digitOnlyErrMsg)
        messageList.add(rtShipEmptyErrMsg)
        messageList.add(vwAirEmptyErrMsg)
        messageList.add(inportTaxAdvanceAmtEmptyErrMsg)
        messageList.add(freeTimeEmptyErrMsg)
        messageList.add(patternNameAlreadyExistErrMsg)
        messageList.add(patternNameEmptyErrMsg)
        messageList.add(packingJpnCharacterErrMsg)
        messageList.add(packingNumberErrMsg)
        messageList.add(fortyFJpnCharacterErrMsg)
        messageList.add(fortyFNumberErrMsg)
        messageList.add(twentyFJpnCharacterErrMsg)
        messageList.add(twentyFNumberErrMsg)
        messageList.add(invoicePriceJpnCharacterErrMsg)
        messageList.add(invoicePriceNumberErrMsg)
        messageList.add(anAdvanceFeeJpnCharacterErrMsg)
        messageList.add(anAdvanceFeeNumberErrMsg)
        messageList.add(cargoKgJpnCharacterErrMsg)
        messageList.add(cargoKgNumberErrMsg)
        messageList.add(cargoM3JpnCharacterErrMsg)
        messageList.add(cargoM3NumberErrMsg)
        messageList.add(ctsNumberJpnCharacterErrMsg)
        messageList.add(ctsNumberDigitErrMsg)
        messageList.add(hlBoxesNumberJpnCharacterErrMsg)
        messageList.add(hlBoxesNumberDigitErrMsg)
        messageList.add(exchangeJpnCharacterErrMsg)
        messageList.add(exchangeNumberErrMsg)
        messageList.add(importTaxAdvanceAmountJpnCharacterErrMsg)
        messageList.add(importTaxAdvanceAmountNumberErrMsg)
        messageList.add(logisticsCostJpnCharacterErrMsg)
        messageList.add(logisticsCostNumberErrMsg)

        return Response("message", messageList)
    }


    @GetMapping(value = ["/getInquiryErrorMessage"])
    fun getInquiryErrorMessage(): Response {
        val messageList: MutableList<String> = ArrayList()
        val companyEmptyErrMsg = messageSource?.getMessage("companyEmptyErrMsg", null, Locale.ENGLISH).toString()
        val customerEmptyErrMsg = messageSource?.getMessage("customerEmptyErrMsg", null, Locale.ENGLISH).toString()
        val phoneNoEmptyErrMsg = messageSource?.getMessage("phoneNoEmptyErrMsg", null, Locale.ENGLISH).toString()
        val mailEmptyErrMsg = messageSource?.getMessage("mailEmptyErrMsg", null, Locale.ENGLISH).toString()
        val exportItemEmptyErrMsg = messageSource?.getMessage("exportItemEmptyErrMsg", null, Locale.ENGLISH).toString()
        messageList.add(companyEmptyErrMsg)
        messageList.add(customerEmptyErrMsg)
        messageList.add(phoneNoEmptyErrMsg)
        messageList.add(mailEmptyErrMsg)
        messageList.add(exportItemEmptyErrMsg)

        return Response("message", messageList)
    }

    @GetMapping(value = ["/getCountryErrorMessage"])
    fun getCountryErrorMessage(): Response {
        val messageList: MutableList<String> = ArrayList()
        val countryNameAlreadyExistErrMsg =
            messageSource?.getMessage("countryNameAlreadyExistErrMsg", null, Locale.ENGLISH).toString()
        val countryCodeEmptyErrMsg =
            messageSource?.getMessage("countryCodeEmptyErrMsg", null, Locale.ENGLISH).toString()
        val countryNameEnterEmptyErrMsg =
            messageSource?.getMessage("countryNameEnterEmptyErrMsg", null, Locale.ENGLISH).toString()
        val countryCodeAlreadyExistErrMsg =
            messageSource?.getMessage("countryCodeAlreadyExistErrMsg", null, Locale.ENGLISH).toString()
        val countryCodeJpnCharacterErrMsg =
            messageSource?.getMessage("countryCodeJpnCharacterErrMsg", null, Locale.ENGLISH).toString()

        messageList.add(countryNameAlreadyExistErrMsg)
        messageList.add(countryCodeEmptyErrMsg)
        messageList.add(countryNameEnterEmptyErrMsg)
        messageList.add(countryCodeAlreadyExistErrMsg)
        messageList.add(countryCodeJpnCharacterErrMsg)

        return Response("message", messageList)
    }


    @GetMapping(value = ["/getGroupErrorMessage"])
    fun getGroupErrorMessage(): Response {
        val messageList: MutableList<String> = ArrayList()
        val groupCodeEmptyErrMsg = messageSource?.getMessage("groupCodeEmptyErrMsg", null, Locale.ENGLISH).toString()
        val groupNameEmptyErrMsg = messageSource?.getMessage("groupNameEmptyErrMsg", null, Locale.ENGLISH).toString()
        val repCompanyEmptyErrMsg = messageSource?.getMessage("repCompanyEmptyErrMsg", null, Locale.ENGLISH).toString()
        val groupCodeJpnCharacterErrMsg = messageSource?.getMessage("groupCodeJpnCharacterErrMsg", null, Locale.ENGLISH).toString()
        val groupCodeNumberErrMsg = messageSource?.getMessage("groupCodeNumberErrMsg", null, Locale.ENGLISH).toString()
        val groupCodeAlreadyExistErrMsg = messageSource?.getMessage("groupCodeAlreadyExistErrMsg", null, Locale.ENGLISH).toString()

        messageList.add(groupCodeEmptyErrMsg)
        messageList.add(groupNameEmptyErrMsg)
        messageList.add(repCompanyEmptyErrMsg)
        messageList.add(groupCodeJpnCharacterErrMsg)
        messageList.add(groupCodeNumberErrMsg)
        messageList.add(groupCodeAlreadyExistErrMsg)

        return Response("message", messageList)
    }

    @GetMapping(value = ["/getCompanyCode/companyCode"])
    fun getCompanyInfo(@RequestParam(value = "companyCode") companyCode: String?): Response {

        val companyList = companyRepo?.selectCompanyWithCode(companyCode)
        return Response("message", "message")

    }
    @GetMapping(value = ["/getProductErrorMessage"])
    fun getProductErrorMessage(): Response {
        val messageList: MutableList<String> = ArrayList()
        val productNameAlreadyExistErrMsg = messageSource?.getMessage("productNameAlreadyExistErrMsg", null, Locale.ENGLISH).toString()
        val productNameEmptyErrMsg = messageSource?.getMessage("productNameEmptyErrMsg", null, Locale.ENGLISH).toString()
        val productCodeEmptyErrMsg = messageSource?.getMessage("productCodeEmptyErrMsg", null, Locale.ENGLISH).toString()
        val productCodeJpnCharacterErrMsg = messageSource?.getMessage("productCodeJpnCharacterErrMsg", null, Locale.ENGLISH).toString()
        val productCodeNumberErrMsg = messageSource?.getMessage("productCodeNumberErrMsg", null, Locale.ENGLISH).toString()
        val productCodeAlreadyExistErrMsg = messageSource?.getMessage("productCodeAlreadyExistErrMsg", null, Locale.ENGLISH).toString()

        messageList.add(productNameAlreadyExistErrMsg)
        messageList.add(productNameEmptyErrMsg)
        messageList.add(productCodeEmptyErrMsg)
        messageList.add(productCodeJpnCharacterErrMsg)
        messageList.add(productCodeNumberErrMsg)
        messageList.add(productCodeAlreadyExistErrMsg)

        return Response("message", messageList)
    }


//    @GetMapping(value = ["/getUnselectedProList/proCode"])
//    fun getNotSelectedProductName(@RequestParam(value = "proCode") proCode: String?): Response {
//
//        val proList = proRepo?.getNotSelectedProductName(proCode)
//        return Response("message", proList)
////        return "redirect:/patternEdit"
//    }
}