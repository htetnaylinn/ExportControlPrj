package com.example.exportControl.repository

import com.example.exportControl.model.CompanyControl
import com.example.exportControl.model.ScheduleControl
import com.example.exportControl.model.UserControl
import com.example.exportControl.model.requestModel.PatternRequestModel
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional


interface ScheduleControlRepository : CrudRepository<ScheduleControl, String> {

    @Query(value = "select * from schedule_control where user_code=:userCode", nativeQuery = true)
    fun selectAllByCompanyCode(@Param("userCode") userCode: String?): ScheduleControl?

    @Modifying
    @Transactional
    @Query(
        value ="update schedule_control set customer_name_flag=:customerName , pattern_name_flag=:patternName , billing_month_flag=:billingMonth , booking_no_flag=:bookingNo , po_no_flag=:poNO , shipping_agency_flag=:shippingAgency , export_inport_flag=:exportInport , incoterms_flag=:incoterms , ship_name_flag=:shipName , voy_no_flag=:voyNo , invoice_no_flag=:invoiceCode , packing_flag=:packing , transport_mode_flag=:transportMode , fourtyf_flag=:fourtyf , twentyf_flag=:twentyf , invoice_price_flag=:invoicePrice , bl_awb_no_flag=:blawbNo , departure_port_flag=:departurePort , arrival_port_flag=:arrivalPort , cut_flag=:cut , etd_flag=:etd , eta_flag=:eta , an_advance_fee_flag=:anAdvanceFee , supplier_name_flag=:supplierName , representative_item_flag=:representativeItem , freight_kg_flag=:freightKg , freight_m3_flag=:freightM3 , cts_number_flag=:ctsNumber , size_flag=:size , rt_ship_flag=:rtShip , vw_air_flag=:vwAir , hl_boxes_number_flag=:hlBoxesNumber , money_order_flag=:moneyOrder , inport_tax_advance_amount_flag=:inportTaxAdvanceAmount , remarks_flag=:remarks , declaration_date_flag=:declarationDate , inspection_date_flag=:inspectionDate , delivery_date_and_time_flag=:deliveryDateAndTime , possible_pick_up_date_flag=:possiblePickUpDate , pick_up_date_flag=:pickUpDate , container_no_flag=:containerNo , cargo_insurance_flag=:cargoInsurance , logistics_cost_flag=:logisticsCost ,free_time_flag = :freeTime " +
                "where user_code = :userCode ",
        nativeQuery = true)
    fun updatePatternRegistraion(
        customerName:Int?,
        patternName:Int?,
        billingMonth:Int?,
        bookingNo:Int?,
        poNO:Int?,
        shippingAgency:Int?,
        exportInport:Int?,
        incoterms:Int?,
        shipName:Int?,
        voyNo:Int?,
        invoiceCode:Int?,
        packing:Int?,
        transportMode:Int?,
        fourtyf:Int?,
        twentyf:Int?,
        invoicePrice:Int?,
        blawbNo:Int?,
        departurePort:Int?,
        arrivalPort:Int?,
        cut:Int?,
        etd:Int?,
        eta:Int?,
        anAdvanceFee:Int?,
        supplierName:Int?,
        representativeItem:Int?,
        freightKg:Int?,
        freightM3:Int?,
        ctsNumber:Int?,
        size:Int?,
        rtShip:Int?,
        vwAir:Int?,
        hlBoxesNumber:Int?,
        moneyOrder:Int?,
        inportTaxAdvanceAmount:Int?,
        remarks:Int?,
        declarationDate:Int?,
        inspectionDate:Int?,
        deliveryDateAndTime:Int?,
        possiblePickUpDate:Int?,
        pickUpDate:Int?,
        containerNo:Int?,
        cargoInsurance:Int?,
        logisticsCost:Int?,
        freeTime:Int?,
        userCode:String?
    )


}