package com.example.exportControl.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "inquiry")
class Inquiry{

    @Id
    @Column(name = "inquiry_id")
    var inquiryId : String? = null

    @Column(name = "inquiry_flg")
    var inquiryFlg : Int? = 0

    @Column(name = "company_name")
    var companyName : String? = null

    @Column(name = "name")
    var name : String? = null

    @Column(name = "phone_no")
    var phoneNo : String? = null

    @Column(name = "mail_address")
    var mailAddress : String? = null

    @Column(name = "inquiry_company_name")
    var inquiryCompanyName : String? = null

    @Column(name = "intercoms")
    var intercoms : String? = null

    @Column(name = "land")
    var land : String? = null

    @Column(name = "depart")
    var depart : String? = null

    @Column(name = "ship_name")
    var shipName : String? = null

    @Column(name = "container_size")
    var containerSize : String? = null

    @Column(name = "invoice_no")
    var invoiceNo : String? = null

//    constructor(
//        companyCode: String?,
//        companyNameJPN: String?,
//        companyNameENG: String?,
//        countryName: String?,
//        createdBy: String?,
//        createdDt: Timestamp?,
//        updatedBy: String?,
//        updatedDt: Timestamp?,
//        deleteFlag: Int?
//    ) {
//        this.companyCode = companyCode
//        this.companyNameJPN = companyNameJPN
//        this.companyNameENG = companyNameENG
//        this.countryName = countryName
//        this.createdBy = createdBy
//        this.createdDt = createdDt
//        this.updatedBy = updatedBy
//        this.updatedDt = updatedDt
//        this.deleteFlag = deleteFlag
//    }
}