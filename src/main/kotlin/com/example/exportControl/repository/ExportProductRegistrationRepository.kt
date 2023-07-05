package com.example.exportControl.repository

import com.example.exportControl.model.ExportProductSave
import com.example.exportControl.model.Muser
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface ExportProductRegistrationRepository : CrudRepository<ExportProductSave, String> {

    @Query(value = "select * from t_export_product_save", nativeQuery = true)
    fun getAllProducts(): List<ExportProductSave>

    @Query(value = "select * from t_export_product_save where invoice_code = :invoiceCode", nativeQuery = true)
    fun searchWithCode(@Param("invoiceCode")invoiceCode:String?): List<ExportProductSave>

    @Query(value = "select * from t_export_product_save where product_code = :productCode", nativeQuery = true)
    fun searchWithPCode(@Param("productCode")productCode:String?): List<ExportProductSave>

    @Query(value = "select * from t_export_product_save where loading_port = :loadingPort", nativeQuery = true)
    fun searchWithLoading(@Param("loadingPort")loadingPort:String?): List<ExportProductSave>

    @Query(value = "select * from t_export_product_save where unloading_land = :unloadingLand", nativeQuery = true)
    fun searchWithUnLoading(@Param("unloadingLand")unloadingLand:String?): List<ExportProductSave>

    @Query(value = "select * from t_export_product_save where transport_mode = :transportMode", nativeQuery = true)
    fun searchWithTransport(@Param("transportMode")transportMode:String?): List<ExportProductSave>

    @Query(value = "select * from t_export_product_save where weight_kg = :weightKg", nativeQuery = true)
    fun searchWithWeight(@Param("weightKg")weightKg:String?): List<ExportProductSave>

    @Query(value = "select * from t_export_product_save where pick_up = :pickUp", nativeQuery = true)
    fun searchWithPick(@Param("pickUp")pickUp:String?): List<ExportProductSave>
}