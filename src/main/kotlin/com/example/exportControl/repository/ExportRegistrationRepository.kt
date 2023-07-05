package com.example.exportControl.repository

import com.example.exportControl.model.ExportProductSave
import com.example.exportControl.model.ExportRegistration
import com.example.exportControl.model.Muser
import org.springframework.data.jpa.repository.Modifying


import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

import java.sql.Date
import javax.transaction.Transactional

interface ExportRegistrationRepository : CrudRepository<ExportRegistration, String> {

    @Query(value = "select declaration_date from t_export_registration where customer_name = :companyCode order by declaration_date limit 1", nativeQuery = true)
    fun getEarliestDeclarationDate(@Param("companyCode")companyCode: String?): Date?

    @Query(value = "select * from t_export_registration where customer_name = :companyCode order by created_dt desc", nativeQuery = true)
    fun getAllProducts(@Param("companyCode")companyCode:String?): List<ExportRegistration>

    @Query(value = "select * from t_export_registration where invoice_no = :invoiceNo and customer_name = :companyCode ", nativeQuery = true)
    fun getExportProductByInvoiceAndCustomerName(@Param("invoiceNo")invoiceNo:String?,@Param("companyCode")companyCode:String?): ExportRegistration


    @Query(value = "select * from t_export_registration where invoice_no = :invoiceCode order by created_dt desc", nativeQuery = true)
    fun searchWithCode(@Param("invoiceCode")invoiceCode:String?): List<ExportRegistration>

    @Query(value = "select * from t_export_registration where customer_name = :customerName order by created_dt desc", nativeQuery = true)
    fun searchWithCustomer(@Param("customerName")customerName:String?): List<ExportRegistration>

