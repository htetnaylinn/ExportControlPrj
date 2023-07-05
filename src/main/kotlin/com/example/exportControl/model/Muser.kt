package com.example.exportControl.model

import java.sql.Timestamp
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "m_user")
class Muser {

    @Id
    @Column(name = "user_code")
    var userId: String? = null

    @Column(name = "user_name")
    var userName: String? = null

    @Column(name = "company_code")
    var companyCode: String? = null

    @Column(name = "group_code")
    var groupCode: String? = null

    @Column(name = "start_date")
    var startDate: Date? = null

    @Column(name = "end_date")
    var endDate: Date? = null

    @Column(name = "role")
    var role: String? = null

    @Column(name = "password")
    var password: String? = null

    @Column(name = "mail_address")
    var emailAddress: String? = null

    @Column(name = "lang_flg")
    var langFlg: String? = null

    @Column(name = "delete_flag")
    var deleteFlag: Int? = null

    @Column(name = "created_by")
    protected open var createdBy : String? = null

    @Column(name = "created_dt")
    protected open var createdDt : Timestamp? = null

    @Column(name = "updated_by")
    protected open var updatedBy : String? = null

    @Column(name = "updated_dt")
    protected open var updatedDt : Timestamp? = null
//    override var createdBy : String? = null
//    override var createdDt : Timestamp? = null
//    override var updatedBy : String? = null
//    override var updatedDt : Timestamp? = null


    constructor(userId: String?, userName:String?,encryptPassword: String?) {
        this.userId = userId
        this.userName = userName
        this.password = encryptPassword
    }

    constructor(userId: String?,)  {
        this.userId = userId
    }

    constructor(
        userId: String?,
        userName: String?,
        companyCode: String?,
        groupCode: String?,
        startDate: Date?,
        endDate: Date?,
        role: String?,
        password: String?,
        emailAddress: String?,
        langFlg: String?,
        deleteFlag: Int?,
        createdBy: String?,
        createdDt : Timestamp?,
        updatedBy: String?,
        updatedDt: Timestamp?

    )  {
        this.userId = userId
        this.userName = userName
        this.companyCode = companyCode
        this.groupCode = groupCode
        this.startDate = startDate
        this.endDate = endDate
        this.role = role
        this.password = password
        this.emailAddress = emailAddress
        this.langFlg = langFlg
        this.deleteFlag = deleteFlag
        this.createdBy = createdBy
        this.createdDt = createdDt
        this.updatedBy= updatedBy
        this.updatedDt = updatedDt
    }

//    constructor(
//        user_id: String?,
//        user_name: String?,
//        company_code: String?,
//        group_code: String?,
//        start_date: Date?,
//        end_date: Date?,
//        role: String?,
//        password: String?,
//        delete_flag: Int?,
//        createdBy: String?,
//        createdDt : Timestamp?,
//        updatedBy: String?,
//        updatedDt: Timestamp?
//    ) : super() {
//        this.userId = user_id
//        this.userName = user_name
//        this.companyCode = company_code
//        this.groupCode = group_code
//        this.startDate = start_date
//        this.endDate = end_date
//        this.role = role
//        this.password = password
//        this.deleteFlag = delete_flag
//        this.createdBy = createdBy
//        this.createdDt = createdDt
//        this.updatedBy= updatedBy
//        this.updatedDt = updatedDt
//
//    }


}

