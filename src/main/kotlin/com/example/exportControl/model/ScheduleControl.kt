package com.example.exportControl.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "schedule_control")
class ScheduleControl{

    @Id
    @Column(name = "user_code")
    var companyCode : String? = null

    @Column(name = "customer_name_flag")
    var customerName : Int? = null

    @Column(name = "pattern_name_flag")
    var patternName : Int? = null

    @Column(name = "billing_month_flag")
    var billingMonth : Int? = null

    @Column(name = "booking_no_flag")
    var bookingNo : Int? = null

    @Column(name = "po_no_flag")
    var poNO : Int? = null

    @Column(name = "shipping_agency_flag")
    var shippingAgency : Int? = null

    @Column(name = "export_inport_flag")
    var exportInport : Int? = null

    @Column(name = "incoterms_flag")
    var incoterms : Int? = null

    @Column(name = "ship_name_flag")
    var shipName : Int? = null

    @Column(name = "voy_no_flag")
    var voyNo : Int? = null

    @Column(name = "invoice_no_flag")
    var invoiceCode : Int? = null

    @Column(name = "packing_flag")
    var packing : Int? = null

    @Column(name = "transport_mode_flag")
    var transportMode : Int? = null

    @Column(name = "fourtyf_flag")
    var fourtyf : Int? = null

    @Column(name = "twentyf_flag")
    var twentyf : Int? = null

    @Column(name = "invoice_price_flag")
    var invoicePrice : Int? = null

    @Column(name = "bl_awb_no_flag")
    var blawbNo : Int? = null

    @Column(name = "departure_port_flag")
    var departurePort : Int? = null

    @Column(name = "arrival_port_flag")
    var arrivalPort : Int? = null

    @Column(name = "cut_flag")
    var cut : Int? = null

    @Column(name = "etd_flag")
    var etd : Int? = null

    @Column(name = "eta_flag")
    var eta : Int? = null

    @Column(name = "an_advance_fee_flag")
    var anAdvanceFee : Int? = null

    @Column(name = "supplier_name_flag")
    var supplierName : Int? = null

    @Column(name = "representative_item_flag")
    var representativeItem : Int? = null

    @Column(name = "freight_kg_flag")
    var freightKg : Int? = null

    @Column(name = "freight_m3_flag")
    var freightM3 : Int? = null

    @Column(name = "cts_number_flag")
    var ctsNumber : Int? = null

    @Column(name = "size_flag")
    var size : Int? = null

    @Column(name = "rt_ship_flag")
    var rtShip : Int? = null

    @Column(name = "vw_air_flag")
    var vwAir : Int? = null

    @Column(name = "hl_boxes_number_flag")
    var hlBoxesNumber : Int? = null

    @Column(name = "money_order_flag")
    var moneyOrder : Int? = null

    @Column(name = "inport_tax_advance_amount_flag")
    var inportTaxAdvanceAmount : Int? = null

    @Column(name = "remarks_flag")
    var remarks : Int? = null

    @Column(name = "declaration_date_flag")
    var declarationDate : Int? = null

    @Column(name = "inspection_date_flag")
    var inspectionDate : Int? = null

    @Column(name = "delivery_date_and_time_flag")
    var deliveryDateAndTime : Int? = null

    @Column(name = "possible_pick_up_date_flag")
    var possiblePickUpDate : Int? = null

    @Column(name = "pick_up_date_flag")
    var pickUpDate : Int? = null

    @Column(name = "container_no_flag")
    var containerNo : Int? = null

    @Column(name = "cargo_insurance_flag")
    var cargoInsurance : Int? = null

    @Column(name = "logistics_cost_flag")
    var logisticsCost : Int? = null

    @Column(name = "free_time_flag")
    var freeTime : Int? = null

   constructor()
    constructor(
        companyCode: String?,
        customerName: Int?,
        patternName: Int?,
        billingMonth: Int?,
        bookingNo: Int?,
        poNO: Int?,
        shippingAgency: Int?,
        exportInport: Int?,
        incoterms: Int?,
        shipName: Int?,
        voyNo: Int?,
        invoiceCode: Int?,
        packing: Int?,
        transportMode: Int?,
        fourtyf: Int?,
        twentyf: Int?,
        invoicePrice: Int?,
        blawbNo: Int?,
        departurePort: Int?,
        arrivalPort: Int?,
        cut: Int?,
        etd: Int?,
        eta: Int?,
        anAdvanceFee: Int?,
        supplierName: Int?,
        representativeItem: Int?,
        freightKg: Int?,
        freightM3: Int?,
        ctsNumber: Int?,
        size: Int?,
        rtShip: Int?,
        vwAir: Int?,
        hlBoxesNumber: Int?,
        moneyOrder: Int?,
        inportTaxAdvanceAmount: Int?,
        remarks: Int?,
        declarationDate: Int?,
        inspectionDate: Int?,
        deliveryDateAndTime: Int?,
        possiblePickUpDate: Int?,
        pickUpDate: Int?,
        containerNo: Int?,
        cargoInsurance: Int?,
        logisticsCost: Int?,
        freeTime: Int?
    ) {
        this.companyCode = companyCode
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
