package com.example.exportControl.model

import java.sql.Timestamp
import javax.persistence.*

@Entity
@IdClass(DocumentSerializable::class)
@Table(name = "t_export_document_save")
class ExportDocumentSave {

    @Id
    @Column(name = "invoice_code")
    var invoiceCode : String? = null

    @Id
    @Column(name = "document_id")
    var documentId : Int? = null

    @Column(name = "document_name")
    var documentName : String? = null

    @Column(name = "document_size")
    var documentSize : Double? = null

    @Column(name = "document_file")
    var documentFile : ByteArray? = null

    @Column(name = "created_by")
    var createdBy : String? = null

    @Column(name = "created_dt")
    var createdDt : Timestamp? = null

    @Column(name = "updated_by")
    var updatedBy : String? = null

    @Column(name = "updated_dt")
    var updatedDt : Timestamp? = null

    constructor()
    constructor(
        invoiceCode: String?,
        documentId: Int?,
        documentName: String?,
        documentSize: Double?,
        documentFile: ByteArray?,
        createdBy: String?,
        createdDt: Timestamp?,
        updatedBy: String?,
        updatedDt: Timestamp?
    ) {
        this.invoiceCode = invoiceCode
        this.documentId = documentId
        this.documentName = documentName
        this.documentSize = documentSize
        this.documentFile = documentFile
        this.createdBy = createdBy
        this.createdDt = createdDt
        this.updatedBy = updatedBy
        this.updatedDt = updatedDt
    }
}