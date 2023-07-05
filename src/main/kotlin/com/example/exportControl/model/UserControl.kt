package com.example.exportControl.model

import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user_control")
class UserControl{

    @Id
    @Column(name = "user_code")
    var userCode : String? = null

    @Column(name = "export_registration_flg")
    var exportRegistrationFlg : Int? = null

    @Column(name = "schedule_flg")
    var scheduleFlag : Int? = null

    @Column(name = "user_flg")
    var userFlag : Int? = null

    @Column(name = "product_flg")
    var productFlag : Int? = null

    @Column(name = "company_flg")
    var companyFlag : Int? = null

    @Column(name = "group_flg")
    var groupFlag : Int? = null

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
        userCode: String?,
        exportRegistrationFlg: Int?,
        scheduleFlag: Int?,
        userFlag: Int?,
        productFlag: Int?,
        companyFlag: Int?,
        groupFlag: Int?,
        createdBy: String?,
        createdDt: Timestamp?,
        updatedBy: String?,
        updatedDt: Timestamp?
    ) {
        this.userCode = userCode
        this.exportRegistrationFlg = exportRegistrationFlg
        this.scheduleFlag = scheduleFlag
        this.userFlag = userFlag
        this.productFlag = productFlag
        this.companyFlag = companyFlag
        this.groupFlag = groupFlag
        this.createdBy = createdBy
        this.createdDt = createdDt
        this.updatedBy = updatedBy
        this.updatedDt = updatedDt
    }
}