    @Query(value = "select * from t_export_registration where pattern_name = :customerName and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithPattern(@Param("customerName")patternName:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where booking_no = :bookingNo and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithBooking(@Param("bookingNo")bookingNo:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where po_no = :poNo and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithPo(@Param("poNo")poNo:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where invoice_price = :invoicePrice and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithPrice(@Param("invoicePrice")invoicePrice:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where Date(billing_month) = Date(:billingMonth) and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithBillingMonth(@Param("billingMonth")billingMonth:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where departure_port = :departurePort and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithDeparturePort(@Param("departurePort")departurePort:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where transport_mode = :transportMode and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithTransport(@Param("transportMode")transportMode:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where arrival_port = :arrivalPort and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithArrivalPort(@Param("arrivalPort")arrivalPort:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where shipping_agency = :shipping and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithShipping(@Param("shipping")shipping:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where export_inport = :exportInport and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithExportInport(@Param("exportInport")exportInport:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where incoterms = :incoterms and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithIncoterms(@Param("incoterms")incoterms:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where ship_name = :shipName and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithShipName(@Param("shipName")shipName:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where voy_no = :voy and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithVOY(@Param("voy")voy:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where packing = :packing and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithPacking(@Param("packing")packing:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where fourtyf = :fourtyf and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithfourtyf(@Param("fourtyf")fourtyf:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where twentyf = :twentyf and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithtwentyf(@Param("twentyf")twentyf:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where bl_awb_no = :blAwbNo and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithblAwbNo(@Param("blAwbNo")blAwbNo:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where cut = :cut and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithcut(@Param("cut")cut:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where etd = :etd and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithetd(@Param("etd")etd:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where eta = :eta and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWitheta(@Param("eta")eta:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where an_advance_fee = :advanceFee and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithadvAnceFee(@Param("advanceFee")advanceFee:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where supplier_name = :supplierName and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithSupplierName(@Param("supplierName")supplierName:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where representative_item = :representativeItem and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithRepresentativeItem(@Param("representativeItem")representativeItem:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where freight_kg = :kg and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithKg(@Param("kg")kg:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where freight_m3 = :m3 and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithM3(@Param("m3")m3:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where cts_number = :cts and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithCts(@Param("cts")cts:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where size = :size and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithSize(@Param("size")size:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where rt_ship = :rtShip and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithRT(@Param("rtShip")rtShip:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where vw_air = :vwAir and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithVW(@Param("vwAir")vwAir:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where hl_boxes_number = :hlBox and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithHlBox(@Param("hlBox")hlBox:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where money_order = :moneyOrder and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithMoneyOrder(@Param("moneyOrder")moneyOrder:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where inport_tax_advance_amount = :inportIax and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithInportTax(@Param("inportIax")inportIax:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where remarks = :remarks and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithremarks(@Param("remarks")remarks:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where Date(order by declaration_date) = Date(:declarationDate) and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithDeclarationDate(@Param("declarationDate")declarationDate:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where Date(inspection_date) = Date(:inspectionDate) and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithInspectionDate(@Param("inspectionDate")inspectionDate:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where Date(delivery_date_and_time) = Date(:deliveryDate) and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithDeliveryDate(@Param("deliveryDate")deliveryDate:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where Date(possible_pick_up_date) = Date(:possibleDate) and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithPossibleDate(@Param("possibleDate")possibleDate:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where Date(pick_up_date) = Date(:pickupDate) and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithPickupDate(@Param("pickupDate")pickupDate:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where container_no = :containerNo and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithContainerNo(@Param("containerNo")containerNo:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where cargo_insurance = :cargoInsurance and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithCargoInsurance(@Param("cargoInsurance")cargoInsurance:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where logistics_cost = :logisticsCost and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithlogisticsCost(@Param("logisticsCost")logisticsCost:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>

    @Query(value = "select * from t_export_registration where invoice_no = :invoiceNo and customer_name = :companyCode", nativeQuery = true)
    fun searchWithInvoiceCode(@Param("invoiceNo")invoiceNo:String?,@Param("companyCode")companyCode:String?):  ExportRegistration

    @Query(value = "select * from t_export_registration where Date(free_time) = Date(:freeTime) and customer_name = :companyCode  order by created_dt desc", nativeQuery = true)
    fun searchWithFreeTime(@Param("freeTime")freeTime:String?,@Param("companyCode")companyCode:String?):  List<ExportRegistration>


    @Query(value = "select Distinct m.company_name_jpn,m.company_code  from t_export_registration as t join m_company as m on t.customer_name = m.company_code where t.customer_name = :companyCode and  t.customer_name is not null and t.customer_name != ''", nativeQuery = true)
    fun customerName(@Param("companyCode")companyCode:String?): List<List<String>>?

    @Query(value = "select Distinct pattern_name from t_export_registration where customer_name = :companyCode and  pattern_name is not null and pattern_name != ''", nativeQuery = true)
    fun patternName(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct billing_month from t_export_registration where customer_name = :companyCode and  billing_month is not null ", nativeQuery = true)
    fun billingMonth(@Param("companyCode")companyCode:String?): List<Date>?

    @Query(value = "select Distinct booking_no from t_export_registration where customer_name = :companyCode and  booking_no is not null and booking_no != ''", nativeQuery = true)
    fun bookingNo(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct po_no from t_export_registration where customer_name = :companyCode and  po_no is not null and po_no != ''", nativeQuery = true)
    fun poNO(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct shipping_agency from t_export_registration where customer_name = :companyCode and  shipping_agency is not null and shipping_agency != ''", nativeQuery = true)
    fun shippingAgency(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct export_inport from t_export_registration where customer_name = :companyCode and  export_inport is not null and export_inport != ''", nativeQuery = true)
    fun exportInport(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct incoterms from t_export_registration where customer_name = :companyCode and  incoterms is not null and incoterms != ''", nativeQuery = true)
    fun incoterms(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct ship_name from t_export_registration where customer_name = :companyCode and  ship_name is not null and ship_name != ''", nativeQuery = true)
    fun shipName(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct voy_no from t_export_registration where customer_name = :companyCode and  voy_no is not null and voy_no != ''", nativeQuery = true)
    fun voyNo(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct packing from t_export_registration where customer_name = :companyCode and  packing is not null and packing != ''", nativeQuery = true)
    fun packing(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct transport_mode from t_export_registration where customer_name = :companyCode and  transport_mode is not null and transport_mode != ''", nativeQuery = true)
    fun transportMode(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct fourtyf from t_export_registration where customer_name = :companyCode and  fourtyf is not null and fourtyf != ''", nativeQuery = true)
    fun fourtyf(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct twentyf from t_export_registration where customer_name = :companyCode and  twentyf is not null and twentyf != ''", nativeQuery = true)
    fun twentyf(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct invoice_price from t_export_registration where customer_name = :companyCode and  invoice_price is not null and invoice_price != ''", nativeQuery = true)
    fun invoicePrice(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct bl_awb_no from t_export_registration where customer_name = :companyCode and  bl_awb_no is not null and bl_awb_no != ''", nativeQuery = true)
    fun blawbNo(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct m.port_name,m.port_code from t_export_registration as t join m_port as m on t.departure_port = m.port_code \n" +
            "where t.customer_name = :companyCode and  t.departure_port is not null and t.departure_port != ''", nativeQuery = true)
    fun departurePort(@Param("companyCode")companyCode:String?): List<List<String>>?

    @Query(value = "select Distinct m.port_name,m.port_code from t_export_registration as t join m_port as m on t.arrival_port = m.port_code \n" +
            "where t.customer_name = :companyCode and  t.arrival_port is not null and t.arrival_port != ''", nativeQuery = true)
    fun arrivalPort(@Param("companyCode")companyCode:String?): List<List<String>>?

    @Query(value = "select Distinct cut from t_export_registration where customer_name = :companyCode and  cut is not null", nativeQuery = true)
    fun cut(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct etd from t_export_registration where customer_name = :companyCode and  etd is not null ", nativeQuery = true)
    fun etd(@Param("companyCode")companyCode:String?): List<Date>?

    @Query(value = "select Distinct eta from t_export_registration where customer_name = :companyCode and  eta is not null ", nativeQuery = true)
    fun eta(@Param("companyCode")companyCode:String?): List<Date>?

    @Query(value = "select Distinct an_advance_fee from t_export_registration where customer_name = :companyCode and  an_advance_fee is not null and an_advance_fee != ''", nativeQuery = true)
    fun anAdvanceFee(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct supplier_name from t_export_registration where customer_name = :companyCode and  supplier_name is not null and supplier_name != ''", nativeQuery = true)
    fun supplierName(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct representative_item from t_export_registration where customer_name = :companyCode and  representative_item is not null and representative_item != ''", nativeQuery = true)
    fun representativeItem(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct freight_kg from t_export_registration where customer_name = :companyCode and  freight_kg is not null and freight_kg != ''", nativeQuery = true)
    fun freightKg(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct freight_m3 from t_export_registration where customer_name = :companyCode and  freight_m3 is not null and freight_m3 != ''", nativeQuery = true)
    fun freightM3(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct cts_number from t_export_registration where customer_name = :companyCode and  cts_number is not null and cts_number != ''", nativeQuery = true)
    fun ctsNumber(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct size from t_export_registration where customer_name = :companyCode and  size is not null and size != ''", nativeQuery = true)
    fun size(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct rt_ship from t_export_registration where customer_name = :companyCode and  rt_ship is not null and rt_ship != ''", nativeQuery = true)
    fun rtShip(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct vw_air from t_export_registration where customer_name = :companyCode and  vw_air is not null and vw_air != '' ", nativeQuery = true)
    fun vwAir(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct hl_boxes_number from t_export_registration where customer_name = :companyCode and  hl_boxes_number is not null and hl_boxes_number != ''", nativeQuery = true)
    fun hlBoxesNumber(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct money_order from t_export_registration where customer_name = :companyCode and  money_order is not null and money_order != '' ", nativeQuery = true)
    fun moneyOrder(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct inport_tax_advance_amount from t_export_registration where customer_name = :companyCode and  inport_tax_advance_amount is not null and inport_tax_advance_amount != '' ", nativeQuery = true)
    fun inportTaxAdvanceAmount(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct remarks from t_export_registration where customer_name = :companyCode and  remarks is not null and remarks != ''", nativeQuery = true)
    fun remarks(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct declaration_date from t_export_registration where customer_name = :companyCode and  declaration_date is not null ", nativeQuery = true)
    fun declarationDate(@Param("companyCode")companyCode:String?): List<Date>?

    @Query(value = "select Distinct inspection_date from t_export_registration where customer_name = :companyCode and  inspection_date is not null ", nativeQuery = true)
    fun inspectionDate(@Param("companyCode")companyCode:String?): List<Date>?

    @Query(value = "select Distinct delivery_date_and_time from t_export_registration where customer_name = :companyCode and  delivery_date_and_time is not null", nativeQuery = true)
    fun deliveryDateAndTime(@Param("companyCode")companyCode:String?): List<Date>?

    @Query(value = "select Distinct possible_pick_up_date from t_export_registration where customer_name = :companyCode and  possible_pick_up_date is not null ", nativeQuery = true)
    fun possiblePickUpDate(@Param("companyCode")companyCode:String?): List<Date>?

    @Query(value = "select Distinct pick_up_date from t_export_registration where customer_name = :companyCode and  pick_up_date is not null ", nativeQuery = true)
    fun pickUpDate(@Param("companyCode")companyCode:String?): List<Date>?

    @Query(value = "select Distinct container_no from t_export_registration where customer_name = :companyCode and  container_no is not null and container_no != ''", nativeQuery = true)
    fun containerNo(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct cargo_insurance from t_export_registration where customer_name = :companyCode and  cargo_insurance is not null and cargo_insurance != ''", nativeQuery = true)
    fun cargoInsurance(@Param("companyCode")companyCode:String?): List<String>?

    @Query(value = "select Distinct logistics_cost from t_export_registration where customer_name = :companyCode and  logistics_cost is not null and logistics_cost != '' ", nativeQuery = true)
    fun logisticsCost(@Param("companyCode")companyCode:String?): List<String>?
    @Query(value = "select Distinct free_time from t_export_registration where customer_name = :companyCode and  free_time is not null ", nativeQuery = true)
    fun freeTime(@Param("companyCode")companyCode:String?): List<Date>?



    @Query(value = "select invoice_no from t_export_registration where customer_name = :companyCode", nativeQuery = true)
    fun getSavedInvoiceListByCompany(@Param("companyCode")companyCode:String?): List<String>?





    //Search with Input Fields

    @Query(value = "select *  from t_export_registration where CASE WHEN :declarationStartDate <> '' THEN Date(declaration_date) >= Date(:declarationStartDate) and Date(declaration_date) <= Date(:declarationEndDate) ELSE true END and " +
            "CASE WHEN :inspectionStartDate <> '' THEN Date(inspection_date) >= Date(:inspectionStartDate) and Date(inspection_date) <= Date(:inspectionEndDate) ELSE true END and " +
            "CASE WHEN :deliveryStartDate <> '' THEN Date(delivery_date_and_time) >= Date(:deliveryStartDate) and Date(delivery_date_and_time) <= Date(:deliveryEndDate) ELSE true END and " +
            "CASE WHEN :possiblePickUpStartDate <> '' THEN Date(possible_pick_up_date) >= Date(:possiblePickUpStartDate) and Date(possible_pick_up_date) <= Date(:possiblePickUpEndDate) ELSE true END and " +
            "CASE WHEN :pickUpStartDate <> '' THEN Date(pick_up_date) >= Date(:pickUpStartDate) and Date(pick_up_date) <= Date(:pickUpEndDate) ELSE true END and " +
            "CASE WHEN :freeTimeStartDate <> '' THEN Date(free_time) >= Date(:freeTimeStartDate) and Date(free_time) <= Date(:freeTimeEndDate) ELSE true END and " +
            "CASE WHEN :arrivalPort <> '' THEN arrival_port = :arrivalPort  ELSE true END and " +
            "CASE WHEN :departurePort <> '' THEN departure_port = :departurePort  ELSE true END " +
            "and customer_name = :companyCode ", nativeQuery = true)
    fun searchWithInputFields(@Param("companyCode")companyCode:String?,
                              @Param("declarationStartDate")declarationStartDate:String?,
                              @Param("declarationEndDate")declarationEndDate:String?,
                              @Param("inspectionStartDate")inspectionStartDate:String?,
                              @Param("inspectionEndDate")inspectionEndDate:String?,
                              @Param("deliveryStartDate")deliveryStartDate:String?,
                              @Param("deliveryEndDate")deliveryEndDate:String?,
                              @Param("possiblePickUpStartDate")possiblePickUpStartDate:String?,
                              @Param("possiblePickUpEndDate")possiblePickUpEndDate:String?,
                              @Param("pickUpStartDate")pickUpStartDate:String?,
                              @Param("pickUpEndDate")pickUpEndDate:String?,
                              @Param("freeTimeStartDate")freeTimeStartDate:String?,
                              @Param("freeTimeEndDate")freeTimeEndDate:String?,
                              @Param("arrivalPort")arrivalPort:String?,
                              @Param("departurePort")departurePort:String?

    ): List<ExportRegistration>?

    @Modifying
    @Transactional
    @Query(
        value ="""update t_export_registration set customer_name = :#{#exportObj.customerName}, billing_month = :#{#exportObj.billingMonth}, booking_no = :#{#exportObj.bookingNo}, po_no = :#{#exportObj.poNO}, shipping_agency = :#{#exportObj.shippingAgency}, export_inport = :#{#exportObj.exportInport}, incoterms = :#{#exportObj.incoterms}, ship_name = :#{#exportObj.shipName}, voy_no = :#{#exportObj.voyNo}, packing = :#{#exportObj.packing}, transport_mode = :#{#exportObj.transportMode}, fourtyf = :#{#exportObj.fourtyf}, twentyf = :#{#exportObj.twentyf}, invoice_price = :#{#exportObj.invoicePrice}, bl_awb_no = :#{#exportObj.blawbNo}, departure_port = :#{#exportObj.departurePort}, arrival_port = :#{#exportObj.arrivalPort}, cut = :#{#exportObj.cut}, etd = :#{#exportObj.etd}, eta = :#{#exportObj.eta}, an_advance_fee = :#{#exportObj.anAdvanceFee}, supplier_name = :#{#exportObj.supplierName}, representative_item = :#{#exportObj.representativeItem}, freight_kg = :#{#exportObj.freightKg}, freight_m3 = :#{#exportObj.freightM3}, cts_number = :#{#exportObj.ctsNumber}, size = :#{#exportObj.size }, rt_ship = :#{#exportObj.rtShip}, vw_air = :#{#exportObj.vwAir}, hl_boxes_number = :#{#exportObj.hlBoxesNumber}, money_order = :#{#exportObj.moneyOrder}, inport_tax_advance_amount = :#{#exportObj.inportTaxAdvanceAmount}, remarks = :#{#exportObj.remarks}, declaration_date = :#{#exportObj.declarationDate}, inspection_date = :#{#exportObj.inspectionDate}, delivery_date_and_time = :#{#exportObj.deliveryDateAndTime}, possible_pick_up_date = :#{#exportObj. possiblePickUpDate}, pick_up_date = :#{#exportObj.pickUpDate}, container_no = :#{#exportObj.containerNo}, cargo_insurance = :#{#exportObj.cargoInsurance}, free_time = :#{#exportObj.freeTime}, logistics_cost = :#{#exportObj.logisticsCost} , declarationdateupdatestatus = :#{#exportObj.declarationDateUpdateStatus} , inspectiondateupdatestatus = :#{#exportObj.inspectiondateupdatestatus} , deliverydateupdatestatus = :#{#exportObj.deliverydateupdatestatus}, possiblepickupdateupdatestatus = :#{#exportObj.possiblepickupdateupdatestatus}, pickupdateupdatestatus = :#{#exportObj.pickupdateupdatestatus}, freedateupdatestatus = :#{#exportObj.freedateupdatestatus}, scheduledatesupdatestatus = :#{#exportObj.scheduledatesupdatestatus} where invoice_no = :#{#exportObj.invoiceCode} and customer_name = :#{#exportObj.customerName}""",
        nativeQuery = true)
    fun updateExport(@Param("exportObj") exportObj: ExportRegistration?)

    @Query(value = "select distinct invoice_no from t_export_registration as e join t_export_chat_daily_save as c on \n" +
            "e.invoice_no = c.invoice_code where c.send_company_code = :companyCode \n ", nativeQuery = true)
    fun getChatInvoice(@Param("companyCode")companyCode:String?): List<String>?

}