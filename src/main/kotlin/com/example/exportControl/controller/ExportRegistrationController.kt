package com.example.exportControl.controller

import com.example.exportControl.model.ExportRegistration
import com.example.exportControl.model.requestModel.ExportRequestModel
import com.example.exportControl.repository.*
import com.example.exportControl.util.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.security.Principal
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

/**
 * ExportRegistrationController
 *
 */
@Controller
class ExportRegistrationController : MenuController() {

    @Autowired
    private val productMasterRepo: ProductMasterRepository? = null

    @Autowired
    private val exportRegistrationRepo: ExportRegistrationRepository? = null

    @Autowired
    private val patternRepo: PatternRegistrationRepository? = null

    @Autowired
    private val portRepo: PortMasterRepository? = null



    /**
     * 初期表示処理
     * @param model
     * @param principal
     */
    @RequestMapping("exportRegistration")
    fun exportRegistration(model: Model, principal: Principal): String {
        val userName = principal.name
        val muser = userMasterRepo?.selectUserByUserCode(userName)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)
        val savedInvoiceCodeList = exportRegistrationRepo?.getSavedInvoiceListByCompany(muser?.companyCode)

        model.addAttribute("savedInvoiceList",savedInvoiceCodeList)
        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("languageOption", webConfig?.messageSourcePath)
        model.addAttribute("ProductList", productMasterRepo?.selectAllProduct())
        println(productMasterRepo?.selectAllProduct())
        model.addAttribute("ProductCode", productMasterRepo?.selectAllProduct())
        model.addAttribute("ProductCodeList", productMasterRepo?.selectAllProductCode())
        model.addAttribute("ProductNameList", productMasterRepo?.selectAllProductName())
        model.addAttribute("patternList", patternRepo?.getAllProducts())
        model.addAttribute("portList", portRepo?.selectAllPort())

