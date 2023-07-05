package com.example.exportControl.model

import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "m_group")
class Group {
    @Id
    @Column(name = "group_code")
    var groupCode : String? = null

    @Column(name = "group_name")
    var groupName : String? = null

    @Column(name = "company_name")
    var companyName : String? = null

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
        groupCode: String?,
        groupName: String?,
        companyName: String?,
        createdBy: String?,
        createdDt: Timestamp?,
        updatedBy: String?,
        updatedDt: Timestamp?,
        deleteFlag: Int?
    ) {
        this.groupCode = groupCode
        this.groupName = groupName
        this.companyName = companyName
        this.createdBy = createdBy
        this.createdDt = createdDt
        this.updatedBy = updatedBy
        this.updatedDt = updatedDt
        this.deleteFlag = deleteFlag
    }
}