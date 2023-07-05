package com.example.exportControl.controller

import com.example.exportControl.model.PatternRegistration
import com.example.exportControl.model.requestModel.ExportRequestModel
import com.example.exportControl.repository.*
import com.example.exportControl.util.ExportTImportENG
import com.example.exportControl.util.ExportTImportJPN
import com.example.exportControl.util.TransportModeENG
import com.example.exportControl.util.TransportModeJPN
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import java.security.Principal
import java.text.SimpleDateFormat
import java.util.*

@Controller
class NewRegistrationController  : MenuController(){

    @Autowired
    private val patternRegistrationRepo: PatternRegistrationRepository? = null

    @Autowired
    private val portRepo: PortMasterRepository? = null

    @RequestMapping("/newRegistration")
    fun patternAdd(model: Model,principal: Principal): String {

        val userName = principal.name
        val user = userMasterRepo?.selectUserByUserCode(userName)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(user?.companyCode)
        model.addAttribute("languageOption",webConfig?.messageSourcePath)
        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("portList", portRepo?.selectAllPort())

        model.addAttribute("patternList",patternRegistrationRepo?.getAllPatternNameList() )


        if(role != "2"){
            model.addAttribute("customerList", listOf(loginCompany))
        }else{
            model.addAttribute("customerList", companyMasterRepo?.selectAllCompany())
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

        return "newRegistration"
    }

    @RequestMapping("/patternEdit")
    fun patternEdit(model: Model,principal: Principal): String {

        val userName = principal.name
        val user = userMasterRepo?.selectUserByUserCode(userName)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(user?.companyCode)
        val allPatternList = patternRegistrationRepo?.getAllProducts()
        model.addAttribute("languageOption",webConfig?.messageSourcePath)
        model.addAttribute("allPatternList",allPatternList)
        model.addAttribute("loginCompany", loginCompany)
        if(role != "2"){
            model.addAttribute("customerList", listOf(loginCompany))
        }else{
            model.addAttribute("customerList", companyMasterRepo?.selectAllCompany())
        }
        model.addAttribute("patternList", allPatternList)
        model.addAttribute("languageOption",webConfig?.messageSourcePath)
        model.addAttribute("portList", portRepo?.selectAllPort())
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
        return "patternEdit"
    }

    @RequestMapping("/sample")
    fun sample(model: Model,principal: Principal): String {

        val userName = principal.name
        val user = userMasterRepo?.selectUserByUserCode(userName)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(user?.companyCode)
        model.addAttribute("languageOption",webConfig?.messageSourcePath)

        model.addAttribute("loginCompany", loginCompany)

        return "Sample"
    }

    @RequestMapping("/savePatternRegistration")
    fun savePatternRegistration(
        @ModelAttribute("exportObj") exportReq: ExportRequestModel?,
        model: Model, principal: Principal
    ): String {
        val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
        val formatter2 = SimpleDateFormat("yyyy/MM",Locale.ENGLISH)
        val billingMonthInDate =  formatter2.parse(exportReq?.billingMonth)
        val declarationDateInDate = formatter.parse(exportReq?.declarationDate)
        val inspectionDateInDate = formatter.parse(exportReq?.inspectionDate)
        val deliveryDateAndTimeInDate = formatter.parse(exportReq?.deliveryDateAndTime)
        val possiblePickUpDateInDate = formatter.parse(exportReq?.possiblePickUpDate)
        val pickUpDateInDate = formatter.parse(exportReq?.pickUpDate)
        val etdDate = formatter.parse(exportReq?.etd)
        val etaDate = formatter.parse(exportReq?.eta)
        val freeTime = formatter.parse(exportReq?.freeTime)
        val cut = formatter.parse(exportReq?.cut)
        val newPatternRegistration = PatternRegistration( exportReq?.invoiceCode,
            exportReq?.customerName,
            exportReq?.patternName,
            billingMonthInDate,
            exportReq?. bookingNo,
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
            exportReq?.hlBoxesNumber ,
            exportReq?.moneyOrder ,
            exportReq?.inportTaxAdvanceAmount,
            exportReq?.remarks,
            declarationDateInDate ,
            inspectionDateInDate ,
            deliveryDateAndTimeInDate ,
            possiblePickUpDateInDate ,
            pickUpDateInDate ,
            exportReq?.containerNo,
            exportReq?.cargoInsurance,
            exportReq?.logisticsCost,
            freeTime
        )

        patternRegistrationRepo?.save(newPatternRegistration)

        return "redirect:/newRegistration"
    }

}
