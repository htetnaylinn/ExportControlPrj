package com.example.exportControl.repository

import com.example.exportControl.model.ExportDocumentSave
import com.example.exportControl.model.ExportProductSave
import com.example.exportControl.model.Muser
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface ExportDocumentSaveRepository : CrudRepository<ExportDocumentSave, String> {
    @Query(
        value = "select document_id from t_export_document_save where invoice_code = :invoiceCode order by document_id desc limit 1",
        nativeQuery = true
    )
    fun selectLastDocumentIdByInvoiceCode(@Param("invoiceCode")invoiceCode:String?): Int?

    @Query(
        value = "select * from t_export_document_save where invoice_code = :invoiceCode ",
        nativeQuery = true
    )
    fun selectDocumentByInvoiceCode(@Param("invoiceCode")invoiceCode:String?): List<ExportDocumentSave>?

    @Query(
        value = "select document_name from t_export_document_save where invoice_code = :invoiceCode ",
        nativeQuery = true
    )
    fun selectDocumentNameByInvoiceCode(@Param("invoiceCode")invoiceCode:String?): List<String>?

    @Modifying
    @Transactional
    @Query(
        value = "delete from t_export_document_save where invoice_code = :invoiceCode and document_id = :documentId ",
        nativeQuery = true
    )
    fun deleteDocument(@Param("invoiceCode")invoiceCode:String?,@Param("documentId")documentId:Int?)

    @Query(
        value = "select * from t_export_document_save where invoice_code = :invoiceCode and document_id = :documentId ",
        nativeQuery = true
    )
    fun selectDocumentFile(@Param("invoiceCode")invoiceCode:String?,@Param("documentId")documentId:Int?): ExportDocumentSave?
}