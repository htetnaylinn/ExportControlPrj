package com.example.exportControl.repository

import com.example.exportControl.model.*
import com.example.exportControl.model.requestModel.CompanyRequestModel
import com.example.exportControl.model.requestModel.PatternRequestModel
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface PatternRegistrationRepository : CrudRepository<PatternRegistration, String> {

    @Query(value = "select * from t_pattern_registration", nativeQuery = true)
    fun getAllProducts(): List<PatternRegistration>

    @Query(value = "select * from t_pattern_registration where pattern_name =:patternName", nativeQuery = true)
    fun getPatternObj(@Param("patternName") patternName: String?): PatternRegistration

    @Modifying
    @Transactional
    @Query(
        value ="""update t_pattern_registration set invoice_no = :#{#patternReq.invoiceCode}, customer_name = :#{#patternReq.customerName}, billing_month = :#{#patternReq.billingMonth}, booking_no = :#{#patternReq.bookingNo}, po_no = :#{#patternReq.poNO}, shipping_agency = :#{#patternReq.shippingAgency}, export_inport = :#{#patternReq.exportInport}, incoterms = :#{#patternReq.incoterms}, ship_name = :#{#patternReq.shipName}, voy_no = :#{#patternReq.voyNo}, packing = :#{#patternReq.packing}, transport_mode = :#{#patternReq.transportMode}, fourtyf = :#{#patternReq.fourtyf}, twentyf = :#{#patternReq.twentyf}, invoice_price = :#{#patternReq.invoicePrice}, bl_awb_no = :#{#patternReq.blawbNo}, departure_port = :#{#patternReq.departurePort}, arrival_port = :#{#patternReq.arrivalPort}, cut = :#{#patternReq.cut}, etd = :#{#patternReq.etd}, eta = :#{#patternReq.eta}, an_advance_fee = :#{#patternReq.anAdvanceFee}, supplier_name = :#{#patternReq.supplierName}, representative_item = :#{#patternReq.representativeItem}, freight_kg = :#{#patternReq.freightKg}, freight_m3 = :#{#patternReq.freightM3}, cts_number = :#{#patternReq.ctsNumber}, size = :#{#patternReq.size }, rt_ship = :#{#patternReq.rtShip}, vw_air = :#{#patternReq.vwAir}, hl_boxes_number = :#{#patternReq.hlBoxesNumber}, money_order = :#{#patternReq.moneyOrder}, inport_tax_advance_amount = :#{#patternReq.inportTaxAdvanceAmount}, remarks = :#{#patternReq.remarks}, declaration_date = :#{#patternReq.declarationDate}, inspection_date = :#{#patternReq.inspectionDate}, delivery_date_and_time = :#{#patternReq.deliveryDateAndTime}, possible_pick_up_date = :#{#patternReq. possiblePickUpDate}, pick_up_date = :#{#patternReq.pickUpDate}, container_no = :#{#patternReq.containerNo}, cargo_insurance = :#{#patternReq.cargoInsurance}, free_time = :#{#patternReq.freeTime},logistics_cost = :#{#patternReq.logisticsCost} where pattern_name = :#{#patternReq.patternName}""",
        nativeQuery = true)
    fun updatePatternRegistration(@Param("patternReq") patternReq: PatternRequestModel?)

    @Query(value = "select pattern_name from t_pattern_registration", nativeQuery = true)
    fun getAllPatternNameList(): List<String>
}