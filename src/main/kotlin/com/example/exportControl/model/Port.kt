package com.example.exportControl.model

import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "m_port")
class Port {
    @Id
    @Column(name = "port_code")
    var portCode : String? = null

    @Column(name = "port_name")
    var portName : String? = null

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
        portCode: String?,
        portName: String?,
        countryName: String?,
        createdBy: String?,
        createdDt: Timestamp?,
        updatedBy: String?,
        updatedDt: Timestamp?,
        deleteFlag: Int?
    ) {
        this.portCode = portCode
        this.portName = portName
        this.countryName = countryName
        this.createdBy = createdBy
        this.createdDt = createdDt
        this.updatedBy = updatedBy
        this.updatedDt = updatedDt
        this.deleteFlag = deleteFlag
    }
}