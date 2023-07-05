package com.example.exportControl.controller

import com.example.exportControl.model.*
import com.example.exportControl.model.requestModel.ScheduleColumnNames
import com.example.exportControl.repository.*
import com.example.exportControl.util.ExportTImportENG
import com.example.exportControl.util.ExportTImportJPN
import com.example.exportControl.util.TransportModeENG
import com.example.exportControl.util.TransportModeJPN
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.support.RequestContextUtils
import org.springframework.web.servlet.view.RedirectView
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.security.Principal
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*
import javax.servlet.http.HttpServletRequest
import kotlin.collections.ArrayList


@Controller
class ScheduleController : MenuController() {

    @Autowired
    private val dailyChatSaveRepo: DailyChatSaveRepository? = null

    @Autowired
    private val scheduleControl: ScheduleControlRepository? = null

    @Autowired
    private val exportRegistrationRepo: ExportRegistrationRepository? = null

    @Autowired
    private val exportDocumentRepo: ExportDocumentSaveRepository? = null

    @Autowired
    private val companyControlRepo: CompanyControlRepository? = null

    @Autowired
    private val LatestChatReadRepo: LatestChatReadRepository? = null

    @Autowired
    private val DailyChatSaveRepo: DailyChatSaveRepository? = null

    @Autowired
    private val portRepo: PortMasterRepository? = null

    @Autowired
    private val patternRepo: PatternRegistrationRepository? = null

    @Autowired
    final var messageSource: MessageSource? = null

    final var customerName = ""
    final var patternName = ""
    final var billingMonth = ""
    final var bookingNo = ""
    final var poNo = ""
    final var shippingAgency = ""
    final var exportInport = ""
    final var incoterms = ""
    final var shipName = ""
    final var voyNo = ""
    final var invoiceCode = ""
    final var packing = ""
    final var transportMode = ""
    final var fourtyf = ""
    final var twentyf = ""
    final var invoicePrice = ""
    final var blawbNo = ""
    final var departurePort = ""
    final var arrivalPort = ""
    final var cut = ""
    final var etd = ""
    final var eta = ""
    final var anAdvanceFee = ""
    final var supplierName = ""
    final var representativeItem = ""
    final var freightKg = ""
    final var freightM3 = ""
    final var ctsNumber = ""
    final var size = ""
    final var rtShip = ""
    final var vwAir = ""
    final var hlBoxesNumber = ""
    final var moneyOrder = ""
    final var inportTaxAdvanceAmount = ""
    final var remarks = ""
    final var declarationDate = ""
    final var inspectionDate = ""
    final var deliveryDateAndTime = ""
    final var possiblePickUpDate = ""
    final var pickUpDate = ""
    final var containerNo = ""
    final var cargoInsurance = ""
    final var logisticsCost = ""
    final var freeTimeDate = ""


    public var  csvAllProducts:List<ExportRegistration>? = null
    public var csvColumnarrayList = java.util.ArrayList<String>()
    public var csvDateList = java.util.ArrayList<String>()

    public var showDeclarationDateFlag:Int? = null;
    public var showInspectionDateFlag:Int? = null;
    public var showDeliveryDateFlag:Int? = null;
    public var showpossiblePickUpDateFlag:Int? = null;
    public var showPickUpDateFlag:Int? = null;
    public var showfreeTimeDateFlag:Int? = null;

    public var showBillingMonthFlag:Int? = null;
    public var showEtaFlag:Int? = null;
    public var showEtdFlag:Int? = null;
    public var showCutFlag:Int? = null;

    public var companyName:String? = null;

    var currentDate = LocalDate.now()

    @RequestMapping("/searchScheduleWithInputField")
    fun searchProductMaster(
        model: Model,
        principal: Principal,
        redirectAttributes:RedirectAttributes,
        @Param("startDate") startDate: String?,
        @Param("endDate") endDate: String?,
        @Param("date_num") date_num: String?,
        @Param("declarationStartDate") declarationStartDate: String?,
        @Param("declarationEndDate") declarationEndDate: String?,
        @Param("inspectionStartDate") inspectionStartDate: String?,
        @Param("inspectionEndDate") inspectionEndDate: String?,
        @Param("deliveryStartDate") deliveryStartDate: String?,
        @Param("deliveryEndDate") deliveryEndDate: String?,
        @Param("possiblePickUpStartDate") possiblePickUpStartDate: String?,
        @Param("possiblePickUpEndDate") possiblePickUpEndDate: String?,
        @Param("pickUpStartDate") pickUpStartDate: String?,
        @Param("pickUpEndDate") pickUpEndDate: String?,
        @Param("freeTimeStartDate") freeTimeStartDate: String?,
        @Param("freeTimeEndDate") freeTimeEndDate: String?,
        @Param("arrivalPort") arrivalPort: String?,
        @Param("departurePort") departurePort: String?


    ): RedirectView {
        val userName = principal.name
        val user = userMasterRepo?.selectUserByUserCode(userName)
        val companyCode = user?.companyCode

        //search date Filter
        if(startDate != "" && date_num != "NaN" ){
            val dateList = ArrayList<String>()
            val startDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"))
            dateList.add(DateTimeFormatter.ofPattern("MM/dd/yyyy").withZone(ZoneOffset.UTC).format(startDate))

            val num = Integer.parseInt(date_num)
            for (i in 0..num-1) {
                val x = DateTimeFormatter.ofPattern("MM/dd/yyyy").withZone(ZoneOffset.UTC).format(
                    startDate.plusDays((i+1).toLong())
                )
                dateList.add(x)
            }
            redirectAttributes.addFlashAttribute("dateList",dateList)
            this.csvColumnarrayList = dateList
        }

        val allProducts = exportRegistrationRepo?.searchWithInputFields(companyCode,declarationStartDate,declarationEndDate,inspectionStartDate,inspectionEndDate,deliveryStartDate,deliveryEndDate,possiblePickUpStartDate,possiblePickUpEndDate,pickUpStartDate,pickUpEndDate,freeTimeStartDate,freeTimeEndDate,arrivalPort,departurePort)
        redirectAttributes.addFlashAttribute("allProducts", allProducts)
        redirectAttributes.addFlashAttribute("startDate", startDate)
        redirectAttributes.addFlashAttribute("endDate", endDate)
        redirectAttributes.addFlashAttribute("declarationStartDate", declarationStartDate)
        redirectAttributes.addFlashAttribute("declarationEndDate", declarationEndDate)
        redirectAttributes.addFlashAttribute("inspectionStartDate", inspectionStartDate)
        redirectAttributes.addFlashAttribute("inspectionEndDate", inspectionEndDate)
        redirectAttributes.addFlashAttribute("deliveryStartDate", deliveryStartDate)
        redirectAttributes.addFlashAttribute("deliveryEndDate", deliveryEndDate)
        redirectAttributes.addFlashAttribute("possiblePickUpStartDate", possiblePickUpStartDate)
        redirectAttributes.addFlashAttribute("possiblePickUpEndDate", possiblePickUpEndDate)
        redirectAttributes.addFlashAttribute("pickUpStartDate", pickUpStartDate)
        redirectAttributes.addFlashAttribute("pickUpEndDate", pickUpEndDate)
        redirectAttributes.addFlashAttribute("freeTimeStartDate", freeTimeStartDate)
        redirectAttributes.addFlashAttribute("freeTimeEndDate", freeTimeEndDate)
        redirectAttributes.addFlashAttribute("arrivalPortCode", arrivalPort)
        redirectAttributes.addFlashAttribute("departurePortCode", departurePort)


        return RedirectView("/scheduleOne", true)
    }

