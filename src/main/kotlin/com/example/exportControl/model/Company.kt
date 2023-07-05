package com.example.exportControl.model

import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "m_company")
class Company{

    @Id
    @Column(name = "company_code")
    var companyCode : String? = null

    @Column(name = "company_name_jpn")
    var companyNameJPN : String? = null

    @Column(name = "company_name_eng")
    var companyNameENG : String? = null

    @Column(name = "country_name")
    var countryName : String? = null

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
        companyCode: String?,
        companyNameJPN: String?,
        companyNameENG: String?,
        countryName: String?,
        createdBy: String?,
        createdDt: Timestamp?,
        updatedBy: String?,
        updatedDt: Timestamp?,
        deleteFlag: Int?
    ) {
        this.companyCode = companyCode
        this.companyNameJPN = companyNameJPN
        this.companyNameENG = companyNameENG
        this.countryName = countryName
        this.createdBy = createdBy
        this.createdDt = createdDt
        this.updatedBy = updatedBy
        this.updatedDt = updatedDt
        this.deleteFlag = deleteFlag
    }
}