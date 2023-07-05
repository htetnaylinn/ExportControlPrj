package com.example.exportControl.model.requestModel

import java.util.*
import javax.persistence.*

class PatternRequestModel {

    var patternCode : String? = null
    var invoiceCode : String? = null
    var customerName : String? = null
    var patternName : String? = null
    var billingMonth : Date? = null
    var bookingNo : String? = null
    var poNO : String? = null
    var shippingAgency : String? = null
    var exportInport : String? = null
    var incoterms : String? = null
    var shipName : String? = null
    var voyNo : String? = null
    var packing : String? = null
    var transportMode : String? = null
    var fourtyf : String? = null
    var twentyf : String? = null
    var invoicePrice : String? = null
    var blawbNo : String? = null
    var departurePort : String? = null
    var arrivalPort : String? = null
    var cut : Date? = null
    var etd : Date? = null
    var eta : Date? = null
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

    var declarationDate : Date? = null

    var inspectionDate : Date? = null

    var deliveryDateAndTime : Date? = null

    var possiblePickUpDate : Date? = null

    var pickUpDate : Date? = null

    var containerNo : String? = null

    var cargoInsurance : String? = null

    var logisticsCost : String? = null

    var freeTime : Date? = null


    constructor()
    constructor(
        patternCode: String?,
        invoiceCode: String?,
        customerName: String?,
        patternName: String?,
        billingMonth: Date?,
        bookingNo: String?,
        poNO: String?,
        shippingAgency: String?,
        exportInport: String?,
        incoterms: String?,
        shipName: String?,
        voyNo: String?,
        packing: String?,
        transportMode: String?,
        fourtyf: String?,
        twentyf: String?,
        invoicePrice: String?,
        blawbNo: String?,
        departurePort: String?,
        arrivalPort: String?,
        cut: Date?,
        etd: Date?,
        eta: Date?,
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
        declarationDate: Date?,
        inspectionDate: Date?,
        deliveryDateAndTime: Date?,
        possiblePickUpDate: Date?,
        pickUpDate: Date?,
        containerNo: String?,
        cargoInsurance: String?,
        logisticsCost: String?,
        freeTime: Date?
    ) {
        this.patternCode = patternCode
        this.invoiceCode = invoiceCode
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