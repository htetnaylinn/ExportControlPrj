package com.example.exportControl.model.requestModel

import java.util.*
import javax.persistence.*

class ScheduleColumnNames {

    var customerName : String? = null
    var patternName : String? = null
    var billingMonth : String? = null
    var bookingNo : String? = null
    var poNO : String? = null
    var shippingAgency : String? = null
    var exportInport : String? = null
    var incoterms : String? = null
    var shipName : String? = null
    var voyNo : String? = null
    var invoiceCode :String? = null
    var packing : String? = null
    var transportMode : String? = null
    var fourtyf : String? = null
    var twentyf : String? = null
    var invoicePrice : String? = null
    var blawbNo : String? = null
    var departurePort : String? = null
    var arrivalPort : String? = null
    var cut : String? = null
    var etd : String? = null
    var eta : String? = null
    var anAdvanceFee : String? = null
    var supplierName : String? = null
    var representativeItem : String? = null
    var freightKg : String? = null
    var freightM3 : String? = null
    var ctsNumber : String? = null
    var size : String? = null
    var rtShip : String? = null
    var vwAir : String? = null
    var hlBoxesNumber : String? = null
    var moneyOrder : String? = null
    var inportTaxAdvanceAmount : String? = null
    var remarks : String? = null
    var declarationDate : String? = null
    var inspectionDate : String? = null
    var deliveryDateAndTime : String? = null
    var possiblePickUpDate : String? = null
    var pickUpDate : String? = null
    var containerNo : String? = null
    var cargoInsurance : String? = null
    var logisticsCost : String? = null
    var freeTime : String? = null

    constructor()
    constructor(
        customerName: String?,
        patternName: String?,
        billingMonth: String?,
        bookingNo: String?,
        poNO: String?,
        shippingAgency: String?,
        exportInport: String?,
        incoterms: String?,
        shipName: String?,
        voyNo: String?,
        invoiceCode: String?,
        packing: String?,
        transportMode: String?,
        fourtyf: String?,
        twentyf: String?,
        invoicePrice: String?,
        blawbNo: String?,
        departurePort: String?,
        arrivalPort: String?,
        cut: String?,
        etd: String?,
        eta: String?,
        anAdvanceFee: String?,
        supplierName: String?,
        representativeItem: String?,
        freightKg: String?,
        freightM3: String?,
        ctsNumber: String?,
        size: String?,
        rtShip: String?,
        vwAir: String?,
        hlBoxesNumber: String?,
        moneyOrder: String?,
        inportTaxAdvanceAmount: String?,
        remarks: String?,
        declarationDate: String?,
        inspectionDate: String?,
        deliveryDateAndTime: String?,
        possiblePickUpDate: String?,
        pickUpDate: String?,
        containerNo: String?,
        cargoInsurance: String?,
        logisticsCost: String?,
        freeTime: String?
    ) {
        this.customerName = customerName
        this.patternName = patternName
        this.billingMonth = billingMonth
        this.bookingNo = bookingNo
        this.poNO = poNO
        this.shippingAgency = shippingAgency
        this.exportInport = exportInport
        this.incoterms = incoterms
        this.shipName = shipName
        this.voyNo = voyNo
        this.invoiceCode = invoiceCode
        this.packing = packing
        this.transportMode = transportMode
        this.fourtyf = fourtyf
        this.twentyf = twentyf
        this.invoicePrice = invoicePrice
        this.blawbNo = blawbNo
        this.departurePort = departurePort
        this.arrivalPort = arrivalPort
        this.cut = cut
        this.etd = etd
        this.eta = eta
        this.anAdvanceFee = anAdvanceFee
        this.supplierName = supplierName
        this.representativeItem = representativeItem
        this.freightKg = freightKg
        this.freightM3 = freightM3
        this.ctsNumber = ctsNumber
        this.size = size
        this.rtShip = rtShip
        this.vwAir = vwAir
        this.hlBoxesNumber = hlBoxesNumber
        this.moneyOrder = moneyOrder
        this.inportTaxAdvanceAmount = inportTaxAdvanceAmount
        this.remarks = remarks
        this.declarationDate = declarationDate
        this.inspectionDate = inspectionDate
        this.deliveryDateAndTime = deliveryDateAndTime
        this.possiblePickUpDate = possiblePickUpDate
        this.pickUpDate = pickUpDate
        this.containerNo = containerNo
        this.cargoInsurance = cargoInsurance
        this.logisticsCost = logisticsCost
        this.freeTime = freeTime
    }
}