        if(role != "2"){
            model.addAttribute("companyList", listOf(loginCompany))
        }else{
            model.addAttribute("companyList", companyMasterRepo?.selectAllCompany())
        }



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
        return "exportRegistration"
    }

    /**
     * 輸出登録処理
     * @param exportReq
     * @param model
     * @param principal
     */
    @RequestMapping("/saveExportRegistration")
    fun saveExportRegistration(
        @ModelAttribute("exportObj") exportReq: ExportRequestModel?,
        model: Model, principal: Principal
    ): String {
        val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
        val formatter2 = SimpleDateFormat("yyyy/MM", Locale.ENGLISH)
        val userName = principal.name
        val currentDateTime: LocalDateTime = LocalDateTime.now()
        val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)

        val billingMonthInDate = formatter2.parse(exportReq?.billingMonth)
        val declarationDateInDate = formatter.parse(exportReq?.declarationDate)
        val inspectionDateInDate = formatter.parse(exportReq?.inspectionDate)
        val deliveryDateAndTimeInDate = formatter.parse(exportReq?.deliveryDateAndTime)
        val possiblePickUpDateInDate = formatter.parse(exportReq?.possiblePickUpDate)
        val pickUpDateInDate = formatter.parse(exportReq?.pickUpDate)
        val etdDate = formatter.parse(exportReq?.etd)
        val etaDate = formatter.parse(exportReq?.eta)
        val freeTime = formatter.parse(exportReq?.freeTime)
        val cut = formatter.parse(exportReq?.cut)
        val newExportRegistration = ExportRegistration(
            exportReq?.invoiceCode,
            exportReq?.customerName,
            exportReq?.patternName,
            billingMonthInDate,
            exportReq?.bookingNo,
            exportReq?.poNO,
            exportReq?.shippingAgency,
            exportReq?.exportInport,
            exportReq?.incoterms,
            exportReq?.shipName,
            exportReq?.voyNo,
            exportReq?.packing,
            exportReq?.transportMode,
            exportReq?.fourtyf,
            exportReq?.twentyf,
            exportReq?.invoicePrice,
            exportReq?.blawbNo,
            exportReq?.departurePort,
            exportReq?.arrivalPort,
            cut,
            etdDate,
            etaDate,
            exportReq?.anAdvanceFee,
            exportReq?.supplierName,
            exportReq?.representativeItem,
            exportReq?.freightKg,
            exportReq?.freightM3,
            exportReq?.ctsNumber,
            exportReq?.size,
            exportReq?.rtShip,
            exportReq?.vwAir,
            exportReq?.hlBoxesNumber,
            exportReq?.moneyOrder,
            exportReq?.inportTaxAdvanceAmount,
            exportReq?.remarks,
            declarationDateInDate,
            inspectionDateInDate,
            deliveryDateAndTimeInDate,
            possiblePickUpDateInDate,
            pickUpDateInDate,
            exportReq?.containerNo,
            exportReq?.cargoInsurance,
            exportReq?.logisticsCost,
            freeTime,
            false,
            false,
            false,
            false,
            false,
            false,
            true,
            userName,
            currentTime,
            userName,
            currentTime
        )

        exportRegistrationRepo?.save(newExportRegistration)

        return "redirect:/scheduleOne"
    }

    /**
     * 輸出更新処理
     * @param exportReq
     * @param model
     * @param principal
     */
    @RequestMapping("/updateExportRegistration")
    fun updateExportRegistration(
        @ModelAttribute("exportObj") exportReq: ExportRequestModel?,
        model: Model, principal: Principal,
        redirectAttributes: RedirectAttributes
    ): String {
        var declarationDateUpdateStatus:Boolean? = false;
        var inspectionDateUpdateStatus:Boolean? = false;
        var deliveryDateUpdateStatus:Boolean? = false;
        var possiblePickUpDateUpdateStatus:Boolean? = false;
        var pickUpDateUpdateStatus:Boolean? = false;
        var freedateupdatestatus:Boolean? = false;

        var scheduleDatesUpdateStatus:Boolean? = false;
        var pastSavedData = exportRegistrationRepo?.getExportProductByInvoiceAndCustomerName(exportReq?.invoiceCode,exportReq?.customerName)



        val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
        val formatter2 = SimpleDateFormat("yyyy/MM", Locale.ENGLISH)

        val userName = principal.name
        val currentDateTime: LocalDateTime = LocalDateTime.now()
        val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)

        val billingMonthInDate = formatter2.parse(exportReq?.billingMonth)
        val declarationDateInDate = formatter.parse(exportReq?.declarationDate)
        val inspectionDateInDate = formatter.parse(exportReq?.inspectionDate)
        val deliveryDateAndTimeInDate = formatter.parse(exportReq?.deliveryDateAndTime)
        val possiblePickUpDateInDate = formatter.parse(exportReq?.possiblePickUpDate)
        val pickUpDateInDate = formatter.parse(exportReq?.pickUpDate)
        val etdDate = formatter.parse(exportReq?.etd)
        val etaDate = formatter.parse(exportReq?.eta)
        val freeTime = formatter.parse(exportReq?.freeTime)
        val cut = formatter.parse(exportReq?.cut)

        if(!pastSavedData?.declarationDateUpdateStatus!!){
            declarationDateUpdateStatus =( pastSavedData?.declarationDate?.time != declarationDateInDate.time )
        }else{
            declarationDateUpdateStatus = true;
        }

        if(!pastSavedData.inspectiondateupdatestatus!!){
            inspectionDateUpdateStatus =( pastSavedData.inspectionDate?.time != inspectionDateInDate.time )
        }else{
            inspectionDateUpdateStatus = true
        }

        if(!pastSavedData.deliverydateupdatestatus!!){
            deliveryDateUpdateStatus =( pastSavedData.deliveryDateAndTime?.time != deliveryDateAndTimeInDate.time )
        }else{
            deliveryDateUpdateStatus = true
        }

        if(!pastSavedData.possiblepickupdateupdatestatus!!){
            possiblePickUpDateUpdateStatus =( pastSavedData?.possiblePickUpDate?.time != possiblePickUpDateInDate.time )
        }else{
            possiblePickUpDateUpdateStatus = true
        }

        if(!pastSavedData.pickupdateupdatestatus!!){
            pickUpDateUpdateStatus =( pastSavedData?.pickUpDate?.time != pickUpDateInDate.time )
        }else{
            pickUpDateUpdateStatus = true
        }

        if(!pastSavedData.freedateupdatestatus!!){
            freedateupdatestatus =( pastSavedData?.freeTime?.time != freeTime.time )
        }else{
            freedateupdatestatus = true
        }

        if(declarationDateUpdateStatus == true && inspectionDateUpdateStatus == true && deliveryDateUpdateStatus == true && possiblePickUpDateUpdateStatus == true && pickUpDateUpdateStatus == true && freedateupdatestatus == true){
            declarationDateUpdateStatus = false
            inspectionDateUpdateStatus = false
            deliveryDateUpdateStatus = false
            possiblePickUpDateUpdateStatus = false
            pickUpDateUpdateStatus = false
            freedateupdatestatus = false
        }

        scheduleDatesUpdateStatus = (declarationDateUpdateStatus == false && inspectionDateUpdateStatus == false && deliveryDateUpdateStatus == false && possiblePickUpDateUpdateStatus == false && pickUpDateUpdateStatus == false && freedateupdatestatus == false)

        val exportObj = ExportRegistration(
            exportReq?.invoiceCode,
            exportReq?.customerName,
            exportReq?.patternName,
            billingMonthInDate,
            exportReq?.bookingNo,
            exportReq?.poNO,
            exportReq?.shippingAgency,
            exportReq?.exportInport,
            exportReq?.incoterms,
            exportReq?.shipName,
            exportReq?.voyNo,
            exportReq?.packing,
            exportReq?.transportMode,
            exportReq?.fourtyf,
            exportReq?.twentyf,
            exportReq?.invoicePrice,
            exportReq?.blawbNo,
            exportReq?.departurePort,
            exportReq?.arrivalPort,
            cut,
            etdDate,
            etaDate,
            exportReq?.anAdvanceFee,
            exportReq?.supplierName,
            exportReq?.representativeItem,
            exportReq?.freightKg,
            exportReq?.freightM3,
            exportReq?.ctsNumber,
            exportReq?.size,
            exportReq?.rtShip,
            exportReq?.vwAir,
            exportReq?.hlBoxesNumber,
            exportReq?.moneyOrder,
            exportReq?.inportTaxAdvanceAmount,
            exportReq?.remarks,
            declarationDateInDate,
            inspectionDateInDate,
            deliveryDateAndTimeInDate,
            possiblePickUpDateInDate,
            pickUpDateInDate,
            exportReq?.containerNo,
            exportReq?.cargoInsurance,
            exportReq?.logisticsCost,
            freeTime,
            declarationDateUpdateStatus,
            inspectionDateUpdateStatus,
            deliveryDateUpdateStatus,
            possiblePickUpDateUpdateStatus,
            pickUpDateUpdateStatus,
            freedateupdatestatus,
            scheduleDatesUpdateStatus,
            userName,
            currentTime,
            userName,
            currentTime
        )

        exportRegistrationRepo?.updateExport(exportObj)

        return "redirect:/scheduleOne"
    }
}