    @RequestMapping("/scheduleOneSearch/{id}-{searchType}")
    fun scheduleOneSearch(model: Model, redirectAttributes: RedirectAttributes, principal: Principal, @PathVariable id:String, @PathVariable searchType:String): RedirectView {
        var allProducts: List<ExportRegistration>? = null
        var searchColumn:String? = null
        val userName = principal.name
        val user = userMasterRepo?.selectUserByUserCode(userName)
        val companyCode = user?.companyCode
        val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
//        val billingMonthInDate =  formatter.parse(billingMonth)
//        val declarationDateInDate = formatter.parse(declarationDate)
//        val inspectionDateInDate = formatter.parse(inspectionDate)
//        val deliveryDateAndTimeInDate = formatter.parse(deliveryDateAndTime)
//        val possiblePickUpDateInDate = formatter.parse(possiblePickUpDate)
//        val pickUpDateInDate = formatter.parse(pickUpDate)
        if (searchType == "CUSTOMER") {
            allProducts = exportRegistrationRepo?.searchWithCustomer(id)
            searchColumn = this.customerName
        }else if(searchType == "PATTERN"){
            allProducts = exportRegistrationRepo?.searchWithPattern(id,companyCode)
            searchColumn = this.patternName
        }else if(searchType == "BILLING") {
            allProducts = exportRegistrationRepo?.searchWithBillingMonth(id,companyCode)
            searchColumn = this.billingMonth
        }else if (searchType == "BOOKING"){
            allProducts = exportRegistrationRepo?.searchWithBooking(id,companyCode)
            searchColumn = this.bookingNo
        }else if (searchType == "PONO"){
            allProducts = exportRegistrationRepo?.searchWithPo(id,companyCode)
            searchColumn = this.poNo
        }else if (searchType == "SHIPPING"){
            allProducts = exportRegistrationRepo?.searchWithShipping(id,companyCode)
            searchColumn = this.shippingAgency
        }else if (searchType == "EXPORTINPORT"){
            allProducts = exportRegistrationRepo?.searchWithExportInport(id,companyCode)
            searchColumn = this.exportInport
        }else if (searchType == "INCOTERMS"){
            allProducts = exportRegistrationRepo?.searchWithIncoterms(id,companyCode)
            searchColumn = this.incoterms
        }else if (searchType == "SHIPNAME"){
            allProducts = exportRegistrationRepo?.searchWithShipName(id,companyCode)
            searchColumn = this.incoterms
        }else if (searchType == "VOY"){
            allProducts = exportRegistrationRepo?.searchWithVOY(id,companyCode)
            searchColumn = this.voyNo
        }else if (searchType == "INVOICECODE") {
            allProducts = exportRegistrationRepo?.searchWithCode(id)
            searchColumn = "INV No"
        }else if(searchType == "PACKING"){
            allProducts = exportRegistrationRepo?.searchWithPacking(id,companyCode)
            searchColumn = this.packing
        }else if(searchType == "TRANSPORTMODE") {
            allProducts = exportRegistrationRepo?.searchWithTransport(id,companyCode)
            searchColumn = this.transportMode
        }else if (searchType == "FOURTY"){
            allProducts = exportRegistrationRepo?.searchWithfourtyf(id,companyCode)
            searchColumn = this.fourtyf
        }else if (searchType == "TWENTY"){
            allProducts = exportRegistrationRepo?.searchWithtwentyf(id,companyCode)
            searchColumn = this.twentyf
        }else if (searchType == "INVOICEPRICE"){
            allProducts = exportRegistrationRepo?.searchWithPrice(id,companyCode)
            searchColumn = this.invoicePrice
        }else if (searchType == "BLAWB"){
            allProducts = exportRegistrationRepo?.searchWithblAwbNo(id,companyCode)
            searchColumn = this.blawbNo
        }else if (searchType == "DEPARTURE"){
            allProducts = exportRegistrationRepo?.searchWithDeparturePort(id,companyCode)
            searchColumn = this.departurePort
        }else if (searchType == "ARRIVAL"){
            allProducts = exportRegistrationRepo?.searchWithArrivalPort(id,companyCode)
            searchColumn = this.arrivalPort
        }else if (searchType == "CUT"){
            allProducts = exportRegistrationRepo?.searchWithcut(id,companyCode)
            searchColumn = this.cut
        }else if (searchType == "ETD") {
            allProducts = exportRegistrationRepo?.searchWithetd(id,companyCode)
            searchColumn = this.etd
        }else if(searchType == "ETA"){
            allProducts = exportRegistrationRepo?.searchWitheta(id,companyCode)
            searchColumn = this.eta
        }else if(searchType == "ADVANCEFEE") {
            allProducts = exportRegistrationRepo?.searchWithadvAnceFee(id,companyCode)
            searchColumn = this.anAdvanceFee
        }else if (searchType == "SUPPLIERNAME"){
            allProducts = exportRegistrationRepo?.searchWithSupplierName(id,companyCode)
            searchColumn = this.supplierName
        }else if (searchType == "REPRESENTATIVE"){
            allProducts = exportRegistrationRepo?.searchWithRepresentativeItem(id,companyCode)
            searchColumn = this.representativeItem
        }else if (searchType == "KG"){
            allProducts = exportRegistrationRepo?.searchWithKg(id,companyCode)
            searchColumn = this.freightKg
        }else if (searchType == "M3"){
            allProducts = exportRegistrationRepo?.searchWithM3(id,companyCode)
            searchColumn = this.freightM3
        }else if (searchType == "CTS"){
            allProducts = exportRegistrationRepo?.searchWithCts(id,companyCode)
            searchColumn = this.ctsNumber
        }else if (searchType == "SIZE"){
            allProducts = exportRegistrationRepo?.searchWithSize(id,companyCode)
            searchColumn = this.size
        }else if (searchType == "RTSHIP"){
            allProducts = exportRegistrationRepo?.searchWithRT(id,companyCode)
            searchColumn = this.rtShip
        } else if (searchType == "VW"){
            allProducts = exportRegistrationRepo?.searchWithVW(id,companyCode)
            searchColumn = this.vwAir
        }else if (searchType == "HLBOX"){
            allProducts = exportRegistrationRepo?.searchWithHlBox(id,companyCode)
            searchColumn = this.hlBoxesNumber
        }else if (searchType == "MONEYORDER"){
            allProducts = exportRegistrationRepo?.searchWithMoneyOrder(id,companyCode)
            searchColumn = this.moneyOrder
        }else if (searchType == "INPORTTAX") {
            allProducts = exportRegistrationRepo?.searchWithInportTax(id,companyCode)
            searchColumn = this.inportTaxAdvanceAmount
        }else if(searchType == "REMARKS"){
            allProducts = exportRegistrationRepo?.searchWithremarks(id,companyCode)
            searchColumn = this.remarks
        }else if(searchType == "DECLARATION") {
            allProducts = exportRegistrationRepo?.searchWithDeclarationDate(id,companyCode)
            searchColumn = this.declarationDate
        }else if (searchType == "INSPECTION"){
            allProducts = exportRegistrationRepo?.searchWithInspectionDate(id,companyCode)
            searchColumn = this.inspectionDate
        }else if (searchType == "DELIVERY"){
            allProducts = exportRegistrationRepo?.searchWithDeliveryDate(id,companyCode)
            searchColumn = this.deliveryDateAndTime
        }else if (searchType == "POSSIBLE"){
            allProducts = exportRegistrationRepo?.searchWithPossibleDate(id,companyCode)
            searchColumn = this.possiblePickUpDate
        }else if (searchType == "PICKUP"){
            allProducts = exportRegistrationRepo?.searchWithPickupDate(id,companyCode)
            searchColumn = this.pickUpDate
        }else if (searchType == "CONTAINER"){
            allProducts = exportRegistrationRepo?.searchWithContainerNo(id,companyCode)
            searchColumn = this.containerNo
        }else if (searchType == "INSURANCE"){
            allProducts = exportRegistrationRepo?.searchWithCargoInsurance(id,companyCode)
            searchColumn = this.cargoInsurance
        }else if (searchType == "LOGISTIC"){
            allProducts = exportRegistrationRepo?.searchWithlogisticsCost(id,companyCode)
            searchColumn = this.logisticsCost
        }else if (searchType == "FREETIME"){
            allProducts = exportRegistrationRepo?.searchWithFreeTime(id,companyCode)
            searchColumn = this.freeTimeDate
        }
        redirectAttributes.addFlashAttribute("searchColumn", searchColumn)
        redirectAttributes.addFlashAttribute("searchType", searchType)
        redirectAttributes.addFlashAttribute("allProducts", allProducts)
        this.csvAllProducts = allProducts
        return RedirectView("/scheduleOne", true)
    }


