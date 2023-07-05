package com.example.exportControl.model

import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "m_fare")
class Fare {
    @Id
    @Column(name = "fare_code")
    var fareCode : String? = null

    @Column(name = "order_list")
    var orderList : String? = null

    @Column(name = "fare_title")
    var fareTitle : String? = null

    @Column(name = "fare_content")
    var fareContent : String? = null

    @Column(name = "fare_file_name")
    var fareFileName : String? = null

    @Column(name = "fare_file")
    var fareFile : ByteArray? = null

    @Column(name = "delete_flag")
    var deleteFlag : Int? = 0

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
        fareCode: String?,
        orderList: String?,
        fareTitle: String?,
        fareContent: String?,
        fareFileName: String?,
        fareFile: ByteArray?,
        deleteFlag: Int?,
        createdBy: String?,
        createdDt: Timestamp?,
        updatedBy: String?,
        updatedDt: Timestamp?
    ) {
        this.fareCode = fareCode
        this.orderList = orderList
        this.fareTitle = fareTitle
        this.fareContent = fareContent
        this.fareFileName = fareFileName
        this.fareFile = fareFile
        this.deleteFlag = deleteFlag
        this.createdBy = createdBy
        this.createdDt = createdDt
        this.updatedBy = updatedBy
        this.updatedDt = updatedDt
    }


}