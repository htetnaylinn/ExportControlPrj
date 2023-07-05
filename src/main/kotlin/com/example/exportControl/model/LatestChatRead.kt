package com.example.exportControl.model

import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "t_latest_chat_read")
class LatestChatRead{

    @Id
    @Column(name = "id")
    var id : Int? = null

    @Column(name = "user_code")
    var userCode : String? = null

    @Column(name = "invoice_code")
    var invoiceCode : String? = null

    @Column(name = "send_company_code")
    var sendCompanyCode : String? = null

    @Column(name = "message_id")
    var messageId : Int? = null

    @Column(name = "last_seen_time")
    var lastSeenTime : Timestamp? = null

    constructor()
    constructor(
        id: Int?,
        userCode: String?,
        invoiceCode: String?,
        sendCompanyCode: String?,
        messageId: Int?,
        lastSeenTime: Timestamp?
    ) {
        this.id = id
        this.userCode = userCode
        this.invoiceCode = invoiceCode
        this.sendCompanyCode = sendCompanyCode
        this.messageId = messageId
        this.lastSeenTime = lastSeenTime
    }
}