    @RequestMapping("/searchMessage")
    fun scheduleOneSearch(model: Model,principal:Principal,redirectAttributes:RedirectAttributes, @Param("searchMessageKey")searchMessageKey: String?,@Param("searchInvoiceCode")searchInvoiceCode: String?): RedirectView {
        val userCode = principal.name
        val user = userMasterRepo?.selectUserByUserCode(userCode)
        val searchMessageKey = searchMessageKey
        var allMessage: MutableList<ExportChatDailySaveShow> = mutableListOf()
        val allMessageTemp = dailyChatSaveRepo?.searchMessages(searchMessageKey,principal.name,searchInvoiceCode,user?.companyCode)

        allMessageTemp?.forEachIndexed { index, e ->
            allMessage.add(ExportChatDailySaveShow(e.id,e.invoiceCode,e.sendCompanyCode,e.groupCode,e.userCode,e.chatMessage,String(e.chatImage!!),e.reactCount,e.chatDate,e.createdBy,e.createdDt,e.updatedBy,e.updatedDt,e.reactFlag,e.userName))

        }
        redirectAttributes.addFlashAttribute("allMessage",allMessage)
        return RedirectView("/scheduletwo/${searchInvoiceCode}", true)

    }

    @RequestMapping("/scheduletwo/{id}")
    fun scheduleTwo(model: Model,principal: Principal,@PathVariable id:String,request: HttpServletRequest): String {
        val userCode = principal.name
        val user = userMasterRepo?.selectUserByUserCode(userCode)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(user?.companyCode)
        var allMessage: MutableList<ExportChatDailySaveShow> = mutableListOf()
        val inputFlashMap = RequestContextUtils.getInputFlashMap(request)

        val exportDocumentList = exportDocumentRepo?.selectDocumentByInvoiceCode(id)
        val exportDocumentNameList = exportDocumentRepo?.selectDocumentNameByInvoiceCode(id)

        if (inputFlashMap != null) {
            allMessage = (inputFlashMap["allMessage"] as  MutableList<ExportChatDailySaveShow>?)!!
            // do the job
        }else{
            var allMessageTemp =  dailyChatSaveRepo?.selectAllMessage(userCode,id,user?.companyCode)
            allMessageTemp?.forEachIndexed  { index, e ->
                allMessage.add(ExportChatDailySaveShow(e.id,e.invoiceCode,e.sendCompanyCode,e.groupCode,e.userCode,e.chatMessage,String(e.chatImage!!),e.reactCount,e.chatDate,e.createdBy,e.createdDt,e.updatedBy,e.updatedDt,e.reactFlag,e.userName))
            }
        }

        var currentlatestMessageId = DailyChatSaveRepo?.selectLatestMassageByUserCode(id,user?.companyCode)
        var userlatestMessageId = LatestChatReadRepo?.selectMessageIdByUserCodeAndInvoice(userCode,id,user?.companyCode)
        model.addAttribute("userlatestMessageId",userlatestMessageId)
        model.addAttribute("currentlatestMessageId",currentlatestMessageId)

        model.addAttribute("allMessage",allMessage)
        model.addAttribute("companyCode",user?.companyCode)
        model.addAttribute("loginCompany",loginCompany)
        model.addAttribute("languageOption",webConfig?.messageSourcePath)
        model.addAttribute("userCode",userCode)
        model.addAttribute("invoiceCode",id)
        model.addAttribute("exportDocumentList",exportDocumentList)
        model.addAttribute("exportDocumentNameList",exportDocumentNameList)

        // for export registration
        val exportItemObj = exportRegistrationRepo?.searchWithInvoiceCode(id,user?.companyCode)
        model.addAttribute("exportMode",exportItemObj?.exportInport)
        model.addAttribute("tranMode",exportItemObj?.transportMode)
        model.addAttribute("cusObj",exportItemObj?.customerName)
        model.addAttribute("patName",exportItemObj?.patternName)


        if(webConfig?.messageSourcePath == 0){
            val exportArr = enumValues<ExportTImportJPN>()
            val transportArr = enumValues<TransportModeJPN>()
            model.addAttribute("ExportType",exportArr.toList())
            model.addAttribute("TransportType",transportArr.toList())
        }else{
            val exportArr = enumValues<ExportTImportENG>()
            val transportArr = enumValues<TransportModeENG>()
            model.addAttribute("ExportType",exportArr.toList())
            model.addAttribute("TransportType",transportArr.toList())
        }

        model.addAttribute("portList", portRepo?.selectAllPort())
        model.addAttribute("exportRegistration",exportItemObj)
        model.addAttribute("companyList", listOf(loginCompany))
        model.addAttribute("patternList",patternRepo?.getAllPatternNameList())

        return "scheduleTwo"
    }

