package com.example.exportControl.service

import com.example.exportControl.model.CompanyControl
import com.example.exportControl.model.ScheduleControl
import com.example.exportControl.model.requestModel.CompanyRequestModel
import com.example.exportControl.model.requestModel.ScheduleRequestModel
import com.example.exportControl.repository.CompanyControlRepository
import com.example.exportControl.repository.CountryMasterRepository
import com.example.exportControl.repository.ScheduleControlRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime

@Service
class ScheduleControlService  {

    @Autowired
    private val scheduleControl: ScheduleControlRepository? = null

    /**
     * 新規会社コントロール登録実施
     * @param companyReq
     */

    fun saveScheduleControl(@Param("schedleControl") schedleControl: ScheduleRequestModel?,@Param("userCode") userCode: String?){

        var customerName : Int? = if (schedleControl?.customerName != null) 1 else 0
        var patternName : Int? = if (schedleControl?.patternName != null) 1 else 0
        var billingMonth : Int? = if (schedleControl?.billingMonth != null) 1 else 0
        var bookingNo : Int? = if (schedleControl?.bookingNo != null) 1 else 0
        var poNo : Int? = if (schedleControl?.poNo != null) 1 else 0
        var shippingAgency : Int? = if (schedleControl?.shippingAgency != null) 1 else 0
        var exportInport : Int? = if (schedleControl?.exportInport != null) 1 else 0
        var incoterms : Int? = if (schedleControl?.incoterms != null) 1 else 0
        var shipName : Int? = if (schedleControl?.shipName != null) 1 else 0
        var voyNo : Int? = if (schedleControl?.voyNo != null) 1 else 0
        var invoiceCode : Int? = if (schedleControl?.invoiceCode != null) 1 else 0
        var packing : Int? = if (schedleControl?.packing != null) 1 else 0
        var transportMode : Int? = if (schedleControl?.transportMode != null) 1 else 0
        var fourtyf : Int? = if (schedleControl?.fourtyf != null) 1 else 0
        var twentyf : Int? = if (schedleControl?.twentyf != null) 1 else 0
        var invoicePrice : Int? = if (schedleControl?.invoicePrice != null) 1 else 0
        var blawbNo : Int? = if (schedleControl?.blawbNo != null) 1 else 0
        var departurePort : Int? = if (schedleControl?.departurePort != null) 1 else 0
        var arrivalPort : Int? = if (schedleControl?.arrivalPort != null) 1 else 0
        var cut : Int? = if (schedleControl?.cut != null) 1 else 0
        var etd : Int? = if (schedleControl?.etd != null) 1 else 0
        var eta : Int? = if (schedleControl?.eta != null) 1 else 0
        var anAdvanceFee : Int? = if (schedleControl?.anAdvanceFee != null) 1 else 0
        var supplierName : Int? = if (schedleControl?.supplierName != null) 1 else 0
        var representativeItem : Int? = if (schedleControl?.representativeItem != null) 1 else 0
        var freightKg : Int? = if (schedleControl?.freightKg != null) 1 else 0
        var freightM3 : Int? = if (schedleControl?.freightM3 != null) 1 else 0
        var ctsNumber : Int? = if (schedleControl?.ctsNumber != null) 1 else 0
        var size : Int? = if (schedleControl?.size != null) 1 else 0
        var rtShip : Int? = if (schedleControl?.rtShip != null) 1 else 0
        var vwAir : Int? = if (schedleControl?.vwAir != null) 1 else 0
        var hlBoxesNumber : Int? = if (schedleControl?.hlBoxesNumber != null) 1 else 0
        var moneyOrder : Int? = if (schedleControl?.moneyOrder != null) 1 else 0
        var inportTaxAdvanceAmount : Int? = if (schedleControl?.inportTaxAdvanceAmount != null) 1 else 0
        var remarks : Int? = if (schedleControl?.remarks != null) 1 else 0
        var declarationDate : Int? = if (schedleControl?.declarationDate != null) 1 else 0
        var inspectionDate : Int? = if (schedleControl?.inspectionDate != null) 1 else 0
        var deliveryDateAndTime : Int? = if (schedleControl?.deliveryDateAndTime != null) 1 else 0
        var possiblePickUpDate : Int? = if (schedleControl?.possiblePickUpDate != null) 1 else 0
        var pickUpDate : Int? = if (schedleControl?.pickUpDate != null) 1 else 0
        var containerNo : Int? = if (schedleControl?.containerNo != null) 1 else 0
        var cargoInsurance : Int? = if (schedleControl?.cargoInsurance != null) 1 else 0
        var logisticsCost : Int? = if (schedleControl?.logisticsCost != null) 1 else 0
        var freeTime : Int? = if (schedleControl?.freeTime != null) 1 else 0

        val newScheduleControl = ScheduleControl(
            userCode,
            customerName  ,
            patternName  ,
            billingMonth  ,
            bookingNo  ,
            poNo  ,
            shippingAgency  ,
            exportInport  ,
            incoterms  ,
            shipName  ,
            voyNo  ,
            invoiceCode  ,
            packing  ,
            transportMode  ,
            fourtyf  ,
            twentyf  ,
            invoicePrice  ,
            blawbNo  ,
            departurePort  ,
            arrivalPort  ,
            cut  ,
            etd  ,
            eta  ,
            anAdvanceFee  ,
            supplierName  ,
            representativeItem  ,
            freightKg  ,
            freightM3  ,
            ctsNumber  ,
            size  ,
            rtShip  ,
            vwAir  ,
            hlBoxesNumber  ,
            moneyOrder  ,
            inportTaxAdvanceAmount  ,
            remarks  ,
            declarationDate  ,
            inspectionDate  ,
            deliveryDateAndTime  ,
            possiblePickUpDate  ,
            pickUpDate  ,
            containerNo  ,
            cargoInsurance  ,
            logisticsCost,
            freeTime
                )
        if(scheduleControl?.selectAllByCompanyCode(userCode) == null){
//            save
            scheduleControl?.save(newScheduleControl)
        }else{
//            update
            scheduleControl?.updatePatternRegistraion(customerName ,
                patternName ,
                billingMonth ,
                bookingNo ,
                poNo ,
                shippingAgency ,
                exportInport ,
                incoterms ,
                shipName ,
                voyNo ,
                invoiceCode ,
                packing ,
                transportMode ,
                fourtyf ,
                twentyf ,
                invoicePrice ,
                blawbNo ,
                departurePort ,
                arrivalPort ,
                cut ,
                etd ,
                eta ,
                anAdvanceFee ,
                supplierName ,
                representativeItem ,
                freightKg ,
                freightM3 ,
                ctsNumber ,
                size ,
                rtShip ,
                vwAir ,
                hlBoxesNumber ,
                moneyOrder ,
                inportTaxAdvanceAmount ,
                remarks ,
                declarationDate ,
                inspectionDate ,
                deliveryDateAndTime ,
                possiblePickUpDate ,
                pickUpDate ,
                containerNo ,
                cargoInsurance ,
                logisticsCost,freeTime,userCode)
        }


    }
}