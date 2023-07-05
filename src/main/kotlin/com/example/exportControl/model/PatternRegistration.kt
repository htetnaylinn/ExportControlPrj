package com.example.exportControl.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "t_pattern_registration")
class PatternRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pattern_code", updatable = false, nullable = false)
    var patternCode : String? = null

    @Column(name = "invoice_no")
    var invoiceCode : String? = null

    @Column(name = "customer_name")
    var customerName : String? = null

    @Column(name = "pattern_name")
    var patternName : String? = null

    @Column(name = "billing_month")
    var billingMonth : Date? = null

    @Column(name = "booking_no")
    var bookingNo : String? = null

    @Column(name = "po_no")
    var poNO : String? = null

    @Column(name = "shipping_agency")
    var shippingAgency : String? = null

    @Column(name = "export_inport")
    var exportInport : String? = null

    @Column(name = "incoterms")
    var incoterms : String? = null

    @Column(name = "ship_name")
    var shipName : String? = null

    @Column(name = "voy_no")
    var voyNo : String? = null

    @Column(name = "packing")
    var packing : String? = null

    @Column(name = "transport_mode")
    var transportMode : String? = null

    @Column(name = "fourtyf")
    var fourtyf : String? = null

    @Column(name = "twentyf")
    var twentyf : String? = null

    @Column(name = "invoice_price")
    var invoicePrice : String? = null

    @Column(name = "bl_awb_no")
    var blawbNo : String? = null

    @Column(name = "departure_port")
    var departurePort : String? = null

    @Column(name = "arrival_port")
    var arrivalPort : String? = null

    @Column(name = "cut")
    var cut : Date? = null

    @Column(name = "etd")
    var etd : Date? = null

    @Column(name = "eta")
    var eta : Date? = null

    @Column(name = "an_advance_fee")
    var anAdvanceFee : String? = null

    @Column(name = "supplier_name")
    var supplierName : String? = null

    @Column(name = "representative_item")
    var representativeItem : String? = null

    @Column(name = "freight_kg")
    var freightKg : String? = null

    @Column(name = "freight_m3")
    var freightM3 : String? = null

    @Column(name = "cts_number")
    var ctsNumber : String? = null

    @Column(name = "size")
    var size : String? = null

    @Column(name = "rt_ship")
    var rtShip : String? = null

    @Column(name = "vw_air")
    var vwAir : String? = null

    @Column(name = "hl_boxes_number")
    var hlBoxesNumber : String? = null

    @Column(name = "money_order")
    var moneyOrder : String? = null

    @Column(name = "inport_tax_advance_amount")
    var inportTaxAdvanceAmount : String? = null

    @Column(name = "remarks")
    var remarks : String? = null

    @Column(name = "declaration_date")
    var declarationDate : Date? = null

    @Column(name = "inspection_date")
    var inspectionDate : Date? = null

    @Column(name = "delivery_date_and_time")
    var deliveryDateAndTime : Date? = null

    @Column(name = "possible_pick_up_date")
    var possiblePickUpDate : Date? = null

    @Column(name = "pick_up_date")
    var pickUpDate : Date? = null

    @Column(name = "container_no")
    var containerNo : String? = null

    @Column(name = "cargo_insurance")
    var cargoInsurance : String? = null

    @Column(name = "logistics_cost")
    var logisticsCost : String? = null

    @Column(name = "free_time")
    var freeTime : Date? = null

    constructor()
    constructor(
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