    @RequestMapping(value = ["/scheduleOne"], method = [RequestMethod.GET])
    fun userInfoAccountInfo(model: Model, principal: Principal,request: HttpServletRequest): String {

        this.customerName = messageSource?.getMessage("customerName", null, Locale.ENGLISH).toString()
        this.patternName = messageSource?.getMessage("patternName", null, Locale.ENGLISH).toString()
        this.billingMonth = 	messageSource?.getMessage("billingMonth", null, Locale.ENGLISH).toString()
        this.bookingNo = 	messageSource?.getMessage("bookingNo", null, Locale.ENGLISH).toString()
        this.poNo = 	messageSource?.getMessage("poNO", null, Locale.ENGLISH).toString()
        this.shippingAgency = 	messageSource?.getMessage("shippingAgency", null, Locale.ENGLISH).toString()
        this.exportInport = 	messageSource?.getMessage("exportInport", null, Locale.ENGLISH).toString()
        this.incoterms = 	messageSource?.getMessage("incoterms", null, Locale.ENGLISH).toString()
        this.shipName = 	messageSource?.getMessage("shipName", null, Locale.ENGLISH).toString()
        this.voyNo = 	messageSource?.getMessage("voyNo", null, Locale.ENGLISH).toString()
        this.invoiceCode = 	messageSource?.getMessage("invoiceCode", null, Locale.ENGLISH).toString()
        this.packing = 	messageSource?.getMessage("packing", null, Locale.ENGLISH).toString()
        this.transportMode = 	messageSource?.getMessage("transportMode", null, Locale.ENGLISH).toString()
        this.fourtyf = 	messageSource?.getMessage("fourtyf", null, Locale.ENGLISH).toString()
        this.twentyf = 	messageSource?.getMessage("twentyf", null, Locale.ENGLISH).toString()
        this.invoicePrice = 	messageSource?.getMessage("invoicePrice", null, Locale.ENGLISH).toString()
        this.blawbNo = 	messageSource?.getMessage("blawbNo", null, Locale.ENGLISH).toString()
        this.departurePort = 	messageSource?.getMessage("departurePort", null, Locale.ENGLISH).toString()
        this.arrivalPort = 	messageSource?.getMessage("arrivalPort", null, Locale.ENGLISH).toString()
        this.cut = 	messageSource?.getMessage("cut", null, Locale.ENGLISH).toString()
        this.etd = 	messageSource?.getMessage("etd", null, Locale.ENGLISH).toString()
        this.eta = 	messageSource?.getMessage("eta", null, Locale.ENGLISH).toString()
        this.anAdvanceFee = 	messageSource?.getMessage("anAdvanceFee", null, Locale.ENGLISH).toString()
        this.supplierName = 	messageSource?.getMessage("supplierName", null, Locale.ENGLISH).toString()
        this.representativeItem = 	messageSource?.getMessage("representativeItem", null, Locale.ENGLISH).toString()
        this.freightKg = 	messageSource?.getMessage("freightKg", null, Locale.ENGLISH).toString()
        this.freightM3 = 	messageSource?.getMessage("freightM3", null, Locale.ENGLISH).toString()
        this.ctsNumber = 	messageSource?.getMessage("ctsNumber", null, Locale.ENGLISH).toString()
        this.size = 	messageSource?.getMessage("size", null, Locale.ENGLISH).toString()
        this.rtShip = 	messageSource?.getMessage("rtShip", null, Locale.ENGLISH).toString()
        this.vwAir = 	messageSource?.getMessage("vwAir", null, Locale.ENGLISH).toString()
        this.hlBoxesNumber = 	messageSource?.getMessage("hlBoxesNumber", null, Locale.ENGLISH).toString()
        this.moneyOrder = 	messageSource?.getMessage("moneyOrder", null, Locale.ENGLISH).toString()
        this.inportTaxAdvanceAmount = 	messageSource?.getMessage("inportTaxAdvanceAmount", null, Locale.ENGLISH).toString()
        this.remarks = 	messageSource?.getMessage("remarks", null, Locale.ENGLISH).toString()
        this.declarationDate = 	messageSource?.getMessage("declarationDate", null, Locale.ENGLISH).toString()
        this.inspectionDate = 	messageSource?.getMessage("inspectionDate", null, Locale.ENGLISH).toString()
        this.deliveryDateAndTime = 	messageSource?.getMessage("deliveryDateAndTime", null, Locale.ENGLISH).toString()
        this.possiblePickUpDate = 	messageSource?.getMessage("possiblePickUpDate", null, Locale.ENGLISH).toString()
        this.pickUpDate = 	messageSource?.getMessage("pickUpDate", null, Locale.ENGLISH).toString()
        this.containerNo = 	messageSource?.getMessage("containerNo", null, Locale.ENGLISH).toString()
        this.cargoInsurance = messageSource?.getMessage("cargoInsurance", null, Locale.ENGLISH).toString()
        this.logisticsCost = messageSource?.getMessage("logisticsCost", null, Locale.ENGLISH).toString()
        this.freeTimeDate = messageSource?.getMessage("freeTime", null, Locale.ENGLISH).toString()

            var columnNames:ScheduleColumnNames = ScheduleColumnNames(customerName,patternName,billingMonth,bookingNo,poNo,shippingAgency,exportInport,incoterms,shipName,voyNo,
            invoiceCode,packing,transportMode,fourtyf,twentyf,invoicePrice,blawbNo,departurePort,arrivalPort,cut,etd,eta,anAdvanceFee,supplierName,
            representativeItem,freightKg,freightM3,ctsNumber,size,rtShip,vwAir,hlBoxesNumber,moneyOrder,inportTaxAdvanceAmount,remarks,declarationDate,
            inspectionDate,deliveryDateAndTime,possiblePickUpDate,pickUpDate,containerNo,cargoInsurance,logisticsCost,freeTimeDate
        )

        val userName = principal.name
        val user = userMasterRepo?.selectUserByUserCode(userName)
        val companyCode = user?.companyCode
        this.companyName = companyMasterRepo?.selectCompanyNameByCode(companyCode,webConfig?.messageSourcePath)
        val loginCompany: Company? = companyMasterRepo?.selectCompanyByCompanyCode(companyCode)
//        val companyControl = companyControlRepo?.selectAllByCompanyCode(user?.companyCode)
        val scheduleControl = scheduleControl?.selectAllByCompanyCode(userName)
        model.addAttribute("loginCompany",loginCompany)
        model.addAttribute("languageOption",webConfig?.messageSourcePath)
        var dateList = java.util.ArrayList<String>()//Creating an empty arraylist
        val columnarrayList = java.util.ArrayList<String>()
        var allProducts : List<ExportRegistration>? = null
        var allproductsAddFlg = false
        var dateListAddFlg  = false


        val inputFlashMap = RequestContextUtils.getInputFlashMap(request)
        if (inputFlashMap != null) {
            if(inputFlashMap["allProducts"] != null){
                allProducts = inputFlashMap["allProducts"] as  List<ExportRegistration>?
                allproductsAddFlg = true
            }
            if(inputFlashMap["dateList"] != null){
                dateList = inputFlashMap["dateList"] as java.util.ArrayList<String>
                dateListAddFlg = true
            }
            // do the job
        }

        if(!allproductsAddFlg){
            allProducts = exportRegistrationRepo?.getAllProducts(companyCode)
        }

        if(!dateListAddFlg){
            val date = LocalDate.now()
            val firstDayOfMonth = LocalDate.of(date.year, date.month, 1)
            dateList.add(DateTimeFormatter.ofPattern("MM/dd/yyyy").format(firstDayOfMonth))
            for (i in 0 until firstDayOfMonth.lengthOfMonth() - 1) {
                val date = firstDayOfMonth.plusDays((i + 1).toLong())
                dateList.add(DateTimeFormatter.ofPattern("MM/dd/yyyy").format(date))
            }
            this.currentDate = LocalDate.now()
        }
        //editable column

        if(scheduleControl?.customerName == 1){ columnarrayList.add(customerName) }
        if(scheduleControl?.patternName == 1){ columnarrayList.add(patternName) }
        if(scheduleControl?.billingMonth == 1){ columnarrayList.add(billingMonth) }
        if(scheduleControl?.bookingNo == 1){ columnarrayList.add(bookingNo) }
        if(scheduleControl?.poNO == 1){ columnarrayList.add(poNo) }
        if(scheduleControl?.shippingAgency == 1){ columnarrayList.add(shippingAgency) }
        if(scheduleControl?.exportInport == 1){ columnarrayList.add(exportInport) }
        if(scheduleControl?.incoterms == 1){ columnarrayList.add(incoterms) }
        if(scheduleControl?.shipName == 1){ columnarrayList.add(shipName) }
        if(scheduleControl?.voyNo == 1){ columnarrayList.add(voyNo) }
//        if(scheduleControl?.invoiceCode == 1){ columnarrayList.add(invoiceCode) }
        if(scheduleControl?.packing == 1){ columnarrayList.add(packing) }
        if(scheduleControl?.transportMode == 1){ columnarrayList.add(transportMode) }
        if(scheduleControl?.fourtyf == 1){ columnarrayList.add(fourtyf) }
        if(scheduleControl?.twentyf == 1){ columnarrayList.add(twentyf) }
        if(scheduleControl?.invoicePrice == 1){ columnarrayList.add(invoicePrice) }
        if(scheduleControl?.blawbNo == 1){ columnarrayList.add(blawbNo) }
        if(scheduleControl?.departurePort == 1){ columnarrayList.add(departurePort) }
        if(scheduleControl?.arrivalPort == 1){ columnarrayList.add(arrivalPort) }
        if(scheduleControl?.cut == 1){ columnarrayList.add(cut) }
        if(scheduleControl?.etd == 1){ columnarrayList.add(etd) }
        if(scheduleControl?.eta == 1){ columnarrayList.add(eta) }
        if(scheduleControl?.anAdvanceFee == 1){ columnarrayList.add(anAdvanceFee) }
        if(scheduleControl?.supplierName == 1){ columnarrayList.add(supplierName) }
        if(scheduleControl?.representativeItem == 1){ columnarrayList.add(representativeItem) }
        if(scheduleControl?.freightKg == 1){ columnarrayList.add(freightKg) }
        if(scheduleControl?.freightM3 == 1){ columnarrayList.add(freightM3) }
        if(scheduleControl?.ctsNumber == 1){ columnarrayList.add(ctsNumber) }
        if(scheduleControl?.size == 1){ columnarrayList.add(size) }
        if(scheduleControl?.rtShip == 1){ columnarrayList.add(rtShip) }
        if(scheduleControl?.vwAir == 1){ columnarrayList.add(vwAir) }
        if(scheduleControl?.hlBoxesNumber == 1){ columnarrayList.add(hlBoxesNumber) }
        if(scheduleControl?.moneyOrder == 1){ columnarrayList.add(moneyOrder) }
        if(scheduleControl?.inportTaxAdvanceAmount == 1){ columnarrayList.add(inportTaxAdvanceAmount) }
        if(scheduleControl?.remarks == 1){ columnarrayList.add(remarks) }
        if(scheduleControl?.declarationDate == 1){ columnarrayList.add(declarationDate) }
        if(scheduleControl?.inspectionDate == 1){ columnarrayList.add(inspectionDate) }
        if(scheduleControl?.deliveryDateAndTime == 1){ columnarrayList.add(deliveryDateAndTime) }
        if(scheduleControl?.possiblePickUpDate == 1){ columnarrayList.add(possiblePickUpDate) }
        if(scheduleControl?.pickUpDate == 1){ columnarrayList.add(pickUpDate) }
        if(scheduleControl?.containerNo == 1){ columnarrayList.add(containerNo) }
        if(scheduleControl?.cargoInsurance == 1){ columnarrayList.add(cargoInsurance) }
        if(scheduleControl?.logisticsCost == 1){ columnarrayList.add(logisticsCost) }
        if(scheduleControl?.freeTime == 1){ columnarrayList.add(freeTimeDate) }

        var showDeclarationDateFlag:Int? = null
        var showInspectionDateFlag:Int? = null
        var showDeliveryDateFlag:Int? = null
        var showpossiblePickUpDateFlag:Int? = null
        var showPickUpDateFlag:Int? = null
        var showFreeTimeFlag:Int? = null

        var showBillingMonthFlag:Int?=null
        var showEtaFlag:Int? = null;
        var showEtdFlag:Int? = null;
        var showCutFlag:Int? = null

        if (allProducts != null) {
            for (product in allProducts) {

                if(scheduleControl?.customerName == 0){ product.customerName = null }else{product.customerName = companyMasterRepo?.selectCompanyNameByCode(product.customerName,webConfig?.messageSourcePath?.toInt())}
                if(scheduleControl?.patternName == 0){ product.patternName = null }
                if(scheduleControl?.billingMonth == 0){  showBillingMonthFlag = 0 }else{showBillingMonthFlag = 1}
                if(scheduleControl?.bookingNo == 0){ product.bookingNo = null }
                if(scheduleControl?.poNO == 0){ product.poNO = null }
                if(scheduleControl?.shippingAgency == 0){ product.shippingAgency = null }
                if(scheduleControl?.exportInport == 0){ product.exportInport = null }else{ if(webConfig?.messageSourcePath == 1){if(product.exportInport == "Export") { product.exportInport = ExportTImportENG.Export.type} else {product.exportInport = ExportTImportENG.Import.type}}else{if(product.exportInport == "Export") { product.exportInport =ExportTImportJPN.Export.type } else {product.exportInport = ExportTImportJPN.Import.type}} }
                if(scheduleControl?.incoterms == 0){ product.incoterms = null }
                if(scheduleControl?.shipName == 0){ product.shipName = null }
                if(scheduleControl?.voyNo == 0){ product.voyNo = null }
//                if(scheduleControl?.invoiceCode == 0){ product.invoiceCode = null }
                if(scheduleControl?.packing == 0){ product.packing = null }
                if(scheduleControl?.transportMode == 0){ product.transportMode = null }else{ if(webConfig?.messageSourcePath == 1){if(product.transportMode == "Air") { product.transportMode = TransportModeENG.Air.type } else {product.transportMode = TransportModeENG.Ship.type}}else{if(product.transportMode == "Air") { product.transportMode =TransportModeJPN.Air.type } else {product.transportMode = TransportModeJPN.Ship.type}} }
                if(scheduleControl?.fourtyf == 0){ product.fourtyf = null }
                if(scheduleControl?.twentyf == 0){ product.twentyf = null }
                if(scheduleControl?.invoicePrice == 0){ product.invoicePrice = null }
                if(scheduleControl?.blawbNo == 0){ product.blawbNo = null }
                if(scheduleControl?.departurePort == 0){ product.departurePort = null }else{product.departurePort = portRepo?.selectPortNameByCode(product.departurePort,webConfig?.messageSourcePath)}
                if(scheduleControl?.arrivalPort == 0){ product.arrivalPort = null }else{product.arrivalPort = portRepo?.selectPortNameByCode(product.arrivalPort,webConfig?.messageSourcePath)}
                if(scheduleControl?.cut == 0){ showCutFlag = 0 }else{showCutFlag = 1 }
                if(scheduleControl?.etd == 0){ showEtdFlag = 0 }else{showEtdFlag = 1 }
                if(scheduleControl?.eta == 0){ showEtaFlag = 0 }else{showEtaFlag = 1 }
                if(scheduleControl?.anAdvanceFee == 0){ product.anAdvanceFee = null }
                if(scheduleControl?.supplierName == 0){ product.supplierName = null }
                if(scheduleControl?.representativeItem == 0){ product.representativeItem = null }
                if(scheduleControl?.freightKg == 0){ product.freightKg = null }
                if(scheduleControl?.freightM3 == 0){ product.freightM3 = null }
                if(scheduleControl?.ctsNumber == 0){ product.ctsNumber = null }
                if(scheduleControl?.size == 0){ product.size = null }
                if(scheduleControl?.rtShip == 0){ product.rtShip = null }
                if(scheduleControl?.vwAir == 0){ product.vwAir = null }
                if(scheduleControl?.hlBoxesNumber == 0){ product.hlBoxesNumber = null }
                if(scheduleControl?.moneyOrder == 0){ product.moneyOrder = null }
                if(scheduleControl?.inportTaxAdvanceAmount == 0){ product.inportTaxAdvanceAmount = null }
                if(scheduleControl?.remarks == 0){ product.remarks = null }
                if(scheduleControl?.declarationDate == 0){ showDeclarationDateFlag = 0 }else{showDeclarationDateFlag = 1}
                if(scheduleControl?.inspectionDate == 0){ showInspectionDateFlag = 0 }else{showInspectionDateFlag = 1}
                if(scheduleControl?.deliveryDateAndTime == 0){ showDeliveryDateFlag = 0 }else{showDeliveryDateFlag = 1}
                if(scheduleControl?.possiblePickUpDate == 0){ showpossiblePickUpDateFlag = 0 }else{showpossiblePickUpDateFlag = 1}
                if(scheduleControl?.pickUpDate == 0){ showPickUpDateFlag = 0 }else{showPickUpDateFlag = 1}
                if(scheduleControl?.containerNo == 0){ product.containerNo = null }
                if(scheduleControl?.cargoInsurance == 0){ product.cargoInsurance = null }
                if(scheduleControl?.logisticsCost == 0){ product.logisticsCost = null }
                if(scheduleControl?.freeTime == 0){ showFreeTimeFlag = 0 }else{showFreeTimeFlag = 1}
            }
        }
        model.addAttribute("showDeclarationDateFlag",showDeclarationDateFlag)
        model.addAttribute("showInspectionDateFlag",showInspectionDateFlag)
        model.addAttribute("showDeliveryDateFlag",showDeliveryDateFlag)
        model.addAttribute("showpossiblePickUpDateFlag",showpossiblePickUpDateFlag)
        model.addAttribute("showPickUpDateFlag",showPickUpDateFlag)
        model.addAttribute("showFreeTimeFlag",showFreeTimeFlag)

        model.addAttribute("showBillingMonthFlag",showBillingMonthFlag)
        model.addAttribute("showEtaFlag",showEtaFlag)
        model.addAttribute("showEtdFlag",showEtdFlag)
        model.addAttribute("showCutFlag",showCutFlag)

        this.showDeclarationDateFlag = showDeclarationDateFlag
        this.showInspectionDateFlag = showInspectionDateFlag
        this.showDeliveryDateFlag = showDeliveryDateFlag
        this.showpossiblePickUpDateFlag = showpossiblePickUpDateFlag
        this.showPickUpDateFlag = showPickUpDateFlag
        this.showfreeTimeDateFlag = showFreeTimeFlag

        this.showBillingMonthFlag = showBillingMonthFlag
        this.showEtaFlag = showEtaFlag
        this.showEtdFlag = showEtdFlag
        this.showCutFlag = showCutFlag

        model.addAttribute("chatInvoiceList",exportRegistrationRepo?.getChatInvoice(companyCode))
        var test =exportRegistrationRepo?.patternName(companyCode)
        model.addAttribute("customerName",exportRegistrationRepo?.customerName(companyCode))
        model.addAttribute("patternName",exportRegistrationRepo?.patternName(companyCode))
        model.addAttribute("billingMonth",exportRegistrationRepo?.billingMonth(companyCode))
        model.addAttribute("bookingNo",exportRegistrationRepo?.bookingNo(companyCode))
        model.addAttribute("poNO",exportRegistrationRepo?.poNO(companyCode))
        model.addAttribute("shippingAgency",exportRegistrationRepo?.shippingAgency(companyCode))
        model.addAttribute("exportInport",exportRegistrationRepo?.exportInport(companyCode))
        if(webConfig?.messageSourcePath == 0){
            val exportArr = enumValues<ExportTImportJPN>()
            val transportArr = enumValues<TransportModeJPN>()
            model.addAttribute("exportInport",exportArr.toList())
            model.addAttribute("transportMode",transportArr.toList())
        }else{
            val exportArr = enumValues<ExportTImportENG>()
            val transportArr = enumValues<TransportModeENG>()
            model.addAttribute("exportInport",exportArr.toList())
            model.addAttribute("transportMode",transportArr.toList())
        }
        model.addAttribute("incoterms",exportRegistrationRepo?.incoterms(companyCode))
        model.addAttribute("shipName",exportRegistrationRepo?.shipName(companyCode))
        model.addAttribute("voyNo",exportRegistrationRepo?.voyNo(companyCode))

        model.addAttribute("packing",exportRegistrationRepo?.packing(companyCode))

        model.addAttribute("fourtyf",exportRegistrationRepo?.fourtyf(companyCode))
        model.addAttribute("twentyf",exportRegistrationRepo?.twentyf(companyCode))
        model.addAttribute("invoicePrice",exportRegistrationRepo?.invoicePrice(companyCode))
        model.addAttribute("blawbNo",exportRegistrationRepo?.blawbNo(companyCode))
        model.addAttribute("departurePort",exportRegistrationRepo?.departurePort(companyCode))
        model.addAttribute("arrivalPort",exportRegistrationRepo?.arrivalPort(companyCode))
        model.addAttribute("cut",exportRegistrationRepo?.cut(companyCode))
        model.addAttribute("etd",exportRegistrationRepo?.etd(companyCode))

        model.addAttribute("eta",exportRegistrationRepo?.eta(companyCode))
        model.addAttribute("anAdvanceFee",exportRegistrationRepo?.anAdvanceFee(companyCode))
        model.addAttribute("supplierName",exportRegistrationRepo?.supplierName(companyCode))
        model.addAttribute("representativeItem",exportRegistrationRepo?.representativeItem(companyCode))
        model.addAttribute("freightKg",exportRegistrationRepo?.freightKg(companyCode))
        model.addAttribute("freightM3",exportRegistrationRepo?.freightM3(companyCode))
        model.addAttribute("ctsNumber",exportRegistrationRepo?.ctsNumber(companyCode))
        model.addAttribute("size",exportRegistrationRepo?.size(companyCode))
        model.addAttribute("rtShip",exportRegistrationRepo?.rtShip(companyCode))
        model.addAttribute("vwAir",exportRegistrationRepo?.vwAir(companyCode))

        model.addAttribute("hlBoxesNumber",exportRegistrationRepo?.hlBoxesNumber(companyCode))
        model.addAttribute("moneyOrder",exportRegistrationRepo?.moneyOrder(companyCode))
        model.addAttribute("inportTaxAdvanceAmount",exportRegistrationRepo?.inportTaxAdvanceAmount(companyCode))
        model.addAttribute("remarks",exportRegistrationRepo?.remarks(companyCode))
        model.addAttribute("declarationDate",exportRegistrationRepo?.declarationDate(companyCode))
        model.addAttribute("inspectionDate",exportRegistrationRepo?.inspectionDate(companyCode))
        model.addAttribute("deliveryDateAndTime",exportRegistrationRepo?.deliveryDateAndTime(companyCode))
        model.addAttribute("possiblePickUpDate",exportRegistrationRepo?.possiblePickUpDate(companyCode))
        model.addAttribute("pickUpDate",exportRegistrationRepo?.pickUpDate(companyCode))
        model.addAttribute("containerNo",exportRegistrationRepo?.containerNo(companyCode))
        model.addAttribute("cargoInsurance",exportRegistrationRepo?.cargoInsurance(companyCode))
        model.addAttribute("logisticsCost",exportRegistrationRepo?.logisticsCost(companyCode))
        model.addAttribute("freeTime",exportRegistrationRepo?.freeTime(companyCode))

        model.addAttribute("portList", portRepo?.selectAllPort())
        model.addAttribute("messageSource", messageSource)

        model.addAttribute("allProducesInvoiceCode",exportRegistrationRepo?.getAllProducts(companyCode))
        model.addAttribute("allProducts",allProducts)
        this.csvAllProducts = allProducts
        model.addAttribute("languageOption",webConfig?.messageSourcePath)
        model.addAttribute("dateList",dateList)
        this.csvDateList = dateList
        model.addAttribute("columnarrayList",columnarrayList)
        this.csvColumnarrayList = columnarrayList
        model.addAttribute("companyCode",user?.companyCode)
        model.addAttribute("columnNames",columnNames)

        return "scheduleOne"
    }

    @RequestMapping(value = ["/scheduleMonthChange/{type}"])
    fun scheduleMonthChange(redirectAttributes:RedirectAttributes,@PathVariable type:String): RedirectView {

        val type = type
        val dateList = java.util.ArrayList<String>()

        if(type == "next"){
            this.currentDate = this.currentDate.plusMonths(1)

        }else if(type == "previous"){
            this.currentDate = this.currentDate.minusMonths(1)
        }

        val firstDayOfMonth = LocalDate.of(this.currentDate.year, this.currentDate.month, 1)
        dateList.add(DateTimeFormatter.ofPattern("MM/dd/yyyy").format(firstDayOfMonth))
        for (i in 0 until firstDayOfMonth.lengthOfMonth() - 1) {
            val date = firstDayOfMonth.plusDays((i + 1).toLong())
            dateList.add(DateTimeFormatter.ofPattern("MM/dd/yyyy").format(date))
        }
        redirectAttributes.addFlashAttribute("dateList",dateList)

        return RedirectView("/scheduleOne", true)
    }
}
