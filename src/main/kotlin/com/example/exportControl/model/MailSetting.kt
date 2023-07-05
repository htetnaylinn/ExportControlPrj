package com.example.exportControl.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "m_mail")
class MailSetting {

    @Id
    @Column(name = "user_code")
    var userCode: String? = null

    @Column(name = "host")
    var host: String? = null

    @Column(name = "port")
    var port: String? = null

    @Column(name = "protocol")
    var protocol: String? = null

    @Column(name = "user_name")
    var userName: String? = null

    @Column(name = "mail_password")
    var mailPassword: String? = null

    @Column(name = "unzipped_email_address")
    var unzippedEmailAddress: String? = null

    constructor()
    constructor(
        userCode: String?,
        host: String?,
        port: String?,
        protocol: String?,
        userName: String?,
        mailPassword: String?,
        unzippedEmailAddress: String?
    ) {
        this.userCode = userCode
        this.host = host
        this.port = port
        this.protocol = protocol
        this.userName = userName
        this.mailPassword = mailPassword
        this.unzippedEmailAddress = unzippedEmailAddress
    }

}

