package com.example.exportControl.model

import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "t_export_chat_daily_save")
class ExportChatDailySaveShow {
    @Id
    @Column(name = "id")
    var id : Int? = null

    @Column(name = "invoice_code")
    var invoiceCode : String? = null

    @Column(name = "send_company_code")
    var sendCompanyCode : String? = null

    @Column(name = "group_code")
    var groupCode : String? = null

    @Column(name = "user_code")
    var userCode : String? = null

    @Column(name = "chat_message")
    var chatMessage : String? = null

    @Column(name = "chat_image")
    var chatImage : String? = null

    @Column(name = "react_count")
    var reactCount : Int? = null

    @Column(name = "chat_date")
    var chatDate : Timestamp? = null

    @Column(name = "created_by")
    var createdBy : String? = null

    @Column(name = "created_dt")
    var createdDt : Timestamp? = null

    @Column(name = "updated_by")
    var updatedBy : String? = null

    @Column(name = "updated_dt")
    var updatedDt : Timestamp? = null

    @Column(name = "react_flag")
    var reactFlag : String? = null

    @Column(name = "user_name")
    var userName : String? = null

    constructor()
    constructor(
        id: Int?,
        invoiceCode: String?,
        sendCompanyCode: String?,
        groupCode: String?,
        userCode: String?,
        chatMessage: String?,
        chatImage: String?,
        reactCount: Int?,
        chatDate: Timestamp?,
        createdBy: String?,
        createdDt: Timestamp?,
        updatedBy: String?,
        updatedDt: Timestamp?,
        reactFlag: String?,
        userName: String?
    ) {
        this.id = id
        this.invoiceCode = invoiceCode
        this.sendCompanyCode = sendCompanyCode
        this.groupCode = groupCode
        this.userCode = userCode
        this.chatMessage = chatMessage
        this.chatImage = chatImage
        this.reactCount = reactCount
        this.chatDate = chatDate
        this.createdBy = createdBy
        this.createdDt = createdDt
        this.updatedBy = updatedBy
        this.updatedDt = updatedDt
        this.reactFlag = reactFlag
        this.userName = userName
    }


}