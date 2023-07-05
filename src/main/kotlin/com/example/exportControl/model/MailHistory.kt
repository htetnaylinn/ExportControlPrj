package com.example.exportControl.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "t_mail_read_history")
class MailHistory{

    @Id
    @Column(name = "history_id")
    var historyId : Int? = null

    @Column(name = "user_code")
    var userCode : String? = null

    @Column(name = "mail_id")
    var mailId : Int? = null



    constructor()

    constructor(historyId: Int?, userCode: String?, mailId: Int?) {
        this.historyId = historyId
        this.userCode = userCode
        this.mailId = mailId
    }
}