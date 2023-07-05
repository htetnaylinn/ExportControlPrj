package com.example.exportControl.model

import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "m_product")
class Product {
    @Id
    @Column(name = "product_code")
    var productCode : String? = null

    @Column(name = "product_name")
    var productName : String? = null

    @Column(name = "created_by")
    var createdBy : String? = null

    @Column(name = "created_dt")
    var createdDt : Timestamp? = null

    @Column(name = "updated_by")
    var updatedBy : String? = null

    @Column(name = "updated_dt")
    var updatedDt : Timestamp? = null

    @Column(name = "delete_flag")
    var deleteFlag : Int? = 0

    constructor()
    constructor(
        productCode: String?,
        productName: String?,
        createdBy: String?,
        createdDt: Timestamp?,
        updatedBy: String?,
        updatedDt: Timestamp?,
        deleteFlag: Int?
    ) {
        this.productCode = productCode
        this.productName = productName
        this.createdBy = createdBy
        this.createdDt = createdDt
        this.updatedBy = updatedBy
        this.updatedDt = updatedDt
        this.deleteFlag = deleteFlag
    }
}