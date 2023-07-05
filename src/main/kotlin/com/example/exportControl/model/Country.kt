package com.example.exportControl.model

import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "m_country")
class Country {
    @Id
    @Column(name = "country_code")
    var countryCode : String? = null

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
        countryCode: String?,
        countryName: String?,
        createdBy: String?,
        createdDt: Timestamp?,
        updatedBy: String?,
        updatedDt: Timestamp?,
        deleteFlag: Int?
    ) {
        this.countryCode = countryCode
        this.countryName = countryName
        this.createdBy = createdBy
        this.createdDt = createdDt
        this.updatedBy = updatedBy
        this.updatedDt = updatedDt
        this.deleteFlag = deleteFlag
    }
}